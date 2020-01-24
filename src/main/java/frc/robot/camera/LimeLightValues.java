package frc.robot.camera;

public class LimeLightValues {
    public double tx;
    public double ty;
    public double tv;
    public double ts;
    public double ta;

    public boolean hasTarget(){
        return tv == 1;
    }

    public double getTargetHorizontal(){
        return tx;
    }

    public double getTargetVertical(){
        return ty;
    }

    public double getTargetSkew(){
        return ts;
    }
}