/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystem;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.Gains;
import frc.robot.RobotMap;

public class DriveSubsystem extends SubsystemBase implements TechnoTechSubsystem {
	// Put methods for controlling this subsystem
	// here. Call these from Commands.
	
	protected final DifferentialDrive differentialDrive1 = RobotMap.drivetrain;
	public WPI_TalonSRX frontleftDrive;
	public WPI_TalonSRX frontrightDrive;
	public WPI_TalonSRX backleftDrive;
	public WPI_TalonSRX backrightDrive;
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

		configureTalonSRX(this.frontleftDrive);
		configureTalonSRX(this.frontrightDrive);
		configureTalonSRX(this.backleftDrive);
		configureTalonSRX(this.backrightDrive);

		this.backleftDrive.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, 0, 0);
		this.backrightDrive.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, 0, 0);

		frontleftDrive.setInverted(false);
		backleftDrive.setInverted(true);

		frontleftDrive.set(ControlMode.Follower, 3);
		frontrightDrive.set(ControlMode.Follower, 4);

	}

	public void configureTalonSRX(WPI_TalonSRX srx) {
		//srx.configFactoryDefault();
        srx.setSensorPhase(true);
		
		double kP = SmartDashboard.getNumber("Drive P", 0.25);
        double kI = SmartDashboard.getNumber("Drive I", 0);
        double kD = SmartDashboard.getNumber("Drive D", 0);

        Gains kGains_Velocit = new Gains( kP, kI, kD, 1023.0/7200.0,  300,  1.00);
		
		srx.configNominalOutputForward(0, Constants.kTimeoutMs);
		srx.configNominalOutputReverse(0, Constants.kTimeoutMs);
		srx.configPeakOutputForward(1, Constants.kTimeoutMs);
		srx.configPeakOutputReverse(-1, Constants.kTimeoutMs);
		/* Config the Velocity closed loop gains in slot0 */
		srx.config_kF(Constants.kPIDLoopIdx, kGains_Velocit.kF, Constants.kTimeoutMs);
		srx.config_kP(Constants.kPIDLoopIdx, kGains_Velocit.kP, Constants.kTimeoutMs);
		srx.config_kI(Constants.kPIDLoopIdx, kGains_Velocit.kI, Constants.kTimeoutMs);
		srx.config_kD(Constants.kPIDLoopIdx, kGains_Velocit.kD, Constants.kTimeoutMs);

		srx.setNeutralMode(NeutralMode.Brake);
	}

	public void tankDrive(double leftSpeed, double rightSpeed) {
		differentialDrive1.tankDrive(leftSpeed, rightSpeed);
	}

	public void arcadeDrive(double forwardSpeed, double rotationSpeed) {
		differentialDrive1.arcadeDrive(forwardSpeed, rotationSpeed);
	}

	public void driveDistance(double distance) {
		//backleftDrive.set(ControlMode.PercentOutput, -0.1);
		this.backleftDrive.setSelectedSensorPosition(0, Constants.kPIDLoopIdx, Constants.kTimeoutMs);
		//this.frontleftDrive.setSelectedSensorPosition(0, Constants.kPIDLoopIdx, Constants.kTimeoutMs);
		this.backrightDrive.setSelectedSensorPosition(0, Constants.kPIDLoopIdx, Constants.kTimeoutMs);
		//this.frontrightDrive.setSelectedSensorPosition(0, Constants.kPIDLoopIdx, Constants.kTimeoutMs);
		backrightDrive.set(ControlMode.Position, distance);
		backleftDrive.set(ControlMode.Position, distance);
	}

	public void changeGear(boolean state) {
		//driveShifter.set(state);
	}

	public void log() {	
		SmartDashboard.putNumber("Front Left Speed", frontleftDrive.getMotorOutputPercent());
		SmartDashboard.putNumber("Front Right Speed", frontrightDrive.getMotorOutputPercent());
		SmartDashboard.putNumber("Back Left Speed", backleftDrive.getMotorOutputPercent());
		SmartDashboard.putNumber("Back Right Speed", backrightDrive.getMotorOutputPercent());
		//SmartDashboard.putBoolean("Shifter Gear", driveShifter.get());
		
		SmartDashboard.putNumber("Left Encoder", backleftDrive.getSelectedSensorVelocity());
		SmartDashboard.putNumber("Right Encoder", backrightDrive.getSelectedSensorVelocity());
	}
}
