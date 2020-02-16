package frc.robot.subsystem;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

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
    private Solenoid leftIntakePiston;
    private Solenoid rightIntakePiston;
    private Solenoid intakeGate;

    private double speed = 0.5;

    public BallStorageSubsystem(Talon roller, VictorSPX leftIntake, VictorSPX rightIntake, VictorSPX leftIntakeBelt, VictorSPX rightIntakeBelt, Solenoid leftIntakePiston, Solenoid rightIntakePison, Solenoid intakeGate) {
        addChild("Roller", RobotMap.roller);
        this.roller = roller;
        this.leftIntake = leftIntake;
        this.rightIntake = rightIntake;
        this.leftIntakeBelt = leftIntakeBelt;
        this.rightIntakeBelt = rightIntakeBelt;
        this.leftIntakePiston = leftIntakePiston;
        this.rightIntakePiston = rightIntakePison;
        this.intakeGate = intakeGate;
        
    }

    public void intake(double beltSpeed) {
        roller.set(speed);
        leftIntake.set(ControlMode.PercentOutput, beltSpeed * 0.5f);
        rightIntake.set(ControlMode.PercentOutput, beltSpeed * 0.5f);

        leftIntakeBelt.set(ControlMode.PercentOutput, beltSpeed * 0.5f);
        rightIntakeBelt.set(ControlMode.PercentOutput, beltSpeed * 0.5f);

        intakeGate.set(RobotMap.controller1.startButton.get());
    }

    public void gate(boolean state) {
        intakeGate.set(state);
    }

    public void stop(){
        roller.set(0);
        leftIntake.set(ControlMode.PercentOutput, 0);
        rightIntake.set(ControlMode.PercentOutput, 0);

        leftIntakeBelt.set(ControlMode.PercentOutput, 0);
        rightIntakeBelt.set(ControlMode.PercentOutput, 0);
    }

    public void intakePneumatics(boolean state) {
        leftIntakePiston.set(state);
        rightIntakePiston.set(state);
    }

    public void log() {
        SmartDashboard.putNumber("Intake Roller", roller.getSpeed());
        SmartDashboard.putNumber("Left Intake Wheel", leftIntake.getMotorOutputPercent());
        SmartDashboard.putNumber("RIght Intake Wheel", rightIntake.getMotorOutputPercent());
        SmartDashboard.putNumber("Left Storage Belt", leftIntakeBelt.getMotorOutputPercent());
        SmartDashboard.putNumber("Right Storage Belt", rightIntakeBelt.getMotorOutputPercent());
    }
}
