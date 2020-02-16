package frc.robot.command;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;

public class ShootCommandGroup extends SequentialCommandGroup {

    public ShootCommandGroup() {
        sequence(new ShootCommand(), new FaceOffCommand(FaceOffCommand.Target.TOP_OUTER_HOLE));
    }
}