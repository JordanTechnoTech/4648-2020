package frc.robot;

import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.Sendable;
import edu.wpi.first.wpilibj.Talon;
import frc.robot.subsystem.DriveSubsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

public class RobotMap {


	public static final Sendable leftEncoder = null;
	public static final Sendable rightEncoder = null;
	public static int rightDriveMotor = 1;
	public static int leftDriveMotor = 0;

	public static Talon leftDriveMotorController;
	public static Talon rightDriveMotorController;
	public static DifferentialDrive drivetrain;
	public static DriveSubsystem driveSubsystem;
	public static OI oi;

	public static TalonSRX rightTalonSRX;
	public static TalonSRX leftTalonSRX;


	public static void init() {
		// drive initialization
		leftDriveMotorController = new Talon(leftDriveMotor);
		rightDriveMotorController = new Talon(rightDriveMotor);
		drivetrain = new DifferentialDrive(leftDriveMotorController, rightDriveMotorController);
		driveSubsystem = new DriveSubsystem(leftDriveMotorController, rightDriveMotorController);
		
		leftTalonSRX = new TalonSRX(0);
		rightTalonSRX = new TalonSRX(1);
		
		
		oi = new OI();
	}
}