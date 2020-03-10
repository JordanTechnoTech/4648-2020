package frc.robot.command;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotMap;

public class RaiseHook extends CommandBase {

    public RaiseHook() {
        addRequirements(RobotMap.hookSubsystem);
    }

    @Override
    public void execute() {
        RobotMap.hookSubsystem.hook(RobotMap.controller1.getStickRightYValue());
    }

    @Override
    public boolean isFinished() {
        return false;
    }
}