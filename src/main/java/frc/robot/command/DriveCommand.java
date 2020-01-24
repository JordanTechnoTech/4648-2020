package frc.robot.command;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.RobotMap;

public class DriveCommand extends Command {
    public DriveCommand() {
        requires(RobotMap.driveSubsystem);
    }

    @Override
    protected void execute() {
        RobotMap.driveSubsystem.arcadeDrive(RobotMap.oi.controller0.getStickRightXValue(), RobotMap.oi.controller0.getStickRightYValue());
    }

    @Override
    protected boolean isFinished() {
        return false;
    }

    @Override
    public void end() {
        RobotMap.driveSubsystem.arcadeDrive(0, 0);
    }
}