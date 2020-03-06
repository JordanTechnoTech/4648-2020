package frc.robot.command;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;

public class ManualShootCommandGroup extends SequentialCommandGroup {

    public ManualShootCommandGroup() {
        addCommands(new ShootCommand(true, false),
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
        new ShootCommand(false, false).execute();
        SmartDashboard.putString("ShootCommandGroup", "finished");
    }
}