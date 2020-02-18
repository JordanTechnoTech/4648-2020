package frc.robot.command;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotMap;

public class ShootCommand extends CommandBase{
    private double speed;
    private boolean state;


    public ShootCommand(boolean state) {
        addRequirements(RobotMap.shooterSubsystem);
        this.state = state;
    }

    @Override
    public void execute() {
        if(state) {
            //speed = (SmartDashboard.getNumber("distance", 50) * 3) + 7100;
            speed = SmartDashboard.getNumber("Test Speed", 8000);
            RobotMap.shooterSubsystem.shoot(speed);
        }else {
            RobotMap.shooterSubsystem.stop();
        }
        SmartDashboard.putString("Shoot Command state","execute");
            SmartDashboard.putNumber("Shooter Target Speed", speed);
    }

    @Override
    public boolean isFinished() {
        return true;
    }


}
