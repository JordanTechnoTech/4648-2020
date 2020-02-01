package frc.robot;

import frc.robot.command.PneumaticCommand;


public class OI {

    public final TechnoTechXBoxController controller0 = new TechnoTechXBoxController(0);

    public OI() {
        if(controller0.dpadLeftButton.get()) {
            new PneumaticCommand(RobotMap.solenoid1);
        }

        if(controller0.dpadUpButton.get()) {
            new PneumaticCommand(RobotMap.solenoid2);
        }
        if(controller0.dpadDownButton.get()) {
            new PneumaticCommand(RobotMap.solenoid3);
        }
    }
}