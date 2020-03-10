package frc.robot;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.revrobotics.ColorSensorV3;

import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.command.AutonomousDistanceGroup;
import frc.robot.command.BallStorageCommand;
import frc.robot.command.ColorCommand;
import frc.robot.command.ColorSensorCommand;
import frc.robot.command.DriveDistanceCommand;
import frc.robot.command.IntakeBeltCommand;
import frc.robot.command.ManualShootCommandGroup;
import frc.robot.command.RaiseRobot;
import frc.robot.command.ShootCommandGroup;
import frc.robot.command.Turn;
import frc.robot.subsystem.BallStorageSubsystem;
import frc.robot.subsystem.ClimberSubsystem;
import frc.robot.subsystem.ColorSensorSubsystem;
import frc.robot.subsystem.DriveSubsystem;
import frc.robot.subsystem.HookSubsystem;
import frc.robot.subsystem.ShooterSubsystem;

public class RobotMap {
	public static final TechnoTechXBoxController controller0 = new TechnoTechXBoxController(2);
	public static final TechnoTechXBoxController controller1 = new TechnoTechXBoxController(3);
	public static final Joystick leftJoystick = new Joystick(0);
	public static final Joystick rightJoystick = new Joystick(1);
	public static final JoystickButton 
	leftbutton1 = new JoystickButton(leftJoystick, 1), 
	leftbutton2 = new JoystickButton(leftJoystick, 2),
	leftbutton3 = new JoystickButton(leftJoystick, 3),
	leftbutton4 = new JoystickButton(leftJoystick, 4),
	leftbutton5 = new JoystickButton(leftJoystick, 5),
	
	rightbutton1 = new JoystickButton(rightJoystick, 1),
	rightbutton2 = new JoystickButton(rightJoystick, 2),
	rightbutton3 = new JoystickButton(rightJoystick, 3),
	rightbutton4 = new JoystickButton(rightJoystick, 4),
	rightbutton5 = new JoystickButton(rightJoystick, 5),
	rightbutton8 = new JoystickButton(rightJoystick, 8),
	rightbutton9 = new JoystickButton(rightJoystick, 9);

	
	

	//TALON
	public static int frontleftDriveMotor = 1;
	public static int frontrightDriveMotor = 2;
	public static int backleftDriveMotor = 3;
	public static int backrightDriveMotor = 4;
	public static int shooterID = 7;
	public static int colorWheelMotorID = 6;
	

	//VICTOR
	public static int intakeBeltsID = 1;
	public static int intakeBelts2ID = 2;
	public static int hookMotorID = 4;
	public static int climberMotorID = 3;
	
	//pwm mappings
	public static int intakegateID = 2;
	public static int colorWheelSolenoidID = 0;
	public static int leftIntakeID = 3;
	public static int rightIntakeID = 4;

	//Drivetrain Subsystem
	public static WPI_TalonSRX frontLeftMotorController;
	public static WPI_TalonSRX frontRightMotorController;
	public static WPI_TalonSRX backLeftMotorController;
	public static WPI_TalonSRX backRightMotorController;
	public static Solenoid driveShifter;

	public static DifferentialDrive drivetrain;
	public static DriveSubsystem driveSubsystem;

	//Ball Storage Subsystem
	public static BallStorageSubsystem ballStorageSubsystem;
	public static Talon intake;
	public static VictorSPX intakeBelts;
	public static VictorSPX intakeBelts2;
	public static Solenoid intakeGate;

	//Shooter Subsystem
	public static ShooterSubsystem shooterSubsystem;
	public static WPI_TalonSRX shooterTalonSRX;

	//Color Sensor Subsystem
	public static ColorSensorV3 colorSensor;
	public static WPI_TalonSRX colorWheelMotor;
	public static Solenoid colorSensorSolenoid;
	public static ColorSensorSubsystem colorSensorSubsystem;

	//climber subsystem
	public static VictorSPX climberSRX;
	public static VictorSPX hookMotor;
	public static ClimberSubsystem climberSubsystem;
	public static HookSubsystem hookSubsystem;

