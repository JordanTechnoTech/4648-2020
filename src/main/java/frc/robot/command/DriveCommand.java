package frc.robot.command;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotMap;

public class DriveCommand extends CommandBase {
    public DriveCommand() {
        addRequirements(RobotMap.driveSubsystem);
    }

    @Override
    public String getName(){
        return "DriveCommand";
    }

    @Override
    public void execute() {
        log();
        //Left stick only for driving
        //RobotMap.driveSubsystem.arcadeDrive(RobotMap.controller0.getStickLeftYValue(), -RobotMap.controller0.getStickLeftXValue() * 0.7f);
        //for stick + triggers
        //RobotMap.driveSubsystem.arcadeDrive(-RobotMap.controller0.getRightTriggerValue() + RobotMap.controller0.getLeftTriggerValue(), - RobotMap.controller0.getStickLeftXValue() * 0.6f);

        //for joysticks and controller
        RobotMap.driveSubsystem.arcadeDrive(RobotMap.leftJoystick.getY(), -RobotMap.rightJoystick.getX());
    }

    @Override
    public boolean isFinished() {
        SmartDashboard.putString("DriveCommandStatus", "finished");
        return false;
    }
    
    public void end() {
        SmartDashboard.putString("DriveCommandStatus", "end");
        RobotMap.driveSubsystem.arcadeDrive(0, 0);
    }

    public void log() {
        SmartDashboard.putNumber("Left Stick X", RobotMap.controller0.getStickLeftXValue());
        SmartDashboard.putNumber("Left Stick Y", RobotMap.controller0.getStickLeftYValue());
    }
}