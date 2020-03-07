package frc.robot.subsystem;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class HookSubsystem extends SubsystemBase implements TechnoTechSubsystem {

    public VictorSPX hookMotor;

    public HookSubsystem(VictorSPX hookMotor) {
		this.hookMotor = hookMotor;
    }
    
    public void hook(double speed) {
		hookMotor.set(ControlMode.PercentOutput, speed);
	}

    @Override
    public void log() {
        SmartDashboard.putNumber("Hook", hookMotor.getMotorOutputPercent());
    }
    
}