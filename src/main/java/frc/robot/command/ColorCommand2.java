package frc.robot.command;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.util.Color;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotMap;

public class ColorCommand2 extends CommandBase {

    private Color color;

    public ColorCommand2() {
        addRequirements(RobotMap.colorSensorSubsystem);
    }

    @Override
    public void initialize() {
        
        color = (Color)SmartDashboard.getData("Color");
        super.initialize();
    }

    @Override
    public void execute() {
        RobotMap.colorSensorSubsystem.goToColor(color);
    }

    @Override
    public boolean isFinished() {
        if(RobotMap.colorSensorSubsystem.goToColor(color) == color) {
            return true;
        }
        return false;
    }
}