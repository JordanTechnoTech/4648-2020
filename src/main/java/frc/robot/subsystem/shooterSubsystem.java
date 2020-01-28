package frc.robot.subsystem;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class shooterSubsystem extends Subsystem implements TechnoTechSubsystem {

    private TalonSRX shooterTalon;

    public shooterSubsystem(TalonSRX talonSRX) {
        this.shooterTalon = talonSRX;
    }

    
    public void initDefaultCommand() {

    }

    public void setSpeed(double speed) {
        shooterTalon.set(ControlMode.PercentOutput, speed);
    }

    
    @Override
    public void log() {
        SmartDashboard.putNumber("TalonSRX", shooterTalon.getMotorOutputPercent());
    }
}