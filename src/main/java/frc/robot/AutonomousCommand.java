package frc.robot;

import com.ctre.phoenix.motorcontrol.ControlMode;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class AutonomousCommand extends CommandBase{

    private double distance;

    public AutonomousCommand() {
        addRequirements(RobotMap.driveSubsystem);
        distance = SmartDashboard.getNumber("Auto Distance", 1);
    }

    @Override
    public void execute() {
        RobotMap.driveSubsystem.driveDistance(distance);
    }

    public void end() {
        RobotMap.driveSubsystem.frontleftDrive.set(ControlMode.PercentOutput, 0.0);
        RobotMap.driveSubsystem.frontrightDrive.set(ControlMode.PercentOutput, 0.0);
    }
}
