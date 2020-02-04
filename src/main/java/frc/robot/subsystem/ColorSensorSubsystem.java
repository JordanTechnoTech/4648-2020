package frc.robot.subsystem;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.revrobotics.ColorSensorV3;

import edu.wpi.first.wpilibj.Sendable;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.util.Color;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class ColorSensorSubsystem extends SubsystemBase implements TechnoTechSubsystem {
    private ColorSensorV3 colorSensor;
    private Solenoid colorSensorSolenoid;
    public WPI_TalonSRX colorWheelMotor;

    private Color detectedColor;
    private Color oldColor = Color.kWhite;
    private Color newColor = Color.kWhite;
    private int changes = 0;

    public ColorSensorSubsystem(ColorSensorV3 colorSensor, Solenoid colorSensorSolenoid, WPI_TalonSRX colorWheelMotor) {
        addChild("Color Sensor", (Sendable) colorSensor);
        addChild("Solenoid", (Solenoid) colorSensorSolenoid);
        addChild("Color Wheel Motor", (Sendable) colorWheelMotor);
        this.colorSensor = colorSensor;
        this.colorSensorSolenoid = colorSensorSolenoid;
        this.colorWheelMotor = colorWheelMotor;
    }

    public void rotate(boolean state, int rotations) {
        if(state == true) {
            while(changes * 8 <= rotations) {
                newColor = getColor();
                colorWheelMotor.set(ControlMode.Velocity, 0.1);

                //check if color change has occurred
                if(oldColor != newColor) {
                    changes = changes + 1;
                }
                oldColor = newColor;
            }
            colorWheelMotor.set(ControlMode.Velocity, 0);
        }
    }

    public void resetCounter(boolean state) {
        changes = 0;
    }

    public void extend(boolean state) {
        colorSensorSolenoid.set(state);
    }

    public Color getColor() {
        detectedColor  = colorSensor.getColor();
        double[] values = {detectedColor.red, detectedColor.green, detectedColor.blue};

        //check if color is RED
        if (values[0] >= 0.5 && values[1] <= 0.35 && values[2] <= 0.2) {
            return Color.kFirstRed;
        }else 
        //check if color detected is GREEN
        if (values[0] <= 0.2 && values[1]>= 0.4 && values[2] <= 0.3) {
            return Color.kForestGreen;
        }else 
        //check if color detected is BLUE
        if (values[0] <= 0.2 && values[1] >= 0.2 && values[2] >= 0.4) {
            return Color.kBlue;
        }else 
        //check if color detected is YELLOW
        if (values[0] >= 0.3 && values[1] >= 0.4 && values[2] <= 0.2) {
            return Color.kYellow;
        }
        else {
            return Color.kAntiqueWhite;
        }
    }

    @Override
	public void log() {
        SmartDashboard.putNumber("Red", detectedColor.red);
        SmartDashboard.putNumber("Green", detectedColor.green);
        SmartDashboard.putNumber("Blue", detectedColor.blue);
        SmartDashboard.putNumber("IR", colorSensor.getIR());
	}
}