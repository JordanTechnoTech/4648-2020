package frc.robot.command;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotMap;

public class ShootCommand extends CommandBase{
    private double speed;
    private boolean state;
    private boolean state2;


    public ShootCommand(boolean state, boolean state2) {
        addRequirements(RobotMap.shooterSubsystem);
        this.state = state;
    }

    @Override
    public void execute() {
        if(state) {
            double distance = SmartDashboard.getNumber("distance", 50);
            if(state2) { 
                speed = (0.09 * distance * distance) + (3.3 * distance) + 9100;
            }else {
                speed = SmartDashboard.getNumber("Test Speed", 8000);
            }
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
