package frc.robot.subsystem;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.Gains;
import frc.robot.RobotMap;

public class ShooterSubsystem extends SubsystemBase implements TechnoTechSubsystem {

	private VictorSPX leftIntake;
	private VictorSPX rightIntake;
	private VictorSPX leftIntakeBelt;
	private VictorSPX rightIntakeBelt;
    private Solenoid intakeGate;
    private WPI_TalonSRX shooterTalonSRX;

    private double speed = 0.6;
    private double shooterSpeed = 1000;


    public ShooterSubsystem(VictorSPX leftIntake, VictorSPX rightIntake, VictorSPX leftIntakeBelt, VictorSPX rightIntakeBelt, Solenoid intakeGate, WPI_TalonSRX shooterTalonSRX) {
        addChild("intakeGate", RobotMap.intakeGate);
        this.leftIntake = leftIntake;
        this.rightIntake = rightIntake;
        this.leftIntakeBelt = leftIntakeBelt;
        this.rightIntakeBelt = rightIntakeBelt;
        this.intakeGate = intakeGate;
        this.shooterTalonSRX = shooterTalonSRX;
        
        
        this.shooterTalonSRX.configFactoryDefault();
        this.shooterTalonSRX.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, 0, 0);
        this.shooterTalonSRX.setSensorPhase(true);
    }

    public void shoot(Double shooterSpeed) {
        double kP = SmartDashboard.getNumber("P", 2);
        double kI = SmartDashboard.getNumber("I", 0.001);
        double kD = SmartDashboard.getNumber("D", 5);

        Gains kGains_Velocit = new Gains( kP, kI, kD, 1023.0/7200.0,  300,  1.00);

        this.shooterTalonSRX.configNominalOutputForward(0, Constants.kTimeoutMs);
		this.shooterTalonSRX.configNominalOutputReverse(0, Constants.kTimeoutMs);
		this.shooterTalonSRX.configPeakOutputForward(1, Constants.kTimeoutMs);
		this.shooterTalonSRX.configPeakOutputReverse(-1, Constants.kTimeoutMs);
		/* Config the Velocity closed loop gains in slot0 */
		this.shooterTalonSRX.config_kF(Constants.kPIDLoopIdx, kGains_Velocit.kF, Constants.kTimeoutMs);
		this.shooterTalonSRX.config_kP(Constants.kPIDLoopIdx, kGains_Velocit.kP, Constants.kTimeoutMs);
		this.shooterTalonSRX.config_kI(Constants.kPIDLoopIdx, kGains_Velocit.kI, Constants.kTimeoutMs);
		this.shooterTalonSRX.config_kD(Constants.kPIDLoopIdx, kGains_Velocit.kD, Constants.kTimeoutMs);
        
        this.shooterSpeed = shooterSpeed;

        shooterTalonSRX.set(ControlMode.Velocity, shooterSpeed);
        intakeGate.set(true);
        //leftIntakeBelt.set(ControlMode.PercentOutput, speed);
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