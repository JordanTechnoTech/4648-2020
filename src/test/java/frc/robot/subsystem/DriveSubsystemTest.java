package frc.robot.subsystem;

import org.junit.Test;

import edu.wpi.first.wpilibj.Talon;
import frc.robot.subsystem.DriveSubsystem;
import frc.robot.RobotMap;

import static org.junit.Assert.*;

public class DriveSubsystemTest {
    public static int rightDriveMotor = 1;
	public static int leftDriveMotor = 0;

	public static Talon leftDriveMotorController;
	public static Talon rightDriveMotorController;
	public static DriveSubsystem driveSubsystem;

    @Test
    public void testInit () {
        //setup
        leftDriveMotorController = new Talon(leftDriveMotor);
		rightDriveMotorController = new Talon(rightDriveMotor);
		driveSubsystem = new DriveSubsystem(leftDriveMotorController, rightDriveMotorController);
        //when
        DriveSubsystem subject = driveSubsystem;

        //then
        assertEquals("DriveCommand", subject.getDefaultCommand().getName());
        assertEquals(0.0, RobotMap.leftDriveMotorController.getSpeed(), 0.0);
        assertEquals(0.0, RobotMap.rightDriveMotorController.getSpeed(), 0.0);
    }

    //@Test
    public void testArcadeDrive () {
        //setup
        DriveSubsystem subject = RobotMap.driveSubsystem;
        RobotMap.init();
        //when
        subject.arcadeDrive(0.25, 0.0);

        //then
        assertEquals("DriveCommand", subject.getDefaultCommand().getName());
        assertEquals(0.25, RobotMap.leftDriveMotorController.getSpeed(), 0.0);
        assertEquals(0.25, RobotMap.rightDriveMotorController.getSpeed(), 0.0);
    }
}