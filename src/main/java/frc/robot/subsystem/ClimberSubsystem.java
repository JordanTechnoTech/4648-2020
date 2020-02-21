package frc.robot.subsystem;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class ClimberSubsystem extends SubsystemBase implements TechnoTechSubsystem {

	WPI_TalonSRX climberSRX;
	WPI_TalonSRX hookMotor;

	public ClimberSubsystem(WPI_TalonSRX climberSRX, WPI_TalonSRX hookMotor) {
		this.climberSRX = climberSRX;
		this.hookMotor = hookMotor;
	}

	public void hook(double speed) {
		hookMotor.set(ControlMode.PercentOutput, speed);
	}

	public void climb(double speed) {
		climberSRX.set(ControlMode.PercentOutput, speed);
	}


	@Override
	public void log() {
		
	}
    
}