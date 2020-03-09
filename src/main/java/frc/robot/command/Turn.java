package frc.robot.command;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotMap;

public class Turn extends CommandBase {

    private double degrees;
    private double distance;
    private double time = 0;

    public Turn(double degrees) {
        addRequirements(RobotMap.driveSubsystem);
        this.degrees = degrees;
    }

    @Override
    public void initialize() {
        distance = 65000 * (degrees / 360);
        RobotMap.driveSubsystem.resetEncoders();
        super.initialize();

        RobotMap.driveSubsystem.configureTalonSRX2(true);
    }

    @Override
    public void execute() {
        RobotMap.driveSubsystem.driveDistance(distance, -distance);
        
        if(Math.abs(distance + RobotMap.driveSubsystem.backleftDrive.getSelectedSensorPosition()) <= 500) {
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
            return time + 1000 < System.currentTimeMillis();
        }
    }

    @Override
    public void end(boolean interrupted) {
        RobotMap.driveSubsystem.arcadeDrive(0, 0);
    }
}