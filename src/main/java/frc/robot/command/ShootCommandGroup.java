package frc.robot.command;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.command.FaceOffCommand.Target;

public class ShootCommandGroup extends SequentialCommandGroup {

    public ShootCommandGroup() {

        addCommands(new BallStorageCommand(true, -0.25), new WaitCommand(0.25), new BallStorageCommand(true, 0), 
        new FaceOffCommand(Target.TOP_OUTER_HOLE), new ShootCommand(true), new WaitCommand(2), new StorageCommand(true), new BallStorageCommand(true, 0.35),
        new WaitCommand(20));
    }

    @Override
    public void execute() {
        super.execute();
    }
    

    @Override  
    public void end(boolean interrupted) {
        new BallStorageCommand(false, 0).execute();
        new ShootCommand(false).execute();
        new StorageCommand(false).execute();
        SmartDashboard.putString("ShootCommandGroup", "finished");
    }
}