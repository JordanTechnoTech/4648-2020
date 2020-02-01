package frc.robot.command;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotMap;

public class ShootCommand extends CommandBase{
    
    public ShootCommand() {
        addRequirements(RobotMap.shooterSubsystem);
    }

    @Override
    public void execute() {
        RobotMap.shooterSubsystem.shoot(RobotMap.oi.controller0.getBButtonPressed());
    }
}
