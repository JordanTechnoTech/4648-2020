package frc.robot.subsystem;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.Sendable;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.RobotMap;

public class ShooterSubsystem extends SubsystemBase implements TechnoTechSubsystem {

	private Talon leftIntake;
	private Talon rightIntake;
	private TalonSRX leftIntakeBelt;
	private TalonSRX rightIntakeBelt;
    private Solenoid intakeGate;
    private TalonSRX shooterTalonSRX;

    private double speed = 0.5;

    public ShooterSubsystem(Talon leftIntake, Talon rightIntake, TalonSRX leftIntakeBelt, TalonSRX rightIntakeBelt, Solenoid intakeGate, TalonSRX shooterTalonSRX) {
        addChild("leftIntake", (Talon) RobotMap.leftIntake);
        addChild("rightIntake", (Talon) RobotMap.rightIntake);
        addChild("leftIntakeBelt", (Sendable) RobotMap.leftIntakeBelt);
        addChild("rightIntakeBelt", (Sendable) RobotMap.rightIntakeBelt);
        addChild("intakeGate", (Solenoid) RobotMap.intakeGate);
        addChild("shooterTalonSRX", (Sendable) RobotMap.shooterTalonSRX);
        this.leftIntake = leftIntake;
        this.rightIntake = rightIntake;
        this.leftIntakeBelt = leftIntakeBelt;
        this.rightIntakeBelt = rightIntakeBelt;
        this.intakeGate = intakeGate;
        this.shooterTalonSRX = shooterTalonSRX;
    }

    public void shoot(boolean shootState) {
        if(shootState == true) {
            shooterTalonSRX.set(ControlMode.Velocity, speed);
            intakeGate.set(false);
            leftIntakeBelt.set(ControlMode.Velocity, speed);
            rightIntakeBelt.set(ControlMode.Velocity, speed);
            leftIntake.set(speed);
            rightIntake.set(speed);
        }
        if(shootState == false) {
            shooterTalonSRX.set(ControlMode.Velocity, 0);
            intakeGate.set(true);
            leftIntakeBelt.set(ControlMode.Velocity, 0);
            rightIntakeBelt.set(ControlMode.Velocity, 0);
            leftIntake.set(0);
            rightIntake.set(0);
        }
    }


    @Override
	public void log() {
        SmartDashboard.putNumber("Left Intake Wheel", leftIntake.getSpeed());
        SmartDashboard.putNumber("RIght Intake Wheel", rightIntake.getSpeed());
        SmartDashboard.putNumber("Left Storage Belt", leftIntakeBelt.getMotorOutputPercent());
        SmartDashboard.putNumber("Right Storage Belt", rightIntakeBelt.getMotorOutputPercent());
        SmartDashboard.putNumber("Shooter Wheel", shooterTalonSRX.getMotorOutputPercent());
        SmartDashboard.putBoolean("Pneumatic Gate", intakeGate.get());
    }
}