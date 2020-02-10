package frc.robot.subsystem;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.RobotMap;

public class ShooterSubsystem extends SubsystemBase implements TechnoTechSubsystem {

	private VictorSPX leftIntake;
	private VictorSPX rightIntake;
	private VictorSPX leftIntakeBelt;
	private VictorSPX rightIntakeBelt;
    private Solenoid intakeGate;
    private TalonSRX shooterTalonSRX;

    private double speed = 0.6;
    private double shooterSpeed = 0.8;


    public ShooterSubsystem(VictorSPX leftIntake, VictorSPX rightIntake, VictorSPX leftIntakeBelt, VictorSPX rightIntakeBelt, Solenoid intakeGate, TalonSRX shooterTalonSRX) {
        //addChild("leftIntake", RobotMap.leftIntake);
        //addChild("rightIntake", RobotMap.rightIntake);
        //addChild("leftIntakeBelt", RobotMap.leftIntakeBelt);
        //addChild("rightIntakeBelt", RobotMap.rightIntakeBelt);
        addChild("intakeGate", RobotMap.intakeGate);
        //addChild("shooterTalonSRX", RobotMap.shooterTalonSRX);
        this.leftIntake = leftIntake;
        this.rightIntake = rightIntake;
        this.leftIntakeBelt = leftIntakeBelt;
        this.rightIntakeBelt = rightIntakeBelt;
        this.intakeGate = intakeGate;
        this.shooterTalonSRX = shooterTalonSRX;
        this.shooterTalonSRX.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, 10);
    }

    public void shoot() {
            shooterTalonSRX.set(ControlMode.PercentOutput, shooterSpeed);
            intakeGate.set(true);
            leftIntakeBelt.set(ControlMode.PercentOutput, speed);
            rightIntakeBelt.set(ControlMode.PercentOutput, speed);
            leftIntake.set(ControlMode.PercentOutput, speed);
            rightIntake.set(ControlMode.PercentOutput, speed);

    }

    public void stop(){
        shooterTalonSRX.set(ControlMode.PercentOutput, 0.0);
        intakeGate.set(false);
        leftIntakeBelt.set(ControlMode.PercentOutput, 0.0);
        rightIntakeBelt.set(ControlMode.PercentOutput, 0.0);
        leftIntake.set(ControlMode.PercentOutput, 0.0);
        rightIntake.set(ControlMode.PercentOutput, 0.0);
    }


    @Override
	public void log() {
        SmartDashboard.putNumber("Left Intake Wheel", leftIntake.getMotorOutputPercent());
        SmartDashboard.putNumber("RIght Intake Wheel", rightIntake.getMotorOutputPercent());
        SmartDashboard.putNumber("Left Storage Belt", leftIntakeBelt.getMotorOutputPercent());
        SmartDashboard.putNumber("Right Storage Belt", rightIntakeBelt.getMotorOutputPercent());
        SmartDashboard.putNumber("Shooter Wheel", shooterTalonSRX.getMotorOutputPercent());
        SmartDashboard.putNumber("Shooter Wheel sensor velocity", shooterTalonSRX.getSelectedSensorVelocity());
        SmartDashboard.putBoolean("Pneumatic Gate", intakeGate.get());
    }
}