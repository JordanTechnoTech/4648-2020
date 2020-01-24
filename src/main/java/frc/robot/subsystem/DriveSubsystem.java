/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystem;

import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.RobotMap;
import frc.robot.command.DriveCommand;

public class DriveSubsystem extends Subsystem implements TechnoTechSubsystem {
	// Put methods for controlling this subsystem
	// here. Call these from Commands.
	
	protected final DifferentialDrive differentialDrive1 = RobotMap.drivetrain;
	private Talon leftDrive;
	private Talon rightDrive;

	public DriveSubsystem(Talon leftDrive, Talon rightDrive) {
		addChild("Left CIM", (Talon) leftDrive);
		addChild("Right CIM", (Talon) rightDrive);
		differentialDrive1.setSafetyEnabled(false);
		differentialDrive1.setExpiration(0.1);
		differentialDrive1.setMaxOutput(1.0);
		this.leftDrive = leftDrive;
		this.rightDrive = rightDrive;
	}

	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		//setDefaultCommand(new DriveCommand());
		setDefaultCommand(new DriveCommand());
	}

	public void tankDrive(double leftSpeed, double rightSpeed) {
		differentialDrive1.tankDrive(leftSpeed, rightSpeed);
	}

	public void arcadeDrive(double forwardSpeed, double rotationSpeed) {
		differentialDrive1.arcadeDrive(forwardSpeed, rotationSpeed);
	}

	public void log() {	
		SmartDashboard.putNumber("Left Speed", leftDrive.get());
		SmartDashboard.putNumber("Right Speed", rightDrive.get());
		
		//SmartDashboard.putNumber("Left Encoder", RobotMap.leftEncoder.get());
		//SmartDashboard.putNumber("Right Encoder", RobotMap.rightEncoder.get());
	}
}
