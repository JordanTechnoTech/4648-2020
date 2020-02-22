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
	private VictorSPX intake;
	private VictorSPX leftIntakeBelt;
	private VictorSPX rightIntakeBelt;
    private Solenoid leftIntakePiston;
    private Solenoid rightIntakePiston;
    private Solenoid intakeGate;

    public BallStorageSubsystem(Talon roller, VictorSPX intake, VictorSPX leftIntakeBelt, VictorSPX rightIntakeBelt, Solenoid leftIntakePiston, Solenoid rightIntakePison, Solenoid intakeGate) {
        addChild("Roller", RobotMap.roller);
        this.roller = roller;
        this.intake = intake;
        this.leftIntakeBelt = leftIntakeBelt;
        this.rightIntakeBelt = rightIntakeBelt;
        this.leftIntakePiston = leftIntakePiston;
        this.rightIntakePiston = rightIntakePison;
        this.intakeGate = intakeGate;
        
    }

    public void intake(double beltSpeed) {
        intake.set(ControlMode.PercentOutput, beltSpeed * 2f);

    }

    public void gate(boolean state) {
        intakeGate.set(state);
    }

    public void belts(double speed) {
        leftIntakeBelt.set(ControlMode.PercentOutput, speed * 0.5f);
        rightIntakeBelt.set(ControlMode.PercentOutput, speed * 0.5f);
    }

    public void stop(){
        roller.set(0);
        intake.set(ControlMode.PercentOutput, 0);

        leftIntakeBelt.set(ControlMode.PercentOutput, 0);
        rightIntakeBelt.set(ControlMode.PercentOutput, 0);
    }

    public void intakePneumatics(boolean state) {
        leftIntakePiston.set(state);
        rightIntakePiston.set(state);
    }

    public void log() {
        SmartDashboard.putNumber("Intake Roller", roller.getSpeed());
        SmartDashboard.putNumber("Left Intake Wheel", intake.getMotorOutputPercent());
        SmartDashboard.putNumber("Left Storage Belt", leftIntakeBelt.getMotorOutputPercent());
        SmartDashboard.putNumber("Right Storage Belt", rightIntakeBelt.getMotorOutputPercent());
    }
}
