package frc.robot;

import frc.robot.command.shooterCommand;

public class OI {

    public final TechnoTechXBoxController controller0 = new TechnoTechXBoxController(0);

    public OI() {
        controller0.xButton.whenActive(new shooterCommand(1));
        controller0.xButton.whenInactive(new shooterCommand(0));
    }
}