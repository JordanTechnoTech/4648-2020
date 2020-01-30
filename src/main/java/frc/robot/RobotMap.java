package frc.robot;

import edu.wpi.first.wpilibj.Sendable;
import edu.wpi.first.wpilibj.Talon;
// import frc.robot.subsystem.shooterSubsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import frc.robot.subsystem.DriveSubsystem;
import frc.robot.subsystem.ShooterSubsystem;

public class RobotMap {


	public static final Sendable leftEncoder = null;
	public static final Sendable rightEncoder = null;
	public static int rightDriveMotor = 1;
	public static int leftDriveMotor = 0;
	public static int shooterMotor = 1;

	// public static TalonSRX shooterMotorController;
	public static Talon leftDriveMotorController;
	public static Talon rightDriveMotorController;
	public static DifferentialDrive drivetrain;
	public static DriveSubsystem driveSubsystem;
    public static ShooterSubsystem shooterSubsystem;
	public static OI oi;



	public static void init() {
		// drive initialization
		//shooterMotorController = new TalonSRX(shooterMotor);
		leftDriveMotorController = new Talon(leftDriveMotor);
		rightDriveMotorController = new Talon(rightDriveMotor);
		drivetrain = new DifferentialDrive(leftDriveMotorController, rightDriveMotorController);
		driveSubsystem = new DriveSubsystem(leftDriveMotorController, rightDriveMotorController);
		//shooterSubsystem = new shooterSubsystem(shooterMotorController);
		
		oi = new OI();
	}
}