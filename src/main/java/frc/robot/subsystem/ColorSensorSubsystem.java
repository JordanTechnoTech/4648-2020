package frc.robot.subsystem;

import java.util.Arrays;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import com.revrobotics.ColorSensorV3;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.util.Color;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class ColorSensorSubsystem extends SubsystemBase implements TechnoTechSubsystem {
    private ColorSensorV3 colorSensor;
    private Solenoid colorSensorSolenoid;
    public VictorSPX colorWheelMotor;

    private Color detectedColor;
    private Color oldColor = Color.kWhite;
    private Color newColor = Color.kWhite;
    private int changes = 0;

    public ColorSensorSubsystem(ColorSensorV3 colorSensor, Solenoid colorSensorSolenoid, VictorSPX colorWheelMotor) {
        addChild("Solenoid", colorSensorSolenoid);
        this.colorSensor = colorSensor;
        this.colorSensorSolenoid = colorSensorSolenoid;
        this.colorWheelMotor = colorWheelMotor;
    }

    public void detectChanges() {
            newColor = getColor();

            //check if color change has occurred
            if(oldColor != newColor) {
                changes = changes + 1;
            }
            oldColor = newColor;
    }

    public void raise(boolean state) {
        colorSensorSolenoid.set(state);
    }

    public void resetCounter() {
        changes = 0;
    }

    public Color getColor() {
        detectedColor  = colorSensor.getColor();
        double[] values = {detectedColor.red, detectedColor.green, detectedColor.blue};

        double maxValue = Arrays.stream(values).max().getAsDouble();

        if(maxValue == values[0]) {
            SmartDashboard.putString("Color", "RED");
            return Color.kFirstRed;
        }
        if(maxValue == values[1] && values[0] < 0.3 && values[2] < 0.3) {
            SmartDashboard.putString("Color", "GREEN");
            return Color.kDarkGreen;
        }
        if(maxValue == values[1] && values[2] >= 0.3) {
            SmartDashboard.putString("Color", "BLUE");
            return Color.kBlue;
        }
        if(maxValue == values[1] && values[0] >= 0.3) {
            SmartDashboard.putString("Color", "YELLOW");
            return Color.kYellow;
        }
        else {
            SmartDashboard.putString("Color", "UNKNOWN");
            return Color.kAntiqueWhite;
        }
    }

    @Override
	public void log() {
        if(detectedColor != null) {
            SmartDashboard.putNumber("Red", detectedColor.red);
            SmartDashboard.putNumber("Green", detectedColor.green);
            SmartDashboard.putNumber("Blue", detectedColor.blue);
            SmartDashboard.putNumber("IR", colorSensor.getIR());
        }
        if(colorWheelMotor != null){
            SmartDashboard.putNumber("Color Wheel SPX", colorWheelMotor.getMotorOutputPercent());
    
        }
        SmartDashboard.putNumber("Color Changes", changes);
    }
}