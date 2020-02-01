package frc.robot.command;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotMap;

public class shooterCommand extends CommandBase {

    private double speed;

    public shooterCommand(double speed) {
        this.speed = speed;
        addRequirements(RobotMap.shooterSubsystem);
    }

    @Override
    public void execute() {
        RobotMap.shooterSubsystem.setSpeed(speed);
    }

    @Override
    public boolean isFinished() {
        return false;
    }

    public void end() {
        RobotMap.shooterSubsystem.setSpeed(0);
    }
}