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
        super.initialize();
    }

    @Override
    public void execute() {
        RobotMap.colorSensorSubsystem.spinWheel(3);
    }

    @Override
    public void end(boolean interrupted) {
        RobotMap.colorSensorSubsystem.spinWheel(0);
    }
}