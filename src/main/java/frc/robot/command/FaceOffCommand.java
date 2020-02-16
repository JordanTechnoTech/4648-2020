package frc.robot.command;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotMap;
import frc.robot.camera.LimeLightValues;
import frc.robot.camera.LimelightCamera;

import java.util.Arrays;
import java.util.List;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class FaceOffCommand extends CommandBase {
    
    public static List<RangeValue> turnSpeedRangeValues = Arrays.asList(
            new RangeValue(-999, 1, 0.0),
            new RangeValue(1, 5, -.023),
            new RangeValue(5, 10, -.025),
            new RangeValue(10, 15, -.03),
            new RangeValue(15, 9999, -.035)
    );

    public double trueDistance;



    LimelightCamera limelightCamera = new LimelightCamera();
    double cameraFail;
    LimeLightValues limeLightValues;
    private boolean finished = false;

    FaceOffCommand.Target target;

    public FaceOffCommand(Target atarget) {
        this.target = atarget;
    }

    @Override
    public void initialize() {
        //finished = false;
        LimelightCamera.setLightMode(LimelightCamera.ledMode.ON);
        LimelightCamera.setPipeline(1);
        LimelightCamera.setCameraMode(LimelightCamera.cameraMode.VISION);

        super.initialize();
    }

    @Override
    public void execute() {
        limeLightValues = limelightCamera.poll();
        //Gets the distance from the camera to the target
        double limelightDistance = LimelightCamera.getDistance(target.getHeight(), limeLightValues.getTargetVertical());
        trueDistance = limelightDistance * 0.86;
        
        SmartDashboard.putNumber("distance", trueDistance);
        SmartDashboard.putNumber("cachedTA", limeLightValues.ta);
        SmartDashboard.putNumber("cachedTX", limeLightValues.tx);
        SmartDashboard.putNumber("cachedTY", limeLightValues.ty);
        SmartDashboard.putNumber("cachedTS", limeLightValues.ts);

        if (!limeLightValues.hasTarget()) {
            cameraFail = cameraFail + 1;
            SmartDashboard.putNumber("CameraFail", cameraFail);
        } else {
            double turnSpeed = getTurnSpeed(limeLightValues);

            SmartDashboard.putNumber("limelightSkew", limeLightValues.getTargetSkew());
            SmartDashboard.putNumber("faceOffTTurnSpeed", turnSpeed);

            RobotMap.driveSubsystem.arcadeDrive(0, -turnSpeed * 2f);
        }
    }

    public double findInCollection(List<RangeValue> rangeValues, double itemToFind) {
        for (RangeValue rangeValue : rangeValues) {
            if (itemToFind > rangeValue.startValue && itemToFind <= rangeValue.endValue) {
                return rangeValue.result;
            }
        }
        throw new RuntimeException("Item not found:" + itemToFind);
    }

    //Tells the robot how much it has to turn
    public double getTurnSpeed(LimeLightValues limeLightValues) {
        float tx = (float) limeLightValues.getTargetHorizontal();
        float angle = Math.abs(tx);

        float Kp = (float) findInCollection(turnSpeedRangeValues, angle);

        float heading_error = -tx;
        float steering_adjust = 0.0f;
        if (tx > 1.0) { //target is moving right
            steering_adjust = (Kp * heading_error);
        } else if (tx < 1.0) { //target is moving left
            steering_adjust = (Kp * heading_error);
        }
        
        return steering_adjust;
    }

    @Override
    public boolean isFinished() {
        if (finished) {
            LimelightCamera.setLightMode(LimelightCamera.ledMode.OFF);
            LimelightCamera.setPipeline(1);
            LimelightCamera.setCameraMode(LimelightCamera.cameraMode.CAMERA);
        }
        return finished;
    }

    @Override
    public synchronized void cancel() {
        LimelightCamera.setLightMode(LimelightCamera.ledMode.OFF);
        LimelightCamera.setCameraMode(LimelightCamera.cameraMode.CAMERA);
        LimelightCamera.setPipeline(1);
        super.cancel();
    }

    public enum Target {
        TOP_OUTER_HOLE(89.0d);

        private final double height;

        Target(double v) {
            height = v;
        }

        public double getHeight() {
            return height;
        }
    }

}