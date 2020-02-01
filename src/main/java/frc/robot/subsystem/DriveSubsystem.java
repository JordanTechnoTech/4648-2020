/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystem;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.Sendable;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.RobotMap;

public class DriveSubsystem extends SubsystemBase implements TechnoTechSubsystem {
	// Put methods for controlling this subsystem
	// here. Call these from Commands.
	
	protected final DifferentialDrive differentialDrive1 = RobotMap.drivetrain;
	private WPI_TalonSRX frontleftDrive;
	private WPI_TalonSRX frontrightDrive;
	private WPI_TalonSRX backleftDrive;
	private WPI_TalonSRX backrightDrive;

	public DriveSubsystem(WPI_TalonSRX frontleftDrive, WPI_TalonSRX frontrightDrive, WPI_TalonSRX backleftDrive, WPI_TalonSRX backrightDrive) {
		addChild("Front Left CIM", (Sendable) frontleftDrive);
		addChild("Front Right CIM", (Sendable) frontrightDrive);
		addChild("Back Left CIM", (Sendable) backleftDrive);
		addChild("Back Right CIM", (Sendable) backrightDrive);
		differentialDrive1.setSafetyEnabled(false);
		differentialDrive1.setExpiration(0.1);
		differentialDrive1.setMaxOutput(1.0);
		this.frontleftDrive = frontleftDrive;
		this.frontrightDrive = frontrightDrive;
		this.backleftDrive = backleftDrive;
		this.backrightDrive = backrightDrive;
	}

	/*
	public void initDefaultCommand() {
		SmartDashboard.putString("Drive default set", "YES");
		// Set the default command for a subsystem here.
		setDefaultCommand(new DriveCommand());
	}
	*/

	public void tankDrive(double leftSpeed, double rightSpeed) {
		differentialDrive1.tankDrive(leftSpeed, rightSpeed);
	}

	public void arcadeDrive(double forwardSpeed, double rotationSpeed) {
		differentialDrive1.arcadeDrive(forwardSpeed, rotationSpeed);
	}

	public void log() {	
		SmartDashboard.putNumber("Front Left Speed", frontleftDrive.getMotorOutputPercent());
		SmartDashboard.putNumber("Front Right Speed", frontrightDrive.getMotorOutputPercent());
		SmartDashboard.putNumber("Back Left Speed", backleftDrive.getMotorOutputPercent());
		SmartDashboard.putNumber("Back Right Speed", backrightDrive.getMotorOutputPercent());
		
		//SmartDashboard.putNumber("Left Encoder", RobotMap.leftEncoder.get());
		//SmartDashboard.putNumber("Right Encoder", RobotMap.rightEncoder.get());
	}
}
