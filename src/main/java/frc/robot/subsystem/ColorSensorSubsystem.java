package frc.robot.subsystem;

import java.util.Arrays;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.revrobotics.ColorSensorV3;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.util.Color;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class ColorSensorSubsystem extends SubsystemBase implements TechnoTechSubsystem {
    private ColorSensorV3 colorSensor;
    private Solenoid colorSensorSolenoid;
    public WPI_TalonSRX colorWheelMotor;

    private Color detectedColor;
    private Color[] colors = {Color.kRed, Color.kYellow, Color.kBlue, Color.kGreen};
    private int ID = 0;
    private Color oldColor = Color.kWhite;
    private Color newColor = Color.kWhite;
    private int changes = 0;

    public ColorSensorSubsystem(ColorSensorV3 colorSensor, Solenoid colorSensorSolenoid, WPI_TalonSRX colorWheelMotor) {
        addChild("Solenoid", colorSensorSolenoid);
        this.colorSensor = colorSensor;
        this.colorSensorSolenoid = colorSensorSolenoid;
        this.colorWheelMotor = colorWheelMotor;
    }

    public void spinWheel(double distance) {
        if(changes < distance * 8) {
            colorWheelMotor.set(ControlMode.PercentOutput, 0.75);
        }else {
            colorWheelMotor.set(ControlMode.PercentOutput, 0.0);
        }
    }

    public Color goToColor(Color color) {
        Color sensColor = colors[ID + 2];
        if(sensColor != newColor) {
            colorWheelMotor.set(ControlMode.PercentOutput, 0.25);
        }else {
            colorWheelMotor.set(ControlMode.PercentOutput, 0);
        }
        return sensColor;
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
            ID = 0;
            return Color.kRed;
        }
        if(maxValue == values[1] && values[0] < 0.3 && values[2] < 0.3) {
            SmartDashboard.putString("Color", "GREEN");
            ID = 3;
            return Color.kGreen;
        }
        if(maxValue == values[1] && values[2] >= 0.3) {
            SmartDashboard.putString("Color", "BLUE");
            ID = 2;
            return Color.kBlue;
        }
        if(maxValue == values[1] && values[0] >= 0.3) {
            SmartDashboard.putString("Color", "YELLOW");
            ID = 1;
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