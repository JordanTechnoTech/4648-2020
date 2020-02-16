package frc.robot.command;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;

public class ShootCommandGroup extends SequentialCommandGroup {

    public ShootCommandGroup() {
        addCommands(
        new FaceOffCommand(FaceOffCommand.Target.TOP_OUTER_HOLE), new SmartDashboardCommand("Shoot Command Group", "Shooter Wheel"), new ShootCommand(true),    //faceoff && wheel on
        new WaitCommand(1), new SmartDashboardCommand("Shoot Command Group", "Intake Belts on"), new BallStorageCommand(true),    //belts on
        new WaitCommand(1), new SmartDashboardCommand("Shoot Command Group", "Gate off"), new StorageCommand(true),     //gate off and 1st Ball
        new WaitCommand(1), new SmartDashboardCommand("Shoot Command Group", "Intake Belts off"), new BallStorageCommand(false),
        new WaitCommand(1), new SmartDashboardCommand("Shoot Command Group", "Intake Belts on"), new BallStorageCommand(true),  //2nd Ball
        new WaitCommand(1), new SmartDashboardCommand("Shoot Command Group", "Intake Belts off"), new BallStorageCommand(false),
        new WaitCommand(1), new SmartDashboardCommand("Shoot Command Group", "Intake Belts on"), new BallStorageCommand(true),  //3rd Ball
        new WaitCommand(1), new SmartDashboardCommand("Shoot Command Group", "Intake Belts off"), new BallStorageCommand(false),
        new WaitCommand(1), new SmartDashboardCommand("Shoot Command Group", "Intake Belts on"), new BallStorageCommand(true),  //4th Ball
        new WaitCommand(1), new SmartDashboardCommand("Shoot Command Group", "Intake Belts off"), new BallStorageCommand(false),
        new WaitCommand(1), new SmartDashboardCommand("Shoot Command Group", "Intake Belts on"), new BallStorageCommand(true),  //5th Ball
        new WaitCommand(1), new SmartDashboardCommand("Shoot Command Group", "Intake Belts off"), new BallStorageCommand(false));
    }

    @Override
    public void execute() {
        super.execute();
    }

    @Override
    public void end(boolean interrupted) {
        SmartDashboard.putString("Shoot Command Group", "finished");
        new StorageCommand(false).execute();
        new ShootCommand(false).execute();
    }
}