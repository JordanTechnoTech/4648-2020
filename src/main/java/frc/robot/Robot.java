/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.Timer;
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
import frc.robot.command.ShootCommandGroup;
import frc.robot.command.StorageCommand;
import frc.robot.subsystem.TechnoTechSubsystem;

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

  Timer t;
  String recordedString = "";

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
    SmartDashboard.putNumber("Test Speed", 8000);
    SmartDashboard.putNumber("Shooter P", 1.5);
    SmartDashboard.putNumber("Shooter I", 0.000001);
    SmartDashboard.putNumber("Shooter D", 0.6);
    SmartDashboard.putNumber("Shooter FF", 0.0575);

    chooser.setDefaultOption("Red", Color.kFirstRed);
    chooser.addOption("Yellow", Color.kYellow);
    chooser.addOption("Blue", Color.kBlue);
    chooser.addOption("Green", Color.kGreen);

    SmartDashboard.putData("Color", chooser);
    t = new Timer();
  }

  public void initSubsystems() {
    subsystems.add(RobotMap.driveSubsystem);
    subsystems.add(RobotMap.ballStorageSubsystem);
    subsystems.add(RobotMap.shooterSubsystem);
    subsystems.add(RobotMap.colorSensorSubsystem);
    subsystems.add(RobotMap.climberSubsystem);
    CommandScheduler.getInstance().setDefaultCommand(RobotMap.driveSubsystem, new DriveCommand());
    CommandScheduler.getInstance().setDefaultCommand(RobotMap.climberSubsystem, new RaiseHook());
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
    // if (m_autonomousCommand != null) {
      // m_autonomousCommand.schedule(false);
    // }

    t.start();
  }

  /**
   * This function is called periodically during autonomous.
   */
  @Override
  public void autonomousPeriodic() {
    String[] splitByFrame = recordedString.split("|");
    String mostRecentFrame = "";

    for(int i = 0; i < splitByFrame.length; i++) {
      if((Double)Double.parseDouble(splitByFrame[i].split(",")[0]) < t.get()) {
        mostRecentFrame = splitByFrame[i];

        String[] mostRecentFrameSplit = mostRecentFrame.split(",");

        double turn = Double.parseDouble(mostRecentFrameSplit[1]);
        double rightTrigger = Double.parseDouble(mostRecentFrameSplit[2]);
        double leftTrigger = Double.parseDouble(mostRecentFrameSplit[3]);
        boolean yButton = Boolean.parseBoolean(mostRecentFrameSplit[4]);
        boolean state = false;

        RobotMap.driveSubsystem.arcadeDrive(-rightTrigger + leftTrigger, -turn);
      
        if(yButton && state == false) {
          new ShootCommandGroup().schedule();
          state = !state;
        }
        if(yButton && state == true) {
          new ShootCommandGroup().cancel();
          state = !state;
        }
      }
      
    }
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
    new ShootCommand(false).execute();
    new StorageCommand(false).execute();

    SmartDashboard.putString("Auto" , recordedString);
  }

  /**
   * This function is called periodically during operator control.
   */
  @Override
  public void teleopPeriodic() {
      log();
  }

  private void log() {
    subsystems.forEach(TechnoTechSubsystem::log);
    RobotMap.logButtonState();
    SmartDashboard.putData("Reset Rotations", new CounterResetCommand());
  }

  @Override
  public void testInit() {
    // Cancels all running commands at the start of test mode.
    //CommandScheduler.getInstance().cancelAll();

    t.start();
  }

  /**
   * This function is called periodically during test mode.
   */
  @Override
  public void testPeriodic() {
    if(recordedString != "") {
      recordedString += "|";
    }
    recordedString += t.get() + "," + RobotMap.controller0.getStickLeftXValue() * 0.6 + "," + RobotMap.controller0.getRightTriggerValue() * 0.6 + "," + RobotMap.controller0.getLeftTriggerValue() * 0.6 + ","  + RobotMap.controller0.yButton.get();
    
    RobotMap.driveSubsystem.arcadeDrive(-(RobotMap.controller0.getRightTriggerValue() - RobotMap.controller0.getLeftTriggerValue()) * 0.6, -RobotMap.controller0.getStickLeftXValue() * 0.6);
  }
}
