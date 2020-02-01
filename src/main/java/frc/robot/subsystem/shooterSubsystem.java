package frc.robot.subsystem;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.subsystem.ShooterSubsystem;

public class ShooterSubsystem extends SubsystemBase implements TechnoTechSubsystem {

    private TalonSRX shooterTalon;

    public ShooterSubsystem(TalonSRX talonSRX) {
        this.shooterTalon = talonSRX;
    }

    
    public void initDefaultCommand() {
        
    }

    public void setSpeed(double speed) {
        shooterTalon.set(ControlMode.Velocity, speed * 500.0 * 4096 / 600);
    }

    
    @Override
    public void log() {
        SmartDashboard.putNumber("TalonSRX", shooterTalon.getMotorOutputPercent());
    }
}