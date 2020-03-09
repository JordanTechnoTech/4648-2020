package frc.robot.command;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.RobotMap;

public class AutonomousDistanceGroup extends SequentialCommandGroup {
    
    public AutonomousDistanceGroup() {
        addRequirements(RobotMap.driveSubsystem);
        
        addCommands(new DriveDistanceCommand(100000));
    }

    @Override
    public void execute() {
        super.execute();
    }

    @Override
    public void end(boolean interrupted) {
        RobotMap.driveSubsystem.arcadeDrive(0, 0);
    }
}