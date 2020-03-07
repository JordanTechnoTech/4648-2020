package frc.robot.command;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;

public class BallStorageCommandGroup extends SequentialCommandGroup {
    
    private boolean state;

    public BallStorageCommandGroup(boolean state) {
        this.state = state;
        addCommands(new StorageCommand(state), new BallStorageCommand(state, 0.65));

    }

    @Override
    public void execute() {
        super.execute();
    }
    

    @Override  
    public void end(boolean interrupted) {
        //new StorageCommand(false).execute();
        //new BallStorageCommand(false, 0).execute();
    }
}