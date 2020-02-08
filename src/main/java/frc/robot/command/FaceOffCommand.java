package frc.robot.command;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.camera.LimeLightValues;
import frc.robot.camera.LimelightCamera;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class FaceOffCommand extends CommandBase {

    Target target;
    LimelightCamera limelightCamera = new LimelightCamera();
    double cameraFail;
    LimeLightValues limeLightValues;
    private boolean finished = false;

    public FaceOffCommand(Target atarget) {
        this.target = atarget;
    }

    @Override
    public void initialize() {
        finished = false;
        LimelightCamera.setLightMode(LimelightCamera.ledMode.ON);
        LimelightCamera.setPipeline(0);
        LimelightCamera.setCameraMode(LimelightCamera.cameraMode.VISION);

        super.initialize();
    }

    @Override
    public void execute() {
        limeLightValues = limelightCamera.poll();
        //Gets the distance from the camera to the target
        double distance = LimelightCamera.getDistance(target.getHeight(), limeLightValues.getTargetVertical());
        SmartDashboard.putNumber("distance", distance);
        SmartDashboard.putNumber("cachedTA", limeLightValues.ta);
        SmartDashboard.putNumber("cachedTX", limeLightValues.tx);
        SmartDashboard.putNumber("cachedTY", limeLightValues.ty);
        SmartDashboard.putNumber("cachedTS", limeLightValues.ts);

        if (!limeLightValues.hasTarget()) {
            cameraFail = cameraFail + 1;
            SmartDashboard.putNumber("CameraFail", cameraFail);
        } else {
            
        }
    }
}