package frc.robot.command;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotMap;

public class ColorSensorCommand extends CommandBase {

    public ColorSensorCommand() {
        addRequirements(RobotMap.colorSensorSubsystem);
    }

    @Override
    public void execute() {
        RobotMap.colorSensorSubsystem.rotate(RobotMap.oi.controller0.getXButtonValue(), 3);
        RobotMap.colorSensorSubsystem.extend(RobotMap.oi.controller0.getLeftBumperValue());
        
    }
}