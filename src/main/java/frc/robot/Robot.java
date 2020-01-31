/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.util.Color;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import frc.robot.command.DriveCommand;
import frc.robot.subsystem.TechnoTechSubsystem;

import java.util.ArrayList;
import java.util.List;

import com.revrobotics.ColorSensorV3;

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

  private final I2C.Port i2cPort = I2C.Port.kOnboard;
  private final ColorSensorV3 m_colorSensor = new ColorSensorV3(i2cPort);

  private Color oldColor = Color.kWhite;
  private Color newColor = Color.kWhite;
  private int changes = 0;
  private String[] colors = { "RED", "YELLOW", "BLUE", "GREEN" };
  private int currentColor = 0;
  private int sensorColor = 2;

  List<TechnoTechSubsystem> subsystems = new ArrayList<>();

  /**
   * This function is run when the robot is first started up and should be used for any
   * initialization code.
   */
  @Override
  public void robotInit() {
    // Instantiate our RobotContainer.  This will perform all our button bindings, and put our
    // autonomous chooser on the dashboard.
    m_robotContainer = new RobotContainer();
    RobotMap.init();
    initSubsystems();
  }

  public void initSubsystems() {
    subsystems.add(RobotMap.driveSubsystem);
    CommandScheduler.getInstance().setDefaultCommand(RobotMap.driveSubsystem, new DriveCommand());
  }

  /** 
   * This function is called every robot packet, no matter the mode. Use this for items like
   * diagnostics that you want ran during disabled, autonomous, teleoperated and test.
   *
   * <p>This runs after the mode specific periodic functions, but before
   * LiveWindow and SmartDashboard integrated updating.
   */
  @Override
  public void robotPeriodic() {
    // Runs the Scheduler.  This is responsible for polling buttons, adding newly-scheduled
    // commands, running already-scheduled commands, removing finished or interrupted commands,
    // and running subsystem periodic() methods.  This must be called from the robot's periodic
    // block in order for anything in the Command-based framework to work.
    CommandScheduler.getInstance().run();

    Color detectedColor = m_colorSensor.getColor();
    double IR = m_colorSensor.getIR();
    double[] values = {detectedColor.red, detectedColor.green,detectedColor.blue};
    

    //check if color detected is RED
    if (values[0] >= 0.5 && values[1] <= 0.35 && values[2] <= 0.2) {
      currentColor = 0;
      newColor = Color.kRed;
    }else 
    //check if color detected is GREEN
    if (values[0] <= 0.2 && values[1]>= 0.4 && values[2] <= 0.3) {
      currentColor = 3;
      newColor = Color.kGreen;
    }else 
    //check if color detected is BLUE
    if (values[0] <= 0.2 && values[1] >= 0.2 && values[2] >= 0.4) {
      currentColor = 2;
      newColor = Color.kBlue;
    }else 
    //check if color detected is YELLOW
    if (values[0] >= 0.3 && values[1] >= 0.4 && values[2] <= 0.2) {
      currentColor = 1;
      newColor = Color.kYellow;
    }

    //check if a color change has happened
    if(oldColor != newColor) {
      changes = changes + 1;
    }
    oldColor = newColor;

    //offset the color for the sensor
    sensorColor = currentColor + 2;
    if(sensorColor > 3) {
      sensorColor = sensorColor - 3;
    }


    SmartDashboard.putNumber("Red", detectedColor.red);
    SmartDashboard.putNumber("Green", detectedColor.green);
    SmartDashboard.putNumber("Blue", detectedColor.blue);
    SmartDashboard.putNumber("IR", IR);
    SmartDashboard.putString("Color Changes", "" + changes);
    SmartDashboard.putString("Color", colors[currentColor]);
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
   * This autonomous runs the autonomous command selected by your {@link RobotContainer} class.
   */
  @Override
  public void autonomousInit() {
    m_autonomousCommand = m_robotContainer.getAutonomousCommand();

    // schedule the autonomous command (example)
    if (m_autonomousCommand != null) {
      m_autonomousCommand.schedule();
    }
  }

  /**
   * This function is called periodically during autonomous.
   */
  @Override
  public void autonomousPeriodic() {
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
  }

  /**
   * This function is called periodically during operator control.
   */
  @Override
  public void teleopPeriodic() {
      //RobotMap.drivetrain.arcadeDrive(RobotMap.oi.controller0.getStickLeftYValue(), RobotMap.oi.controller0.getStickLeftXValue());
    
      CommandScheduler.getInstance().run();
      log();
  }

  private void log() {
    subsystems.forEach(TechnoTechSubsystem::log);
  }

  @Override
  public void testInit() {
    // Cancels all running commands at the start of test mode.
    CommandScheduler.getInstance().cancelAll();
  }

  /**
   * This function is called periodically during test mode.
   */
  @Override
  public void testPeriodic() {
  }
}
