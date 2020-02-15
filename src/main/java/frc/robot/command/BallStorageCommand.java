package frc.robot.command;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotMap;

public class BallStorageCommand extends CommandBase {

    public BallStorageCommand() {
        addRequirements(RobotMap.ballStorageSubsystem);
    }

    @Override
    public void execute() {
        RobotMap.ballStorageSubsystem.intake(RobotMap.controller1.getRightTriggerValue() - RobotMap.controller1.getLeftTriggerValue());
        SmartDashboard.putString("BallStorageCommand state","execute");
    }

    @Override
    public void end(boolean interrupted) {
        RobotMap.ballStorageSubsystem.stop();
        SmartDashboard.putString("BallStorageCommand state","end");
    }

}