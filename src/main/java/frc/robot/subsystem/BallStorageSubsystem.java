package frc.robot.subsystem;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.Sendable;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.RobotMap;

public class BallStorageSubsystem extends SubsystemBase implements TechnoTechSubsystem {
    private Talon roller;
	private Talon leftIntake;
	private Talon rightIntake;
	private TalonSRX leftIntakeBelt;
	private TalonSRX rightIntakeBelt;
    private Solenoid intakeGate;
    private Solenoid leftIntakePiston;
    private Solenoid rightIntakePiston;

    private double speed = 0.5;

    public BallStorageSubsystem(Talon roller, Talon leftIntake, Talon rightIntake, TalonSRX leftIntakeBelt, TalonSRX rightIntakeBelt, Solenoid intakeGate, Solenoid leftIntakePiston, Solenoid rightIntakePison) {
        addChild("Roller", (Talon) RobotMap.roller);
        addChild("leftIntake", (Talon) RobotMap.leftIntake);
        addChild("rightIntake", (Talon) RobotMap.rightIntake);
        addChild("leftIntakeBelt", (Sendable) RobotMap.leftIntakeBelt);
        addChild("rightIntakeBelt", (Sendable) RobotMap.rightIntakeBelt);
        addChild("intakeGate", (Solenoid) RobotMap.intakeGate);
        this.roller = roller;
        this.leftIntake = leftIntake;
        this.rightIntake = rightIntake;
        this.leftIntakeBelt = leftIntakeBelt;
        this.rightIntakeBelt = rightIntakeBelt;
        this.intakeGate = intakeGate;
        this.leftIntakePiston = leftIntakePiston;
        this.rightIntakePiston = rightIntakePison;
        
    }

    public void intake() {
            intakeGate.set(true);

            roller.set(speed);
            leftIntake.set(speed);
            rightIntake.set(speed);

            leftIntakeBelt.set(ControlMode.Velocity, speed);
            rightIntakeBelt.set(ControlMode.Velocity, speed);
            intakeGate.set(true);

            roller.set(0);
            leftIntake.set(0);
            rightIntake.set(0);

            leftIntakeBelt.set(ControlMode.Velocity, 0);
            rightIntakeBelt.set(ControlMode.Velocity, 0);
    }

    public void intakePneumatics() {
        leftIntakePiston.set(true);
        rightIntakePiston.set(true);
    }

    public void log() {
        SmartDashboard.putNumber("Intake Roller", roller.getSpeed());
        SmartDashboard.putNumber("Left Intake Wheel", leftIntake.getSpeed());
        SmartDashboard.putNumber("RIght Intake Wheel", rightIntake.getSpeed());
        SmartDashboard.putNumber("Left Storage Belt", leftIntakeBelt.getMotorOutputPercent());
        SmartDashboard.putNumber("Right Storage Belt", rightIntakeBelt.getMotorOutputPercent());
        SmartDashboard.putBoolean("Pneumatic Gate", intakeGate.get());
    }
}
