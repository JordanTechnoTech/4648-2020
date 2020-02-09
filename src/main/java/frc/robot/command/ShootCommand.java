package frc.robot.command;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotMap;

public class ShootCommand extends CommandBase{
    
    public ShootCommand() {
        addRequirements(RobotMap.shooterSubsystem);
    }

    @Override
    public void execute() {
        RobotMap.shooterSubsystem.shoot();
        SmartDashboard.putString("Shoot Command state","execute");
    }

    @Override
    public void end(boolean interrupted) {
        RobotMap.shooterSubsystem.stop();
        SmartDashboard.putString("Shoot Command state","end");
    }
}
