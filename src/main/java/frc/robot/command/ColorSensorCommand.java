package frc.robot.command;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotMap;

public class ColorSensorCommand extends CommandBase {

    public ColorSensorCommand() {
        addRequirements(RobotMap.colorSensorSubsystem);
    }

    @Override
    public void execute() {
        RobotMap.colorSensorSubsystem.raise(true);
    }

    @Override
    public void end(boolean interrupted) {
        RobotMap.colorSensorSubsystem.raise(false);
    }
}