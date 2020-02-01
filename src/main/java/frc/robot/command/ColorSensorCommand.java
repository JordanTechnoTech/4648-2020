package frc.robot.command;

import edu.wpi.first.wpilibj.util.Color;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotMap;

public class ColorSensorCommand extends CommandBase {

    private Color oldColor = Color.kWhite;
    private Color newColor = Color.kWhite;
    private int changes = 0;
    
    private int rotations;

    public ColorSensorCommand(int rotations) {
        addRequirements(RobotMap.colorSensorSubsystem);
        this.rotations = rotations;
    }

    @Override
    public void execute() {
        newColor = RobotMap.colorSensorSubsystem.getColor();

        //check if color change has occurred
        if(oldColor != newColor) {
            changes = changes + 1;
        }
        oldColor = newColor;
    }
}