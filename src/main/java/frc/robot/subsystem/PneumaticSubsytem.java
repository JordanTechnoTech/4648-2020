package frc.robot.subsystem;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class PneumaticSubsytem extends SubsystemBase {
    
    public PneumaticSubsytem(Solenoid solenoid1, Solenoid solenoid2, Solenoid solenoid3) {
        addChild("Solenoid 1", solenoid1);
        addChild("Solenoid 2", solenoid2);
        addChild("Solenoid 3", solenoid3);
    }

    public void execute() {
        
    }


}