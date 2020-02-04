package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;

public class TechnoTechXBoxController {
    final int channel;
    public final XBoxButton stickLeft;
    public final XBoxButton stickRight;
    public final XBoxButton rbButton;
    public final XBoxButton lbButton;
    public final XBoxButton yButton;
    public final XBoxButton xButton;
    public final XBoxButton bButton;
    public final XBoxButton aButton;
    public final XBoxButton backButton;
    public final DPadButton dpadUpButton;
    public final DPadButton dpadLeftButton;
    public final DPadButton dpadDownButton;
    public final XBoxButton startButton;
    XboxController controller;

    public TechnoTechXBoxController(int channel) {
        this.channel = channel;
        this.controller = new XboxController(channel);
        this.stickLeft = new XBoxButton(controller, XBoxButton.kStickLeft);
        this.stickRight = new XBoxButton(controller, XBoxButton.kStickRight);
        this.rbButton = new XBoxButton(controller, XBoxButton.kBumperRight);
        this.lbButton = new XBoxButton(controller, XBoxButton.kBumperLeft);
        this.yButton = new XBoxButton(controller, XBoxButton.kY);
        this.aButton = new XBoxButton(controller, XBoxButton.kA);
        this.xButton = new XBoxButton(controller, XBoxButton.kX);
        this.bButton = new XBoxButton(controller, XBoxButton.kB);
        this.backButton = new XBoxButton(controller, XBoxButton.kBack);
        this.startButton = new XBoxButton(controller, XBoxButton.kStart);
        this.dpadUpButton = new DPadButton(controller, DPadButton.Direction.Up);
        this.dpadLeftButton = new DPadButton(controller, DPadButton.Direction.Left);
        this.dpadDownButton = new DPadButton(controller, DPadButton.Direction.Down);
    }

    public double getStickRightYValue(){ return controller.getY(GenericHID.Hand.kRight);}
    public double getStickLeftYValue(){ return controller.getY(GenericHID.Hand.kLeft);}
    public double getStickRightXValue(){ return controller.getX(GenericHID.Hand.kRight);}
    public double getLeftTriggerValue(){ return controller.getTriggerAxis(GenericHID.Hand.kLeft);}
    public double getRightTriggerValue(){ return controller.getTriggerAxis(GenericHID.Hand.kRight);}
    
    public boolean getAButtonValue(){
        return controller.getAButton();
    }
    public boolean getBButtonValue(){
        return controller.getBButton();
    }
    public boolean getXButtonValue(){
        return controller.getXButton();
    }
    public boolean getYButtonValue(){
        return controller.getYButton();
    }

    public boolean getBButtonPressed(){
        return controller.getBButtonPressed();
    }
    public boolean getAButtonPressed(){
        return controller.getAButtonPressed();
    }
    public boolean getXButtonPressed(){
        return controller.getXButtonPressed();
    }
    public boolean getYButtonPressed(){
        return controller.getYButtonPressed();
    }

    public boolean getLeftBumperValue(){
        return controller.getBumper(GenericHID.Hand.kLeft);
    }
    public boolean getRightBumperValue(){
        return controller.getBumper(GenericHID.Hand.kRight);
    }

    public double getStickLeftXValue() {
        return controller.getX(GenericHID.Hand.kLeft);
    }
    
    public static double deadZone (double val, double deadZone){
        if (Math.abs(val) > deadZone){
            if (val > 0){
                return (val - deadZone) / (1 - deadZone);
            } else {
                return -(-val - deadZone) / (1 - deadZone);
            }
        }
        return 0;
    }
}