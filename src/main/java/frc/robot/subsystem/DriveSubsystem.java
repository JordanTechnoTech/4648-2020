/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystem;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.Solenoid;
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
	private Solenoid driveShifter;

	public DriveSubsystem(WPI_TalonSRX frontleftDrive, WPI_TalonSRX frontrightDrive, WPI_TalonSRX backleftDrive, WPI_TalonSRX backrightDrive, Solenoid driveShifter) {
		addChild("Front Left CIM", frontleftDrive);
		addChild("Front Right CIM", frontrightDrive);
		addChild("Back Left CIM", backleftDrive);
		addChild("Back Right CIM", backrightDrive);
		differentialDrive1.setSafetyEnabled(false);
		differentialDrive1.setExpiration(0.1);
		differentialDrive1.setMaxOutput(1.0);
		this.frontleftDrive = frontleftDrive;
		this.frontrightDrive = frontrightDrive;
		this.backleftDrive = backleftDrive;
		this.backrightDrive = backrightDrive;
		this.driveShifter = driveShifter;
		
	}

	public void tankDrive(double leftSpeed, double rightSpeed) {
		differentialDrive1.tankDrive(leftSpeed, rightSpeed);
	}

	public void arcadeDrive(double forwardSpeed, double rotationSpeed) {
		differentialDrive1.arcadeDrive(forwardSpeed, rotationSpeed);
	}

	public void changeGear(boolean state) {
		driveShifter.set(state);
	}

	public void log() {	
		SmartDashboard.putNumber("Front Left Speed", frontleftDrive.getMotorOutputPercent());
		SmartDashboard.putNumber("Front Right Speed", frontrightDrive.getMotorOutputPercent());
		SmartDashboard.putNumber("Back Left Speed", backleftDrive.getMotorOutputPercent());
		SmartDashboard.putNumber("Back Right Speed", backrightDrive.getMotorOutputPercent());
		SmartDashboard.putBoolean("Shifter Gear", driveShifter.get());
		
		SmartDashboard.putNumber("Left Encoder", backleftDrive.getSelectedSensorVelocity());
		SmartDashboard.putNumber("Right Encoder", backrightDrive.getSelectedSensorVelocity());
	}
}
