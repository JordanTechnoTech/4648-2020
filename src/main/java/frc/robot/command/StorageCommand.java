package frc.robot.command;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotMap;

public class StorageCommand extends CommandBase {

    public StorageCommand() {
        addRequirements(RobotMap.ballStorageSubsystem);
    }

    @Override
    public void execute() {
        RobotMap.ballStorageSubsystem.intakePneumatics(true);
    }

    @Override
    public void end(boolean interrupted) {
        RobotMap.ballStorageSubsystem.intakePneumatics(false);
    }
}