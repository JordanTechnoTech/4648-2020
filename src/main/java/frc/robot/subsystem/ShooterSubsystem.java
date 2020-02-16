package frc.robot.subsystem;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.Gains;
import frc.robot.RobotMap;

public class ShooterSubsystem extends SubsystemBase implements TechnoTechSubsystem {

    private WPI_TalonSRX shooterTalonSRX;

    private double shooterSpeed = 1000;


    public ShooterSubsystem(WPI_TalonSRX shooterTalonSRX) {
        addChild("intakeGate", RobotMap.intakeGate);
        this.shooterTalonSRX = shooterTalonSRX;
        
        
        this.shooterTalonSRX.configFactoryDefault();
        this.shooterTalonSRX.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, 0, 0);
        this.shooterTalonSRX.setSensorPhase(true);
    }

    public void shoot(Double shooterSpeed) {
        double kP = SmartDashboard.getNumber("Shooter P", 1);
        double kI = SmartDashboard.getNumber("Shooter", 0.001);
        double kD = SmartDashboard.getNumber("Shooter", 10);

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
    }

    public void stop(){
        shooterTalonSRX.set(ControlMode.PercentOutput, 0.0);
    }


    @Override
	public void log() {
        SmartDashboard.putNumber("Shooter Wheel sensor velocity", shooterTalonSRX.getSelectedSensorVelocity());
        SmartDashboard.putNumber("Shooter Ouput", shooterTalonSRX.getMotorOutputPercent());
    }
}