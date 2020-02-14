package frc.robot;

import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.revrobotics.ColorSensorV3;

import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.command.BallStorageCommand;
import frc.robot.command.ColorSensorCommand;
import frc.robot.command.IntakeCommand;
import frc.robot.command.ShootCommand;
import frc.robot.subsystem.BallStorageSubsystem;
import frc.robot.subsystem.ColorSensorSubsystem;
import frc.robot.subsystem.DriveSubsystem;
import frc.robot.subsystem.ShooterSubsystem;

public class RobotMap {
	public static final TechnoTechXBoxController controller0 = new TechnoTechXBoxController(0);

	//can bus mappings

	//TALON
	public static int frontleftDriveMotor = 1;
	public static int frontrightDriveMotor = 2;
	public static int backleftDriveMotor = 3;
	public static int backrightDriveMotor = 4;
	public static int shooterID = 7;

	//VICTOR
	public static int leftintakeBeltID = 2;
	public static int rightIntakeBeltID = 1;
	public static int leftIntakeSPXID = 3;
	public static int rightIntakeSPXID = 4;
	public static int colorWheelMotorID = 8;
	
	//pwm mappings
	public static int driveShifterID = 0;
	public static int intakegateID = 1;
	public static int colorWheelSolenoidID = 2;
	public static int leftIntakeID = 3;
	public static int rightIntakeID = 4;

	//Drivetrain Subsystem
	public static WPI_TalonSRX frontLeftMotorController;
	public static WPI_TalonSRX frontRightMotorController;
	public static WPI_TalonSRX backLeftMotorController;
	public static WPI_TalonSRX backRightMotorController;
	public static SpeedControllerGroup leftControllers;
	public static SpeedControllerGroup rightControllers;
	public static Solenoid driveShifter;

	public static DifferentialDrive drivetrain;
	public static DriveSubsystem driveSubsystem;

	//Ball Storage Subsystem
	public static BallStorageSubsystem ballStorageSubsystem;
	public static Talon roller;
	public static VictorSPX leftIntake;
	public static VictorSPX rightIntake;
	public static VictorSPX leftIntakeBelt;
	public static VictorSPX rightIntakeBelt;
	public static Solenoid intakeGate;
	public static Solenoid rightIntakeGate;
	public static Solenoid leftIntakePiston;
	public static Solenoid rightIntakePiston;

	//Shooter Subsystem
	public static ShooterSubsystem shooterSubsystem;
	public static WPI_TalonSRX shooterTalonSRX;

	//Color Sensor Subsystem
	public static ColorSensorV3 colorSensor;
	public static VictorSPX colorWheelMotor;
	public static Solenoid colorSensorSolenoid;
	public static ColorSensorSubsystem colorSensorSubsystem;

	public static void init() {
		// drive initialization
		frontLeftMotorController = new WPI_TalonSRX(frontleftDriveMotor);
		frontRightMotorController = new WPI_TalonSRX(frontrightDriveMotor);
		backLeftMotorController = new WPI_TalonSRX(backleftDriveMotor);
		backRightMotorController = new WPI_TalonSRX(backrightDriveMotor);
		backLeftMotorController.setInverted(true);
		leftControllers = new SpeedControllerGroup(frontLeftMotorController, backLeftMotorController);
		rightControllers = new SpeedControllerGroup(frontRightMotorController, backRightMotorController);
		driveShifter = new Solenoid(driveShifterID);
		drivetrain = new DifferentialDrive(leftControllers, rightControllers);
		driveSubsystem = new DriveSubsystem(frontLeftMotorController, frontRightMotorController, backLeftMotorController, backRightMotorController, driveShifter);
		
		//intake initialization
		roller = new Talon(0);
		leftIntake = new VictorSPX(leftIntakeSPXID);
		rightIntake =  new VictorSPX(rightIntakeSPXID);
		leftIntakeBelt = new VictorSPX(leftintakeBeltID);
		leftIntakeBelt.setInverted(true);
		rightIntakeBelt = new VictorSPX(rightIntakeBeltID);
		intakeGate = new Solenoid(intakegateID);
		shooterTalonSRX = new WPI_TalonSRX(shooterID);
		leftIntakePiston = new Solenoid(leftIntakeID);
		rightIntakePiston = new Solenoid(rightIntakeID);
		ballStorageSubsystem = new BallStorageSubsystem(roller, leftIntake, rightIntake, leftIntakeBelt, rightIntakeBelt, intakeGate, leftIntakePiston, rightIntakePiston);
		shooterSubsystem = new ShooterSubsystem(leftIntake, rightIntake, leftIntakeBelt, rightIntakeBelt, intakeGate, shooterTalonSRX);
		
		//color sensor initialization
		colorSensor = new ColorSensorV3(I2C.Port.kOnboard);
		// colorWheelMotor = new VictorSPX(colorWheelMotorID);
		colorSensorSolenoid = new Solenoid(colorWheelSolenoidID);
		colorSensorSubsystem = new ColorSensorSubsystem(colorSensor, colorSensorSolenoid, colorWheelMotor);
	
		buttonbinding();
	}
	public static void buttonbinding(){
		controller0.xButton.toggleWhenPressed(new ColorSensorCommand());
		controller0.aButton.whileHeld(new BallStorageCommand());
		controller0.bButton.toggleWhenPressed(new ShootCommand());
		controller0.lbButton.toggleWhenPressed(new IntakeCommand());
	}

	public static void logButtonState(){
		SmartDashboard.putBoolean("aButton", controller0.aButton.get());
		SmartDashboard.putBoolean("bButton", controller0.bButton.get());
		SmartDashboard.putBoolean("lbButton", controller0.lbButton.get());
	}

}