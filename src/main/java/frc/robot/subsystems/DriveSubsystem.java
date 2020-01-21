/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class DriveSubsystem extends Subsystem {
	// Put methods for controlling this subsystem
	// here. Call these from Commands.
	
	private final Spark leftDrive = RobotMap.leftDriveMotorController;
	private final Spark rightDrive = RobotMap.rightDriveMotorController;
	private final DifferentialDrive differentialDrive1 = RobotMap.drivetrain;

	private final ADIS16448_IMU imu = RobotMap.imu;

	public DriveSubsystem() {
		addChild("Left CIM", (Spark) leftDrive);
		addChild("Right CIM", (Spark) rightDrive);
		addChild("IMU", imu);
		addChild("Left Encoder",RobotMap.leftEncoder);
		addChild("Right Encoder",RobotMap.rightEncoder);
		differentialDrive1.setSafetyEnabled(false);
		differentialDrive1.setExpiration(0.1);
		differentialDrive1.setMaxOutput(1.0);
	}

	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		setDefaultCommand(new DriveCommand());
	}

	public void tankDrive(double leftSpeed, double rightSpeed) {
		differentialDrive1.tankDrive(leftSpeed, rightSpeed);
	}

	public void arcadeDrive(double forwardSpeed, double rotationSpeed) {
		differentialDrive1.arcadeDrive(forwardSpeed, rotationSpeed);
	}

	public void log() {
		SmartDashboard.putNumber("Gyro-Angle", imu.getAngle());
		SmartDashboard.putNumber("Gyro-X", imu.getAngleX());
		SmartDashboard.putNumber("Gyro-Y", imu.getAngleY());
		SmartDashboard.putNumber("Gyro-Z", imu.getAngleZ());

		SmartDashboard.putNumber("Pressure: ", imu.getBarometricPressure());
		SmartDashboard.putNumber("Temperature: ", imu.getTemperature());
		
//		SmartDashboard.putNumber("Left Sonar Distance: ", RobotMap.leftSonar.getValue());
//		SmartDashboard.putNumber("Right Sonar Distance: ", RobotMap.rightSonar.getValue());
		
		SmartDashboard.putNumber("Left Speed", leftDrive.get());
		SmartDashboard.putNumber("Right Speed", rightDrive.get());
		
		SmartDashboard.putNumber("Left Encoder", RobotMap.leftEncoder.get());
		SmartDashboard.putNumber("Right Encoder", RobotMap.rightEncoder.get());
	}
}
