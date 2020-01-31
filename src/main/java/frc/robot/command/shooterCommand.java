package frc.robot.command;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.RobotMap;

public class shooterCommand extends Command {

    private double speed;

    public shooterCommand(double speed) {
        this.speed = speed;
        requires(RobotMap.shooterSubsystem);
    }

    @Override
    public void execute() {
        RobotMap.shooterSubsystem.setSpeed(speed);
    }

    @Override
    protected boolean isFinished() {
        return false;
    }

    @Override
    public void end() {
        RobotMap.shooterSubsystem.setSpeed(0);
    }
}