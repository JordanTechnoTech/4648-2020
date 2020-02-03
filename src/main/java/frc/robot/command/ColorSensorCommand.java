package frc.robot.command;

import com.ctre.phoenix.motorcontrol.ControlMode;

import edu.wpi.first.wpilibj.util.Color;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotMap;

public class ColorSensorCommand extends CommandBase {

    private Color oldColor = Color.kWhite;
    private Color newColor = Color.kWhite;
    private int changes = 0;
    private int rotations;
    private boolean state = false;
    

    public ColorSensorCommand(boolean state, int rotations) {
        addRequirements(RobotMap.colorSensorSubsystem);
        this.rotations = rotations;
    }

    @Override
    public void execute() {
        if(state) {
            while(changes * 8 <= rotations) {
                newColor = RobotMap.colorSensorSubsystem.getColor();
                RobotMap.colorSensorSubsystem.colorWheelMotor.set(ControlMode.Velocity, 0.25);

                //check if color change has occurred
                if(oldColor != newColor) {
                    changes = changes + 1;
                }
                oldColor = newColor;
            }
        }
    }
}