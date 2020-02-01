package frc.robot;

import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.Sendable;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import frc.robot.subsystem.BallStorageSubsystem;
import frc.robot.subsystem.DriveSubsystem;
import frc.robot.subsystem.ShooterSubsystem;

public class RobotMap {

	//can bus mappings
	public static int frontleftDriveMotor = 1;
	public static int frontrightDriveMotor = 2;
	public static int backleftDriveMotor = 3;
	public static int backrightDriveMotor = 4;
	public static int leftintakeBeltID = 5;
	public static int rightIntakeBeltID = 6;
	public static int shooterID = 7;
	public static int intakegateID = 0;

	public static WPI_TalonSRX frontLeftMotorController;
	public static WPI_TalonSRX frontRightMotorController;
	public static WPI_TalonSRX backLeftMotorController;
	public static WPI_TalonSRX backRightMotorController;
	public static SpeedControllerGroup leftControllers;
	public static SpeedControllerGroup rightControllers;

	public static DifferentialDrive drivetrain;
	public static DriveSubsystem driveSubsystem;
	public static final Sendable leftEncoder = null;
	public static final Sendable rightEncoder = null;

	public static BallStorageSubsystem ballStorageSubsystem;
	public static Talon roller;
	public static Talon leftIntake;
	public static Talon rightIntake;
	public static TalonSRX leftIntakeBelt;
	public static TalonSRX rightIntakeBelt;
	public static Solenoid intakeGate;

	public static ShooterSubsystem shooterSubsystem;
	public static TalonSRX shooterTalonSRX;

	public static OI oi;



	public static void init() {
		// drive initialization
		frontLeftMotorController = new WPI_TalonSRX(frontleftDriveMotor);
		frontRightMotorController = new WPI_TalonSRX(frontrightDriveMotor);
		backLeftMotorController = new WPI_TalonSRX(backleftDriveMotor);
		backRightMotorController = new WPI_TalonSRX(backrightDriveMotor);
		leftControllers = new SpeedControllerGroup(frontLeftMotorController, backLeftMotorController);
		rightControllers = new SpeedControllerGroup(frontRightMotorController, backRightMotorController);
		
		drivetrain = new DifferentialDrive(leftControllers, rightControllers);
		driveSubsystem = new DriveSubsystem(frontLeftMotorController, frontRightMotorController, backLeftMotorController, backRightMotorController);
		roller = new Talon(0);
		leftIntake = new Talon(1);
		rightIntake =  new Talon(2);
		leftIntakeBelt = new TalonSRX(leftintakeBeltID);
		rightIntakeBelt = new TalonSRX(rightIntakeBeltID);
		intakeGate = new Solenoid(intakegateID);
		shooterTalonSRX = new TalonSRX(shooterID);
		ballStorageSubsystem = new BallStorageSubsystem(roller, leftIntake, rightIntake, leftIntakeBelt, rightIntakeBelt, intakeGate);
		shooterSubsystem = new ShooterSubsystem(leftIntake, rightIntake, leftIntakeBelt, rightIntakeBelt, intakeGate, shooterTalonSRX);
		
		oi = new OI();
	}
}