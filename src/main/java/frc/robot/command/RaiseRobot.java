package frc.robot.command;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotMap;

public class RaiseRobot extends CommandBase {

    public RaiseRobot() {
        addRequirements(RobotMap.climberSubsystem);
    }

    @Override
    public void execute() {
        RobotMap.climberSubsystem.climb(1);
    }

    @Override
    public void end(boolean interrupted) {
        RobotMap.climberSubsystem.climb(0);
    }
}