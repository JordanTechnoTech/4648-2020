package frc.robot.command;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotMap;

public class IntakeCommand extends CommandBase {

    public IntakeCommand() {
        addRequirements(RobotMap.ballStorageSubsystem);
    }
    
    @Override
    public void execute() {
        RobotMap.ballStorageSubsystem.intakePneumatics();
    }
}