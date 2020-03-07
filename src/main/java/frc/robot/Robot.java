/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.util.Color;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import frc.robot.command.BallStorageCommand;
import frc.robot.command.CounterResetCommand;
import frc.robot.command.DriveCommand;
import frc.robot.command.RaiseHook;
import frc.robot.command.ShootCommand;
import frc.robot.command.StorageCommand;
import frc.robot.subsystem.TechnoTechSubsystem;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {
  private Command m_autonomousCommand;

  private RobotContainer m_robotContainer;

  List<TechnoTechSubsystem> subsystems = new ArrayList<>();

  private SendableChooser<Color> chooser;


  boolean isRecording = false;
  static final int autoNumber = 1;
  static final String autoFile = new String("/home/lvuser/recordedAuto" + autoNumber + ".csv");
  BTMacroPlay player;
  BTMacroRecord recorder;

  /**
   * This function is run when the robot is first started up and should be used
   * for any initialization code.
   */
  @Override
  public void robotInit() {
    // Instantiate our RobotContainer. This will perform all our button bindings,
    // and put our
    // autonomous chooser on the dashboard.
    m_robotContainer = new RobotContainer();
    chooser = new SendableChooser<Color>();
    RobotMap.init();
    initSubsystems();
    SmartDashboard.putNumber("Test Speed", 15000);
    SmartDashboard.putNumber("Shooter P", 1.5);
    SmartDashboard.putNumber("Shooter I", 0.000001);
    SmartDashboard.putNumber("Shooter D", 0.6);
    SmartDashboard.putNumber("Shooter FF", 0.0575);

    SmartDashboard.putNumber("faceOff P", 0.7);
    SmartDashboard.putNumber("faceOff I", 0);
    SmartDashboard.putNumber("faceOff D", 0.006);

    SmartDashboard.putNumber("Drive P", 0.25);
    SmartDashboard.putNumber("Drive I", 0);
    SmartDashboard.putNumber("Drive D", 0);
    

    chooser.setDefaultOption("Red", Color.kFirstRed);
    chooser.addOption("Yellow", Color.kYellow);
    chooser.addOption("Blue", Color.kBlue);
    chooser.addOption("Green", Color.kGreen);

    SmartDashboard.putData("Color", chooser);
  }

  public void initSubsystems() {
    subsystems.add(RobotMap.driveSubsystem);
    subsystems.add(RobotMap.ballStorageSubsystem);
    subsystems.add(RobotMap.shooterSubsystem);
    subsystems.add(RobotMap.colorSensorSubsystem);
    subsystems.add(RobotMap.climberSubsystem);
    subsystems.add(RobotMap.hookSubsystem);
    CommandScheduler.getInstance().setDefaultCommand(RobotMap.driveSubsystem, new DriveCommand());
    CommandScheduler.getInstance().setDefaultCommand(RobotMap.hookSubsystem, new RaiseHook());
  }

  /**
   * This function is called every robot packet, no matter the mode. Use this for
   * items like diagnostics that you want ran during disabled, autonomous,
   * teleoperated and test.
   *
   * <p>
   * This runs after the mode specific periodic functions, but before LiveWindow
   * and SmartDashboard integrated updating.
   */
  @Override
  public void robotPeriodic() {
    // Runs the Scheduler. This is responsible for polling buttons, adding
    // newly-scheduled
    // commands, running already-scheduled commands, removing finished or
    // interrupted commands,
    // and running subsystem periodic() methods. This must be called from the
    // robot's periodic
    // block in order for anything in the Command-based framework to work.
    CommandScheduler.getInstance().run();
    RobotMap.colorSensorSubsystem.detectChanges();

  }

  /**
   * This function is called once each time the robot enters Disabled mode.
   */
  @Override
  public void disabledInit() {
  }

  @Override
  public void disabledPeriodic() {
  }

  /**
   * This autonomous runs the autonomous command selected by your
   * {@link RobotContainer} class.
   */
  @Override
  public void autonomousInit() {
    m_autonomousCommand = m_robotContainer.getAutonomousCommand();

    // schedule the autonomous command (example)
     if (m_autonomousCommand != null) {
       m_autonomousCommand.schedule(false);
    }

    player = null;

    try {
      player = new BTMacroPlay();
    }catch(FileNotFoundException e) {
      e.printStackTrace();
    }
  }

  /**
   * This function is called periodically during autonomous.
   */
  @Override
  public void autonomousPeriodic() {
  //  if(isAutonomous()) {
  //    if(player != null) {
  //      player.play();
  //    }
  //  }else {
  //    if(player != null) {
  //      player.end();
  //    }
  //  }


  }

  @Override
  public void teleopInit() {
    // This makes sure that the autonomous stops running when
    // teleop starts running. If you want the autonomous to
    // continue until interrupted by another command, remove
    // this line or comment it out.
    if (m_autonomousCommand != null) {
      m_autonomousCommand.cancel();
    }
    new BallStorageCommand(false, 0).execute();
    new ShootCommand(false, false).execute();
    new StorageCommand(false).execute();

    // recorder = null;
    // try {
    //   recorder = new BTMacroRecord();
    // }catch(IOException e) {
    //   e.printStackTrace();
    // }
  }

  /**
   * This function is called periodically during operator control.
   */
  @Override
  public void teleopPeriodic() {
      log();

      if(isOperatorControl()) {
        if(RobotMap.controller0.getLeftBumperValue()) {
          isRecording = true;
        }
        if(isRecording) {
          try {
            if(recorder != null) {
              recorder.record();
            }
          }catch (IOException e) {
            e.printStackTrace();
          }
        }
      }
      else {
        try {
          if(recorder != null) {
            recorder.end();
          }
        }catch (IOException e) {
          e.printStackTrace();
        }
      }
  }

  private void log() {
    subsystems.forEach(TechnoTechSubsystem::log);
    SmartDashboard.putData("Reset Rotations", new CounterResetCommand());
  }

  @Override
  public void testInit() {
    // Cancels all running commands at the start of test mode.
    //CommandScheduler.getInstance().cancelAll();
  }

  /**
   * This function is called periodically during test mode.
   */
  @Override
  public void testPeriodic() {
  
  }
}
