package frc.robot.command;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotMap;

public class DriveDistanceCommand extends CommandBase {

    private double distance;
    private double time = 0;

    public DriveDistanceCommand(double distance) {
        addRequirements(RobotMap.driveSubsystem);
        this.distance = distance;
    }
    
    @Override
    public void initialize() {
        RobotMap.driveSubsystem.resetEncoders();
        RobotMap.driveSubsystem.configureTalonSRX2(false);
        super.initialize();
    }

    @Override
    public void execute() {
        RobotMap.driveSubsystem.driveDistance(distance, distance);

        if(Math.abs(distance + RobotMap.driveSubsystem.backleftDrive.getSelectedSensorPosition()) <= 200) {
            if(time != System.currentTimeMillis()) {
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
            return time + 200 < System.currentTimeMillis();
        }
    }

    @Override
    public void end(boolean interrupted) {
        RobotMap.driveSubsystem.arcadeDrive(0, 0);
    }
}