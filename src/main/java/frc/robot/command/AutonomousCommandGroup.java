package frc.robot.command;

import com.ctre.phoenix.motorcontrol.ControlMode;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.RobotMap;

public class AutonomousCommandGroup extends SequentialCommandGroup{

    public AutonomousCommandGroup() {
        addRequirements(RobotMap.driveSubsystem);

        addCommands(
            new ShootCommandGroup(),
            new AutoDriveCommand(-0.7, -.1), new WaitCommand(0.75), new AutoDriveCommand(0, 0)
        );
    }

    @Override
    public void execute() {
        super.execute();
    }

    public void end() {
        RobotMap.driveSubsystem.frontleftDrive.set(ControlMode.PercentOutput, 0.0);
        RobotMap.driveSubsystem.frontrightDrive.set(ControlMode.PercentOutput, 0.0);
        SmartDashboard.putBoolean("Auto command", false);
    }
}