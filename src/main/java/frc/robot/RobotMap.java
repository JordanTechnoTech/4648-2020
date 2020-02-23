package frc.robot;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.revrobotics.ColorSensorV3;

import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import frc.robot.command.BallStorageCommand;
import frc.robot.command.ColorCommand;
import frc.robot.command.ColorSensorCommand;
import frc.robot.command.FaceOffCommand;
import frc.robot.command.RaiseRobot;
import frc.robot.command.ShootCommandGroup;
import frc.robot.command.FaceOffCommand.Target;
import frc.robot.subsystem.BallStorageSubsystem;
import frc.robot.subsystem.ClimberSubsystem;
import frc.robot.subsystem.ColorSensorSubsystem;
import frc.robot.subsystem.DriveSubsystem;
import frc.robot.subsystem.ShooterSubsystem;

public class RobotMap {
	public static final TechnoTechXBoxController controller0 = new TechnoTechXBoxController(0);
	public static final TechnoTechXBoxController controller1 = new TechnoTechXBoxController(1);

	

	//TALON
	public static int frontleftDriveMotor = 1;
	public static int frontrightDriveMotor = 2;
	public static int backleftDriveMotor = 3;
	public static int backrightDriveMotor = 4;
	public static int shooterID = 7;
	public static int colorWheelMotorID = 6;
	public static int hookMotorID = 8;
	public static int climberMotorID = 9;

	//VICTOR
	public static int leftintakeBeltID = 1;
	public static int rightIntakeBeltID = 2;
	public static int intakeSPXID = 3;
	
	
	//pwm mappings
	//public static int driveShifterID = 0;  
	public static int intakegateID = 0;
	public static int colorWheelSolenoidID = 2;
	public static int leftIntakeID = 3;
	public static int rightIntakeID = 4;

	//Drivetrain Subsystem
	public static WPI_TalonSRX frontLeftMotorController;
	public static WPI_TalonSRX frontRightMotorController;
	public static WPI_TalonSRX backLeftMotorController;
	public static WPI_TalonSRX backRightMotorController;
	public static Solenoid driveShifter;

	public static DifferentialDrive drivetrain;
	public static DriveSubsystem driveSubsystem;

	//Ball Storage Subsystem
	public static BallStorageSubsystem ballStorageSubsystem;
	public static Talon roller;
	public static VictorSPX intake;
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
	public static WPI_TalonSRX colorWheelMotor;
	public static Solenoid colorSensorSolenoid;
	public static ColorSensorSubsystem colorSensorSubsystem;

	//climber subsystem
	public static WPI_TalonSRX climberSRX;
	public static WPI_TalonSRX hookMotor;
	public static ClimberSubsystem climberSubsystem;

	public static void init() {
		// drive initialization
		frontLeftMotorController = new WPI_TalonSRX(frontleftDriveMotor);
		frontRightMotorController = new WPI_TalonSRX(frontrightDriveMotor);
		backLeftMotorController = new WPI_TalonSRX(backleftDriveMotor);
		backRightMotorController = new WPI_TalonSRX(backrightDriveMotor);
		frontLeftMotorController.set(ControlMode.Follower, backleftDriveMotor);
		frontRightMotorController.set(ControlMode.Follower, backrightDriveMotor);
		
		frontLeftMotorController.setInverted(false);
		backLeftMotorController.setInverted(true);
		
		//driveShifter = new Solenoid(driveShifterID);
		drivetrain = new DifferentialDrive(backLeftMotorController, backRightMotorController);
		driveSubsystem = new DriveSubsystem(frontLeftMotorController, frontRightMotorController, backLeftMotorController, backRightMotorController, driveShifter);
		
		//intake initialization
		roller = new Talon(0);
		intake = new VictorSPX(intakeSPXID);
		leftIntakeBelt = new VictorSPX(leftintakeBeltID);
		leftIntakeBelt.setInverted(true);
		rightIntakeBelt = new VictorSPX(rightIntakeBeltID);
		intakeGate = new Solenoid(intakegateID);
		shooterTalonSRX = new WPI_TalonSRX(shooterID);
		leftIntakePiston = new Solenoid(leftIntakeID);
		rightIntakePiston = new Solenoid(rightIntakeID);
		ballStorageSubsystem = new BallStorageSubsystem(roller, intake, leftIntakeBelt, rightIntakeBelt, leftIntakePiston, rightIntakePiston, intakeGate);
		shooterSubsystem = new ShooterSubsystem(shooterTalonSRX);
		
		//color sensor initialization
		colorSensor = new ColorSensorV3(I2C.Port.kOnboard);
		colorWheelMotor = new WPI_TalonSRX(colorWheelMotorID);
		colorSensorSolenoid = new Solenoid(colorWheelSolenoidID);
		colorSensorSubsystem = new ColorSensorSubsystem(colorSensor, colorSensorSolenoid, colorWheelMotor);

		//climber init
		climberSRX = new WPI_TalonSRX(climberMotorID);
		hookMotor = new WPI_TalonSRX(hookMotorID);
		climberSubsystem = new ClimberSubsystem(climberSRX, hookMotor);
	
		buttonbinding();
	}
	public static void buttonbinding() {

		//controller0.lbButton.toggleWhenPressed(new IntakeCommand());		//toggles pistons to lower intake
		controller0.rbButton.toggleWhenPressed(new ColorSensorCommand());	//toggle colorsensor piston

		controller0.bButton.whenPressed(new BallStorageCommand(false, 0));
		controller0.aButton.whenPressed(new BallStorageCommand(true, 0.5));

		controller0.yButton.toggleWhenPressed(new ShootCommandGroup());
		controller0.xButton.toggleWhenPressed(new ColorCommand());

		controller0.startButton.whenPressed(new RaiseRobot());
		controller0.backButton.whenPressed(new FaceOffCommand(Target.TOP_OUTER_HOLE));
	}

	public static void logButtonState(){
		
	}

}