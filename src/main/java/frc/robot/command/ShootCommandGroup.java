package frc.robot.command;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandGroupBase;
import frc.robot.command.FaceOffCommand.Target;

public class ShootCommandGroup extends CommandGroupBase {

    public ShootCommandGroup() {
        parallel(new FaceOffCommand(Target.TOP_OUTER_HOLE), new ShootCommand(true, true));
        parallel(new StorageCommand(true), new BallStorageCommand(true, 0.65));
        
    }

    @Override
    public void execute() {
        super.execute();
    }

    @Override
    public void end(boolean interrupted) {
        new StorageCommand(false).execute();
        new BallStorageCommand(false, 0).execute();
        new ShootCommand(false, false).execute();
        SmartDashboard.putString("ShootCommandGroup", "finished");
    }

    @Override
    public void addCommands(Command... commands) {
        

    }
}