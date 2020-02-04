package frc.robot.command;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotMap;


public class PneumaticCommand extends CommandBase {
    private boolean toggleState;

    public PneumaticCommand() {
        addRequirements(RobotMap.pneumaticSubsytem);
    }

    @Override
    public void execute() {
        if(RobotMap.oi.controller0.getXButtonPressed()) {
            if(toggleState) {
                toggleState = false;
            }
            else {
                toggleState = true;
            }
        }
        
        RobotMap.pneumaticSubsytem.execute(toggleState);
    }

    @Override
    public boolean isFinished() {
        return false;
    }

}