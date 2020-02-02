package frc.robot.command;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotMap;

public class BallStorageCommand extends CommandBase {

    public BallStorageCommand() {
        addRequirements(RobotMap.ballStorageSubsystem);
    }

    @Override
    public void execute() {
        //RobotMap.ballStorageSubsystem.intake(RobotMap.oi.controller0.getAButtonValue());
    }
}