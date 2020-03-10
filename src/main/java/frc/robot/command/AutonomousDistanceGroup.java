package frc.robot.command;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.RobotMap;

public class AutonomousDistanceGroup extends SequentialCommandGroup {
    
    public AutonomousDistanceGroup() {
        addRequirements(RobotMap.driveSubsystem);
        
        addCommands(new ManualShootCommandGroup(), new DriveDistanceCommand(-2.5), new Turn(-155),
                parallel(new DriveDistanceCommand(15), new BallStorageCommand(true, 0.5)));
    }

    @Override
    public void execute() {
        super.execute();
    }

    @Override
    public void end(boolean interrupted) {
        RobotMap.driveSubsystem.arcadeDrive(0, 0);
        new BallStorageCommand(false, 0);
    }
}