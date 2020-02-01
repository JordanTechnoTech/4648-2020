package frc.robot.command;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotMap;

public class PneumaticCommand extends CommandBase {

    private Solenoid solenoid;

    public PneumaticCommand( Solenoid Solenoid) {
        addRequirements(RobotMap.pneumaticSubsytem);
        this.solenoid = solenoid;
    }

    @Override
    public void execute() {
        solenoid.set(true);
    }

    @Override
    public boolean isFinished() {
        return false;
    }

    public void end() {
        solenoid.set(false);
    }
}