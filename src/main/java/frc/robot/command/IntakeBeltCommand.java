package frc.robot.command;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotMap;

public class IntakeBeltCommand extends CommandBase {

    private boolean button1;
    private boolean button2;

    public IntakeBeltCommand(boolean button1, boolean button2) {
        addRequirements(RobotMap.ballStorageSubsystem);

        this.button1 = button1;
        this.button2 = button2;
    }

    @Override
    public void execute() {
        if(button1 && !button2) {
            RobotMap.ballStorageSubsystem.belts(0.5);
        }
        if(!button1 && button2) {
            RobotMap.ballStorageSubsystem.belts(-0.5);
        }
    }

    @Override
    public void end(boolean interrupted) {
        RobotMap.ballStorageSubsystem.belts(0);
    }

    @Override
    public boolean isFinished() {
        return false;
    }
}