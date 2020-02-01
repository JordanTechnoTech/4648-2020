package frc.robot.subsystem;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class PneumaticSubsytem extends SubsystemBase {
    
    public Solenoid solenoid1, solenoid2, solenoid3;  
    public PneumaticSubsytem(Solenoid solenoid1, Solenoid solenoid2, Solenoid solenoid3) {
        addChild("Solenoid 1", solenoid1);
        addChild("Solenoid 2", solenoid2);
        addChild("Solenoid 3", solenoid3);
        this.solenoid1 = solenoid1;
        this.solenoid2 = solenoid2;
        this.solenoid3 = solenoid3;
    }

    public void execute() {
        solenoid1.set (true);
        solenoid2.set (true);
        solenoid3.set (true);
        
    }


}