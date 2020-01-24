package frc.robot;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.buttons.Button;


public class DPadButton extends Button {
    private XboxController joystick;
    private Direction direction;

    public DPadButton(XboxController joystick, Direction direction) {
        this.joystick = joystick;
        this.direction = direction;
    }

    @Override
    public boolean get() {
        int degree = joystick.getPOV();

        return degree == direction.degree;
    }
//Sets the DPad up so that each direction corresponds to an angle
    public enum Direction {
        Up (0),
        Down (180),
        Left (270),
        Right (90);

        int degree;
        Direction(int degree) {
            this.degree = degree;
        }
    }
}