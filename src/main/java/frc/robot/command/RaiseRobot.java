package frc.robot.command;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotMap;

public class RaiseRobot extends CommandBase {
    private double speed;

    public RaiseRobot(double speed) {
        addRequirements(RobotMap.climberSubsystem);
        this.speed = speed;
    }

    @Override
    public void execute() {
        RobotMap.climberSubsystem.climb(speed);
    }

    @Override
    public void end(boolean interrupted) {
        RobotMap.climberSubsystem.climb(0);
    }
}