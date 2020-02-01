package frc.robot.subsystem;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.Sendable;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class BallStorageSubsystem extends SubsystemBase implements TechnoTechSubsystem {
    private Talon Roller;
    private Talon leftIntake;
    private Talon rightIntake;
    private TalonSRX leftIntakeBelt;
    private TalonSRX rightIntakeBelt;

    private Solenoid intakeGate = new Solenoid(0);

    private double speed = 0.5;

    public BallStorageSubsystem() {
        addChild("Roller", (Talon) Roller);
        addChild("leftIntake", (Talon) leftIntake);
        addChild("rightIntake", (Talon) rightIntake);
        addChild("leftIntakeBelt", (Sendable) leftIntakeBelt);
        addChild("rightIntakeBelt", (Sendable) rightIntakeBelt);
    }

    public void intake(boolean intakeState) {
        if (intakeState == true) {
            intakeGate.set(true);   //closes the gate

            Roller.set(speed);
            leftIntake.set(speed);
            rightIntake.set(speed);

            leftIntakeBelt.set(ControlMode.Velocity, speed);
            rightIntakeBelt.set(ControlMode.Velocity, speed);
        }
        if(intakeState == false) {
            intakeGate.set(true);   //closes the gate

            Roller.set(0);
            leftIntake.set(0);
            rightIntake.set(0);

            leftIntakeBelt.set(ControlMode.Velocity, 0);
            rightIntakeBelt.set(ControlMode.Velocity, 0);
        }
    }

    public void log() {
        SmartDashboard.putNumber("Intake Roller", Roller.getSpeed());
        SmartDashboard.putNumber("Left Intake Wheel", leftIntake.getSpeed());
        SmartDashboard.putNumber("RIght Intake Wheel", rightIntake.getSpeed());
        SmartDashboard.putNumber("Left Storage Belt", leftIntakeBelt.getMotorOutputPercent());
        SmartDashboard.putNumber("Right Storage Belt", rightIntakeBelt.getMotorOutputPercent());
    }
}
