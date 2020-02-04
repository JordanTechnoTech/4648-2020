package frc.robot;

import frc.robot.command.BallStorageCommand;
import frc.robot.command.ColorSensorCommand;
import frc.robot.command.IntakeCommand;
import frc.robot.command.ShootCommand;

public class OI {

    public final TechnoTechXBoxController controller0 = new TechnoTechXBoxController(0);

    public OI() {
        controller0.xButton.whenPressed(new ColorSensorCommand());
        controller0.aButton.whenActive(new BallStorageCommand());
        controller0.bButton.whenPressed(new ShootCommand());
        controller0.lbButton.toggleWhenPressed(new IntakeCommand());
    }
}