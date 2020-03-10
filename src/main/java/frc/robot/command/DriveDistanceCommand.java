package frc.robot.command;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotMap;

public class DriveDistanceCommand extends CommandBase {

    private double distance;
    private double feet;
    private double time = 0;

    public DriveDistanceCommand(double distance) {
        addRequirements(RobotMap.driveSubsystem);
        this.distance = distance;
    }
    
    @Override
    public void initialize() {
        RobotMap.driveSubsystem.resetEncoders();
        RobotMap.driveSubsystem.configureTalonSRX2(false);
        feet = 100000 / 8.9 * distance; 
        super.initialize();
    }

    @Override
    public void execute() {
        //RobotMap.driveSubsystem.driveDistance(distance, distance);
        RobotMap.driveSubsystem.driveDistance(feet, feet);

        if(Math.abs(feet + RobotMap.driveSubsystem.backleftDrive.getSelectedSensorPosition()) <= 800) {
            if(time == 0) {
                time = System.currentTimeMillis();
            }
        }

        super.execute();
    }

    @Override
    public boolean isFinished() {
        if(time == 0) {
            return false;
        }
        else {
            return time + 300 < System.currentTimeMillis();
        }
    }

    @Override
    public void end(boolean interrupted) {
        RobotMap.driveSubsystem.arcadeDrive(0, 0);
        time = 0;
    }
}