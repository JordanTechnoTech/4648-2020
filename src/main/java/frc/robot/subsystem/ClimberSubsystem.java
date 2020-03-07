package frc.robot.subsystem;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class ClimberSubsystem extends SubsystemBase implements TechnoTechSubsystem {

	VictorSPX climberSRX;

	public ClimberSubsystem(VictorSPX climberSRX) {
		this.climberSRX = climberSRX;
	}

	public void climb(double speed) {
		climberSRX.set(ControlMode.PercentOutput, speed);
	}


	@Override
	public void log() {
		
	}
    
}