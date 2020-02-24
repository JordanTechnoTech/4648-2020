package frc.robot.command;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotMap;

public class IntakeBeltCommand extends CommandBase {

    private double beltSpeed;

    public IntakeBeltCommand() {
        //addRequirements(RobotMap.ballStorageSubsystem);
    }

    @Override
    public void execute() {
        if(RobotMap.controller0.dpadUpButton.get()) {
            beltSpeed = 0.5;
        }
        if(RobotMap.controller0.dpadDownButton.get()) {
            beltSpeed = -0.5;
        }else {
            beltSpeed = 0;
        }

        RobotMap.ballStorageSubsystem.belts(beltSpeed);
    }

    @Override
    public boolean isFinished() {
        return false;
    }
}