package frc.robot.subsystem;

import org.junit.Test;

import edu.wpi.first.wpilibj.SpeedControllerGroup;
import frc.robot.subsystem.DriveSubsystem;
import frc.robot.RobotMap;

import static org.junit.Assert.*;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

public class DriveSubsystemTest {
    public static int frontleftDriveMotor = 0;
    public static int frontrightDriveMotor = 1;
    public static int backleftDriveMotor = 2;
	public static int backrightDriveMotor = 3;

	public static WPI_TalonSRX frontLeftMotorController;
    public static WPI_TalonSRX frontRightMotorController;
    public static WPI_TalonSRX backLeftMotorController;
    public static WPI_TalonSRX backRightMotorController;
    
    public static SpeedControllerGroup leftControllers;
    public static SpeedControllerGroup rightControllers;
	public static DriveSubsystem driveSubsystem;

    //@Test
    public void testInit () {
        //setup
        frontLeftMotorController = new WPI_TalonSRX(frontleftDriveMotor);
		frontRightMotorController = new WPI_TalonSRX(frontrightDriveMotor);
		backLeftMotorController = new WPI_TalonSRX(backleftDriveMotor);
		backRightMotorController = new WPI_TalonSRX(backrightDriveMotor);
		leftControllers = new SpeedControllerGroup(frontLeftMotorController, backLeftMotorController);
		rightControllers = new SpeedControllerGroup(frontRightMotorController, backRightMotorController);

		driveSubsystem = new DriveSubsystem(frontLeftMotorController, frontRightMotorController, backLeftMotorController, backRightMotorController);
        //when
        DriveSubsystem subject = driveSubsystem;

        //then
        assertEquals("DriveCommand", subject.getDefaultCommand().getName());
        assertEquals(0.0, RobotMap.leftControllers.get(), 0.0);
        assertEquals(0.0, RobotMap.rightControllers.get(), 0.0);
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
        assertEquals(0.0, RobotMap.leftControllers.get(), 0.0);
        assertEquals(0.0, RobotMap.rightControllers.get(), 0.0);
    }
}