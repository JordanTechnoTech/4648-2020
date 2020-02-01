package frc.robot;

import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.Sendable;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Talon;
// import frc.robot.subsystem.shooterSubsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import frc.robot.subsystem.BallStorageSubsystem;
import frc.robot.subsystem.DriveSubsystem;

public class RobotMap {


	public static final Sendable leftEncoder = null;
	public static final Sendable rightEncoder = null;
	public static int rightDriveMotor = 1;
	public static int leftDriveMotor = 0;
	public static Talon leftDriveMotorController;
	public static Talon rightDriveMotorController;
	public static DifferentialDrive drivetrain;
	public static DriveSubsystem driveSubsystem;

	public static BallStorageSubsystem ballStorageSubsystem;
	public static Talon roller;
	public static Talon leftIntake;
	public static Talon rightIntake;
	public static TalonSRX leftIntakeBelt;
	public static TalonSRX rightIntakeBelt;
	public static Solenoid intakeGate;

	public static OI oi;



	public static void init() {
		// drive initialization
		leftDriveMotorController = new Talon(leftDriveMotor);
		rightDriveMotorController = new Talon(rightDriveMotor);
		drivetrain = new DifferentialDrive(leftDriveMotorController, rightDriveMotorController);
		driveSubsystem = new DriveSubsystem(leftDriveMotorController, rightDriveMotorController);

		roller = new Talon(0);
		leftIntake = new Talon(1);
		rightIntake =  new Talon(2);
		leftIntakeBelt = new TalonSRX(1);
		rightIntakeBelt = new TalonSRX(2);
		intakeGate = new Solenoid(0);
		ballStorageSubsystem = new BallStorageSubsystem(roller, leftIntake, rightIntake, leftIntakeBelt, rightIntakeBelt, intakeGate);
		
		oi = new OI();
	}
}