package frc.robot.command;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotMap;

public class DriveDistanceCommand extends CommandBase {

    private double distance;

    public DriveDistanceCommand(double distance) {
        addRequirements(RobotMap.driveSubsystem);
        this.distance = distance;
    }
    
    @Override
    public void initialize() {
        RobotMap.driveSubsystem.resetEncoders();
        super.initialize();
    }

    @Override
    public void execute() {
        RobotMap.driveSubsystem.driveDistance(distance);
    }

    @Override
    public boolean isFinished() {
        return false;
        //return Math.abs(distance + RobotMap.driveSubsystem.backleftDrive.getSelectedSensorPosition()) <= 100;
    }

    @Override
    public void end(boolean interrupted) {
        RobotMap.driveSubsystem.arcadeDrive(0, 0);
    }
}