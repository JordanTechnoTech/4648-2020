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
        //speed = (SmartDashboard.getNumber("Test Speed",8000));
        RobotMap.shooterSubsystem.shoot(speed);
        SmartDashboard.putString("Shoot Command state","execute");
        SmartDashboard.putNumber("Shooter Target Speed",speed);
    }

    @Override
    public void end(boolean interrupted) {
        RobotMap.shooterSubsystem.stop();
        SmartDashboard.putString("Shoot Command state","end");
    }
}
