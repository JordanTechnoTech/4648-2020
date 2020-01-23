package frc.robot;

import edu.wpi.first.wpilibj.Sendable;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

public class RobotMap {


	public static final Sendable leftEncoder = null;
	public static final Sendable rightEncoder = null;
	public static int rightDriveMotor = 0;
	public static int leftDriveMotor = 3;

	public static Spark leftDriveMotorController;
	public static Spark rightDriveMotorController;
	public static DifferentialDrive drivetrain;


	public static void init() {
		// drive initialization
		leftDriveMotorController = new Spark(leftDriveMotor);
		rightDriveMotorController = new Spark(rightDriveMotor);
		drivetrain = new DifferentialDrive(leftDriveMotorController, rightDriveMotorController);
	}
}