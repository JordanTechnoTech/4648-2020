package frc.robot.subsystem;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import edu.wpi.first.wpilibj.Sendable;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.RobotMap;

public class BallStorageSubsystem extends SubsystemBase implements TechnoTechSubsystem {
    private Talon roller;
	private VictorSPX leftIntake;
	private VictorSPX rightIntake;
	private VictorSPX leftIntakeBelt;
	private VictorSPX rightIntakeBelt;
    private Solenoid intakeGate;
    private Solenoid leftIntakePiston;
    private Solenoid rightIntakePiston;

    private double speed = 0.5;

    public BallStorageSubsystem(Talon roller, VictorSPX leftIntake, VictorSPX rightIntake, VictorSPX leftIntakeBelt, VictorSPX rightIntakeBelt, Solenoid intakeGate, Solenoid leftIntakePiston, Solenoid rightIntakePison) {
        addChild("Roller", (Talon) RobotMap.roller);
        addChild("leftIntake", (Sendable) RobotMap.leftIntake);
        addChild("rightIntake", (Sendable) RobotMap.rightIntake);
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
            leftIntake.set(ControlMode.Velocity, speed);
            rightIntake.set(ControlMode.Velocity, speed);

            leftIntakeBelt.set(ControlMode.Velocity, speed);
            rightIntakeBelt.set(ControlMode.Velocity, speed);
            intakeGate.set(true);

            roller.set(0);
            leftIntake.set(ControlMode.Velocity, 0);
            rightIntake.set(ControlMode.Velocity, 0);

            leftIntakeBelt.set(ControlMode.Velocity, 0);
            rightIntakeBelt.set(ControlMode.Velocity, 0);
    }

    public void intakePneumatics() {
        leftIntakePiston.set(false);
        rightIntakePiston.set(false);
    }

    public void log() {
        SmartDashboard.putNumber("Intake Roller", roller.getSpeed());
        SmartDashboard.putNumber("Left Intake Wheel", leftIntake.getMotorOutputPercent());
        SmartDashboard.putNumber("RIght Intake Wheel", rightIntake.getMotorOutputPercent());
        SmartDashboard.putNumber("Left Storage Belt", leftIntakeBelt.getMotorOutputPercent());
        SmartDashboard.putNumber("Right Storage Belt", rightIntakeBelt.getMotorOutputPercent());
        SmartDashboard.putBoolean("Pneumatic Gate", intakeGate.get());
    }
}
