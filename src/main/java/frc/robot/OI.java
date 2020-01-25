package frc.robot;

import frc.robot.command.shooterCommand;

public class OI {

    public final TechnoTechXBoxController controller0 = new TechnoTechXBoxController(0);

    public OI() {
        controller0.xButton.whenPressed(new shooterCommand(0.25));
        controller0.xButton.whenReleased(new shooterCommand(0));
        
    }
}