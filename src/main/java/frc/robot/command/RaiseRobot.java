package frc.robot.command;

import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.RobotMap;

public class RaiseRobot extends CommandBase {

    public RaiseRobot() {
        addRequirements(RobotMap.climberSubsystem);
    }

    @Override
    public void execute() {
        RobotMap.climberSubsystem.climb(0.5);
        new WaitCommand(3).execute();
    }

    @Override
    public void end(boolean interrupted) {
        RobotMap.climberSubsystem.climb(0);
    }
}