	public static void init() {
		// drive initialization
		frontLeftMotorController = new WPI_TalonSRX(frontleftDriveMotor);
		frontRightMotorController = new WPI_TalonSRX(frontrightDriveMotor);
		backLeftMotorController = new WPI_TalonSRX(backleftDriveMotor);
		backRightMotorController = new WPI_TalonSRX(backrightDriveMotor);
		frontLeftMotorController.set(ControlMode.Follower, backleftDriveMotor);
		frontRightMotorController.set(ControlMode.Follower, backrightDriveMotor);
		
		frontLeftMotorController.setInverted(false);
		backLeftMotorController.setInverted(true);
		
		//driveShifter = new Solenoid(driveShifterID);
		drivetrain = new DifferentialDrive(backLeftMotorController, backRightMotorController);
		driveSubsystem = new DriveSubsystem(frontLeftMotorController, frontRightMotorController, backLeftMotorController, backRightMotorController, driveShifter);
		
		//intake initialization
		//intake = new VictorSPX(intakeSPXID);
		intakeBelts = new VictorSPX(intakeBeltsID);
		intakeBelts2 = new VictorSPX(intakeBelts2ID);
		intakeGate = new Solenoid(intakegateID);
		shooterTalonSRX = new WPI_TalonSRX(shooterID);
		intake = new Talon(0);
		ballStorageSubsystem = new BallStorageSubsystem(intake, intakeBelts, intakeBelts2);
		shooterSubsystem = new ShooterSubsystem(shooterTalonSRX, intakeGate);
		
		//color sensor initialization
		colorSensor = new ColorSensorV3(I2C.Port.kOnboard);
		colorWheelMotor = new WPI_TalonSRX(colorWheelMotorID);
		colorSensorSolenoid = new Solenoid(colorWheelSolenoidID);
		colorSensorSubsystem = new ColorSensorSubsystem(colorSensor, colorSensorSolenoid, colorWheelMotor);

		//climber init
		climberSRX = new VictorSPX(climberMotorID);
		hookMotor = new VictorSPX(hookMotorID);
		climberSubsystem = new ClimberSubsystem(climberSRX);
		hookSubsystem = new HookSubsystem(hookMotor);
	
		buttonbinding();
		buttonbinding2(false);
	}
	public static void buttonbinding() {
		controller1.dpadUpButton.whileHeld(new RaiseRobot(1));
		controller1.dpadDownButton.whileHeld(new RaiseRobot(-0.4));
	}

	public static void buttonbinding2(boolean state) {
		if(state) {
			controller0.rbButton.toggleWhenPressed(new ColorSensorCommand());	//toggle colorsensor piston

			controller0.bButton.whenPressed(new BallStorageCommand(false, 0));
			controller0.aButton.whenPressed(new BallStorageCommand(true, 0.5));
			controller0.yButton.toggleWhenPressed(new ShootCommandGroup());
			controller0.xButton.toggleWhenPressed(new ColorCommand());
			
			controller0.dpadUpButton.whileHeld(new IntakeBeltCommand(true, false));
			controller0.dpadDownButton.whileHeld(new IntakeBeltCommand(false, true));
		}else {
			rightbutton3.whenPressed(new BallStorageCommand(true, 0.5));
			rightbutton2.whenPressed(new BallStorageCommand(false, 0));
			rightbutton5.toggleWhenPressed(new ColorSensorCommand());
			rightbutton4.toggleWhenPressed(new ColorCommand());
			rightbutton1.toggleWhenPressed(new ShootCommandGroup());

			leftbutton3.whileHeld(new IntakeBeltCommand(true, false));
			leftbutton2.whileHeld(new IntakeBeltCommand(false, true));
			leftbutton1.toggleWhenPressed(new ManualShootCommandGroup());

			rightbutton9.whenPressed(new AutonomousDistanceGroup());
			rightbutton8.whenPressed(new Turn(360));
		}
	}
}