/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.util.Color;
import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.Talon;

import com.revrobotics.ColorSensorV3;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {
  private static final String kDefaultAuto = "Default";
  private static final String kCustomAuto = "My Auto";
  private String m_autoSelected;
  private final SendableChooser<String> m_chooser = new SendableChooser<>();

  private final I2C.Port i2cPort = I2C.Port.kOnboard;
  private final ColorSensorV3 m_colorSensor = new ColorSensorV3(i2cPort);

  private final Talon m_leftMotor = new Talon(0);
  private final Talon m_rightMotor = new Talon(1);
  private final DifferentialDrive m_robotDrive = new DifferentialDrive(m_leftMotor, m_rightMotor);
  private final XboxController m_xbox = new XboxController(0);
  private double velocity = 0;

  private Color oldColor = Color.kWhite;
  private Color newColor = Color.kWhite;
  private int changes = 0;
  private String[] colors = {"RED","YELLOW","BLUE","GREEN"};
  private int currentColor = 0;
  private int sensorColor = 2;

  /**
   * This function is run when the robot is first started up and should be used
   * for any initialization code.
   */
  @Override
  public void robotInit() {
    m_chooser.setDefaultOption("Default Auto", kDefaultAuto);
    m_chooser.addOption("My Auto", kCustomAuto);
    SmartDashboard.putData("Auto choices", m_chooser);
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
   * This autonomous (along with the chooser code above) shows how to select
   * between different autonomous modes using the dashboard. The sendable
   * chooser code works with the Java SmartDashboard. If you prefer the
   * LabVIEW Dashboard, remove all of the chooser code and uncomment the
   * getString line to get the auto name from the text box below the Gyro
   *
   * <p>You can add additional auto modes by adding additional comparisons to
   * the switch structure below with additional strings. If using the
   * SendableChooser make sure to add them to the chooser code above as well.
   */
  @Override
  public void autonomousInit() {
    m_autoSelected = m_chooser.getSelected();
    // m_autoSelected = SmartDashboard.getString("Auto Selector", kDefaultAuto);
    System.out.println("Auto selected: " + m_autoSelected);
  }

  /**
   * This function is called periodically during autonomous.
   */
  @Override
  public void autonomousPeriodic() {
    switch (m_autoSelected) {
      case kCustomAuto:
        // Put custom auto code here
        break;
      case kDefaultAuto:
      default:
        // Put default auto code here
        break;
    }
  }

  /**
   * This function is called periodically during operator control.
   */
  @Override
  public void teleopPeriodic() {
    velocity = m_xbox.getTriggerAxis(Hand.kRight) - m_xbox.getTriggerAxis(Hand.kLeft);

    m_robotDrive.arcadeDrive(velocity * 0.7f, (m_xbox.getX(Hand.kLeft) + 0.22f) * 0.5f);
  }

  /**
   * This function is called periodically during test mode.
   */
  @Override
  public void testPeriodic() {
  }
}
