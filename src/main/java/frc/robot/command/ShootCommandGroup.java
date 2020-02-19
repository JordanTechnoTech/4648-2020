package frc.robot.command;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;

public class ShootCommandGroup extends SequentialCommandGroup {

    public ShootCommandGroup() {
        addCommands(
        new FaceOffCommand(FaceOffCommand.Target.TOP_OUTER_HOLE), new SmartDashboardCommand("Shoot Command Group", "Shooter Wheel"), new ShootCommand(true),    //faceoff && wheel on
        new WaitCommand(1), new SmartDashboardCommand("Shoot Command Group", "Intake Belts on"), new BallStorageCommand(true, 0.5),    //belts on
        new WaitCommand(0.7), new SmartDashboardCommand("Shoot Command Group", "Gate off"), new StorageCommand(true),     //gate open and 1st Ball
        new WaitCommand(0.5), new SmartDashboardCommand("Shoot Command Group", "Gate on"), new StorageCommand(false),
        new WaitCommand(1), new SmartDashboardCommand("Shoot Command Group", "Gate off"), new StorageCommand(true),     //gate open and 2nd Ball
        new WaitCommand(0.5), new SmartDashboardCommand("Shoot Command Group", "Gate on"), new StorageCommand(false),
        new WaitCommand(1), new SmartDashboardCommand("Shoot Command Group", "Gate off"), new StorageCommand(true),     //gate open and 3rd Ball
        new WaitCommand(0.5), new SmartDashboardCommand("Shoot Command Group", "Gate on"), new StorageCommand(false),
        new WaitCommand(1.1), new SmartDashboardCommand("Shoot Command Group", "Gate off"), new StorageCommand(true),     //gate open and 4th Ball
        new WaitCommand(0.5), new SmartDashboardCommand("Shoot Command Group", "Gate on"), new StorageCommand(false),
        new BallStorageCommand(false, 0), new ShootCommand(false));
    }

    @Override
    public void execute() {
        super.execute();
    }

    @Override
    public void end(boolean interrupted) {
        SmartDashboard.putString("Shoot Command Group", "finished");
    }
}