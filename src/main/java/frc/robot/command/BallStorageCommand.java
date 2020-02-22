package frc.robot.command;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotMap;

public class BallStorageCommand extends CommandBase {
    private boolean state;
    private double speed;

    public BallStorageCommand(boolean state, double speed) {
        addRequirements(RobotMap.ballStorageSubsystem);
        this.state = state;
        this.speed = speed;
    }

    @Override
    public void execute() {
        if(state) {
            RobotMap.ballStorageSubsystem.intake(speed);
            RobotMap.ballStorageSubsystem.belts(speed);
        }else {
            RobotMap.ballStorageSubsystem.stop();
        }
    }

    @Override
    public boolean isFinished() {
        return true;
    }

}