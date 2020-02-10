package frc.robot.subsystem;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.RobotMap;

public class BallStorageSubsystem extends SubsystemBase implements TechnoTechSubsystem {
    private Talon roller;
	private VictorSPX leftIntake;
	private VictorSPX rightIntake;
	private VictorSPX leftIntakeBelt;
	private VictorSPX rightIntakeBelt;
    private Solenoid intakeGate;
    private Solenoid leftIntakePiston;
    private Solenoid rightIntakePiston;

    private double speed = 0.5;

    public BallStorageSubsystem(Talon roller, VictorSPX leftIntake, VictorSPX rightIntake, VictorSPX leftIntakeBelt, VictorSPX rightIntakeBelt, Solenoid intakeGate, Solenoid leftIntakePiston, Solenoid rightIntakePison) {
        addChild("Roller", RobotMap.roller);
        //addChild("leftIntake", RobotMap.leftIntake);
        //addChild("rightIntake", RobotMap.rightIntake);
        //addChild("leftIntakeBelt", RobotMap.leftIntakeBelt);
        //addChild("rightIntakeBelt", RobotMap.rightIntakeBelt);
        addChild("intakeGate", RobotMap.intakeGate);
        this.roller = roller;
        this.leftIntake = leftIntake;
        this.rightIntake = rightIntake;
        this.leftIntakeBelt = leftIntakeBelt;
        this.rightIntakeBelt = rightIntakeBelt;
        this.intakeGate = intakeGate;
        this.leftIntakePiston = leftIntakePiston;
        this.rightIntakePiston = rightIntakePison;
        
    }

    public void intake() {
            intakeGate.set(false);

            roller.set(speed);
            leftIntake.set(ControlMode.PercentOutput, speed);
            rightIntake.set(ControlMode.PercentOutput, speed);

            leftIntakeBelt.set(ControlMode.PercentOutput, speed);
            rightIntakeBelt.set(ControlMode.PercentOutput, speed);

           
    }

    public void stop(){
        roller.set(0);
        leftIntake.set(ControlMode.PercentOutput, 0);
        rightIntake.set(ControlMode.PercentOutput, 0);

        leftIntakeBelt.set(ControlMode.PercentOutput, 0);
        rightIntakeBelt.set(ControlMode.PercentOutput, 0);
        intakeGate.set(false);
    }

    public void intakePneumatics() {
        leftIntakePiston.set(false);
        rightIntakePiston.set(false);
    }

    public void log() {
        SmartDashboard.putNumber("Intake Roller", roller.getSpeed());
        SmartDashboard.putNumber("Left Intake Wheel", leftIntake.getMotorOutputPercent());
        SmartDashboard.putNumber("RIght Intake Wheel", rightIntake.getMotorOutputPercent());
        SmartDashboard.putNumber("Left Storage Belt", leftIntakeBelt.getMotorOutputPercent());
        SmartDashboard.putNumber("Right Storage Belt", rightIntakeBelt.getMotorOutputPercent());
        SmartDashboard.putBoolean("Pneumatic Gate", intakeGate.get());
    }
}
