package frc.robot.command;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotMap;

public class RaiseHook extends CommandBase {

    public RaiseHook() {
        addRequirements(RobotMap.climberSubsystem);
    }

    @Override
    public void execute() {
        RobotMap.climberSubsystem.hook(RobotMap.controller1.getStickLeftYValue());
    }

    @Override
    public boolean isFinished() {
        return false;
    }
}