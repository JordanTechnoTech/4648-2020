package frc.robot.command;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotMap;

public class ColorCommand extends CommandBase {

    public ColorCommand() {
        addRequirements(RobotMap.colorSensorSubsystem);
    }

    @Override
    public void initialize() {
        RobotMap.colorSensorSubsystem.resetCounter();
        RobotMap.colorSensorSubsystem.raise(true);
        super.initialize();
    }

    @Override
    public void execute() {
        RobotMap.colorSensorSubsystem.spinWheel(3.5);
    }

    @Override
    public void end(boolean interrupted) {
        RobotMap.colorSensorSubsystem.spinWheel(0);
    }
}