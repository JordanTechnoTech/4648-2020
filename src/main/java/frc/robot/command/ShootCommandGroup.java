package frc.robot.command;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.command.FaceOffCommand.Target;

public class ShootCommandGroup extends SequentialCommandGroup {

    public ShootCommandGroup() {

        addCommands(new ShootCommand(true), new BallStorageCommand(true, -0.4), new WaitCommand(0.5), new BallStorageCommand(false, 0), 
        new FaceOffCommand(Target.TOP_OUTER_HOLE), new ShootCommand(true), new WaitCommand(0.5), new StorageCommand(true), new BallStorageCommand(true, 0.4),
        new WaitCommand(10));
    }

    @Override
    public void execute() {
        super.execute();
    }
    

    @Override  
    public void end(boolean interrupted) {
        new StorageCommand(false).execute();
        new BallStorageCommand(false, 0).execute();
        new ShootCommand(false).execute();
        SmartDashboard.putString("ShootCommandGroup", "finished");
    }
}