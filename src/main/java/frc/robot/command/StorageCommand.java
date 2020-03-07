package frc.robot.command;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotMap;

public class StorageCommand extends CommandBase {

    private boolean state;

    public StorageCommand(boolean state) {
        addRequirements(RobotMap.shooterSubsystem);
        this.state = state;
    }

    @Override
    public void execute() {
        RobotMap.shooterSubsystem.gate(state);
    }

    @Override
    public boolean isFinished() {
        return true;
    }
}