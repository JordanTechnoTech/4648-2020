package frc.robot.command;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotMap;

public class AutoDriveCommand extends CommandBase {

    private double forwardSpeed;
    private double turnSpeed;

    public AutoDriveCommand(double forwardSpeed, double turnSpeed) {
        addRequirements(RobotMap.driveSubsystem);
        this.forwardSpeed = forwardSpeed;
        this.turnSpeed = turnSpeed;
    }

    @Override
    public void execute() {
        RobotMap.driveSubsystem.arcadeDrive(-forwardSpeed, -turnSpeed);
    }

     @Override
     public boolean isFinished() {
         
         return true;
     }
}