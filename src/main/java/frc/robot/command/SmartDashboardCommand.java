package frc.robot.command;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.InstantCommand;

public class SmartDashboardCommand extends InstantCommand {
  /**
   * Creates a new a PrintCommand.
   *
   * @param message the message to print
   */
  public SmartDashboardCommand(String key, String message) {
    super(() -> SmartDashboard.putString(key, message));
  }

  @Override
  public boolean runsWhenDisabled() {
    return true;
  }
}
