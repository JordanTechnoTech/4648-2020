package frc.robot.subsystem;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class BallStorageSubsystem extends SubsystemBase implements TechnoTechSubsystem {
	private VictorSPX intake;
	private VictorSPX intakeBelts;
    private Solenoid intakeGate;

    public BallStorageSubsystem(VictorSPX intake, VictorSPX intakeBelts, Solenoid intakeGate) {
        this.intake = intake;
        this.intakeBelts = intakeBelts;
        this.intakeGate = intakeGate;
        
    }

    public void intake(double beltSpeed) {
        intake.set(ControlMode.PercentOutput, beltSpeed * 0.45f);

    }

    public void gate(boolean state) {
        intakeGate.set(state);
    }

    public void belts(double speed) {
        intakeBelts.set(ControlMode.PercentOutput, speed * 0.45f);
    }

    public void stop(){
        intake.set(ControlMode.PercentOutput, 0);
        intakeBelts.set(ControlMode.PercentOutput, 0);
    }

    public void log() {
        SmartDashboard.putNumber("Left Intake Wheel", intake.getMotorOutputPercent());
        SmartDashboard.putNumber("Left Storage Belt", intakeBelts.getMotorOutputPercent());
    }
}
