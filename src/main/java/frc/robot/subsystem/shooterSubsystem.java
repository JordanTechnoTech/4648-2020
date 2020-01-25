package frc.robot.subsystem;

import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import edu.wpi.first.wpilibj.command.Subsystem;

public class shooterSubsystem extends Subsystem implements TechnoTechSubsystem {

    private TalonSRX shooterTalon;

    public shooterSubsystem(TalonSRX talonSRX) {
        shooterTalon = talonSRX;
    }

    
    public void initDefaultCommand() {

    }

    public void shoot() {
        
    }
    
    @Override
    public void log() {
            
    }
}