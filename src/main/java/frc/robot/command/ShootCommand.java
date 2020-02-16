package frc.robot.command;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotMap;

public class ShootCommand extends CommandBase{
    private double speed;


    public ShootCommand() {
        addRequirements(RobotMap.shooterSubsystem);
    }

    @Override
    public void execute() {
        speed = (SmartDashboard.getNumber("distance", 120) * 3) + 7100;
        RobotMap.shooterSubsystem.shoot(speed);
        SmartDashboard.putString("Shoot Command state","execute");
    }

    @Override
    public void end(boolean interrupted) {
        RobotMap.shooterSubsystem.stop();
        SmartDashboard.putString("Shoot Command state","end");
    }
}
