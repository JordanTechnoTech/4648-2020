package frc.robot.subsystem;
import org.junit.Test;

import frc.robot.subsystem.DriveSubsystem;
import frc.robot.RobotMap;

import static org.junit.Assert.*;

public class DriveSubsystemTest {
    @Test
    public void testInit () {
        //setup
        RobotMap.init();
        //when
        DriveSubsystem subject = new DriveSubsystem();

        //then
        assertEquals(null, subject.getDefaultCommand());
        assertEquals(0.0, RobotMap.leftDriveMotorController.getSpeed(), 0.0);
        assertEquals(0.0, RobotMap.rightDriveMotorController.getSpeed(), 0.0);
    }

    //@Test
    public void testArcadeDrive () {
        //setup
        DriveSubsystem subject = new DriveSubsystem();
        RobotMap.init();
        //when
        subject.arcadeDrive(0.25, 0.0);

        //then
        assertEquals(null, subject.getDefaultCommand());
        assertEquals(0.25, RobotMap.leftDriveMotorController.getSpeed(), 0.0);
        assertEquals(0.25, RobotMap.rightDriveMotorController.getSpeed(), 0.0);
    }
}