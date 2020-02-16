package frc.robot.command;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotMap;

public class BallStorageCommand extends CommandBase {
    private boolean state;

    public BallStorageCommand(boolean state) {
        addRequirements(RobotMap.ballStorageSubsystem);
        this.state = state;
    }

    @Override
    public void execute() {
        if(state) {
            RobotMap.ballStorageSubsystem.intake(0.5);
        }else {
            RobotMap.ballStorageSubsystem.stop();
        }
    }

    @Override
    public boolean isFinished() {
        return true;
    }

}