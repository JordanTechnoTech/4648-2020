package frc.robot.subsystem;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.Gains;
import frc.robot.RobotMap;

public class ShooterSubsystem extends SubsystemBase implements TechnoTechSubsystem {

    public WPI_TalonSRX shooterTalonSRX;
    public Solenoid intakeGate;


    public ShooterSubsystem(WPI_TalonSRX shooterTalonSRX, Solenoid intakeGate) {
        addChild("intakeGate", RobotMap.intakeGate);
        this.shooterTalonSRX = shooterTalonSRX;
        this.intakeGate = intakeGate;
        
        this.shooterTalonSRX.configFactoryDefault();
        this.shooterTalonSRX.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, 0, 0);
        this.shooterTalonSRX.setSensorPhase(true);
    }

    public void shoot(Double shooterSpeed) {
        double kP = SmartDashboard.getNumber("Shooter P", 1.55);
        double kI = SmartDashboard.getNumber("Shooter I", 0.000001);
        double kD = SmartDashboard.getNumber("Shooter D", 0.6);
        double kF = SmartDashboard.getNumber("Shooter FF", 0.0577);

        Gains kGains_Velocit = new Gains( kP, kI, kD, kF,  0,  1.00);

        this.shooterTalonSRX.configNominalOutputForward(0, Constants.kTimeoutMs);
		this.shooterTalonSRX.configNominalOutputReverse(0, Constants.kTimeoutMs);
		this.shooterTalonSRX.configPeakOutputForward(1, Constants.kTimeoutMs);
		this.shooterTalonSRX.configPeakOutputReverse(-1, Constants.kTimeoutMs);
		/* Config the Velocity closed loop gains in slot0 */
		this.shooterTalonSRX.config_kF(Constants.kPIDLoopIdx, kGains_Velocit.kF, Constants.kTimeoutMs);
		this.shooterTalonSRX.config_kP(Constants.kPIDLoopIdx, kGains_Velocit.kP, Constants.kTimeoutMs);
		this.shooterTalonSRX.config_kI(Constants.kPIDLoopIdx, kGains_Velocit.kI, Constants.kTimeoutMs);
		this.shooterTalonSRX.config_kD(Constants.kPIDLoopIdx, kGains_Velocit.kD, Constants.kTimeoutMs);
        

        shooterTalonSRX.set(ControlMode.Velocity, shooterSpeed);
    }

    public void stop(){
        shooterTalonSRX.set(ControlMode.PercentOutput, 0.0);
    }

    public void gate(boolean state) {
        intakeGate.set(state);
    }


    @Override
	public void log() {
        SmartDashboard.putNumber("Shooter Wheel sensor velocity", shooterTalonSRX.getSelectedSensorVelocity());
        SmartDashboard.putNumber("Shooter Ouput", shooterTalonSRX.getMotorOutputPercent());
    }
}