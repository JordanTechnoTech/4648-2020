package frc.robot.command;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotMap;

public class CounterResetCommand extends CommandBase {

    public CounterResetCommand() {
        addRequirements(RobotMap.colorSensorSubsystem);
    }
    
    @Override
    public void execute() {
        RobotMap.colorSensorSubsystem.resetCounter();
    }
}
