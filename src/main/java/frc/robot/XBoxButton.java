package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;

public class XBoxButton extends edu.wpi.first.wpilibj.buttons.Button {
  public static final int kBumperLeft = 5;
  public static final int kBumperRight = 6;
  public static final int kStickLeft = 9;
  public static final int kStickRight = 10;
  public static final int kA = 1;
  public static final int kB = 2;
  public static final int kX = 3;
  public static final int kY = 4;
  public static final int kBack = 7;
  public static final int kStart = 8;

  private final GenericHID m_controller;
  private final int m_buttonNumber;

  public XBoxButton(GenericHID controller, int buttonNumber) {
    m_controller = controller;
    m_buttonNumber = buttonNumber;
  }

  @Override
  public boolean get() {
    return m_controller.getRawButton(m_buttonNumber);
  }
}