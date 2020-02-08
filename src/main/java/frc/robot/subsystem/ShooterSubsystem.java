package frc.robot.subsystem;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import edu.wpi.first.wpilibj.Sendable;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.RobotMap;

public class ShooterSubsystem extends SubsystemBase implements TechnoTechSubsystem {

	private VictorSPX leftIntake;
	private VictorSPX rightIntake;
	private VictorSPX leftIntakeBelt;
	private VictorSPX rightIntakeBelt;
    private Solenoid intakeGate;
    private TalonSRX shooterTalonSRX;

    private double speed = 0.5;

    public ShooterSubsystem(VictorSPX leftIntake, VictorSPX rightIntake, VictorSPX leftIntakeBelt, VictorSPX rightIntakeBelt, Solenoid intakeGate, TalonSRX shooterTalonSRX) {
        addChild("leftIntake", (Sendable) RobotMap.leftIntake);
        addChild("rightIntake", (Sendable) RobotMap.rightIntake);
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

    public void shoot() {
            shooterTalonSRX.set(ControlMode.Velocity, speed);
            intakeGate.set(false);
            leftIntakeBelt.set(ControlMode.Velocity, speed);
            rightIntakeBelt.set(ControlMode.Velocity, speed);
            leftIntake.set(ControlMode.Velocity, speed);
            rightIntake.set(ControlMode.Velocity, speed);
    }


    @Override
	public void log() {
        SmartDashboard.putNumber("Left Intake Wheel", leftIntake.getMotorOutputPercent());
        SmartDashboard.putNumber("RIght Intake Wheel", rightIntake.getMotorOutputPercent());
        SmartDashboard.putNumber("Left Storage Belt", leftIntakeBelt.getMotorOutputPercent());
        SmartDashboard.putNumber("Right Storage Belt", rightIntakeBelt.getMotorOutputPercent());
        SmartDashboard.putNumber("Shooter Wheel", shooterTalonSRX.getMotorOutputPercent());
        SmartDashboard.putBoolean("Pneumatic Gate", intakeGate.get());
    }
}