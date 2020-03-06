package frc.robot.subsystem;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class BallStorageSubsystem extends SubsystemBase implements TechnoTechSubsystem {
	public Talon intake;
    public VictorSPX intakeBelts;
    public VictorSPX intakeBelts2;
    public Solenoid intakeGate;

    public BallStorageSubsystem(Talon intake, VictorSPX intakeBelts, VictorSPX intakeBelts2, Solenoid intakeGate) {
        this.intake = intake;
        this.intakeBelts2 = intakeBelts2;
        this.intakeBelts = intakeBelts;
        this.intakeGate = intakeGate;
        
    }

    public void intake(double beltSpeed) {
        intake.set(beltSpeed * 0.45f);

    }

    public void gate(boolean state) {
        intakeGate.set(state);
    }

    public void belts(double speed) {
        intakeBelts.set(ControlMode.PercentOutput, speed * 0.45f);
        intakeBelts2.set(ControlMode.PercentOutput, speed * -0.45f);
    }

    public void stop(){
        intake.set(0);
        intakeBelts.set(ControlMode.PercentOutput, 0);
        intakeBelts2.set(ControlMode.PercentOutput, 0);
    }

    public void log() {
        //SmartDashboard.putNumber("Left Intake Wheel", intake.getMotorOutputPercent());
        SmartDashboard.putNumber("Left Storage Belt", intakeBelts.getMotorOutputPercent());
    }
}
