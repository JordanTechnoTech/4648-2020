package frc.robot;

import edu.wpi.first.wpilibj.Sendable;
import edu.wpi.first.wpilibj.Talon;
import frc.robot.subsystem.DriveSubsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

public class RobotMap {


	public static final Sendable leftEncoder = null;
	public static final Sendable rightEncoder = null;
	public static int rightDriveMotor = 0;
	public static int leftDriveMotor = 3;

	public static Talon leftDriveMotorController;
	public static Talon rightDriveMotorController;
	public static DifferentialDrive drivetrain;
	public static DriveSubsystem driveSubsystem;
	public static OI oi;


	public static void init() {
		// drive initialization
		leftDriveMotorController = new Talon(leftDriveMotor);
		rightDriveMotorController = new Talon(rightDriveMotor);
		drivetrain = new DifferentialDrive(leftDriveMotorController, rightDriveMotorController);
		driveSubsystem = new DriveSubsystem(leftDriveMotorController, rightDriveMotorController);
		oi = new OI();
	}
}