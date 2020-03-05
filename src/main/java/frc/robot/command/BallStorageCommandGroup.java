package frc.robot.command;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;

public class BallStorageCommandGroup extends SequentialCommandGroup {

    public BallStorageCommandGroup() {
        addCommands(new StorageCommand(true), new BallStorageCommand(true, 0.5));
    }

    @Override
    public void execute() {
        super.execute();
    }
    

    @Override  
    public void end(boolean interrupted) {
        new StorageCommand(false).execute();
        new BallStorageCommand(false, 0).execute();
    }
}