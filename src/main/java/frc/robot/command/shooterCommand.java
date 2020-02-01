package frc.robot.command;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotMap;

public class ShooterCommand extends CommandBase {
    public double speed;

    public ShooterCommand(double speed) {
        addRequirements(RobotMap.ShooterSubsystem);
        this.speed = speed;
    }

    @Override
    public void execute() {
        RobotMap.ShooterSubsystem.setSpeed(speed);
    }

    @Override
	public boolean isFinished() {
        return false;
    }

    public void end() {
        
    }
}