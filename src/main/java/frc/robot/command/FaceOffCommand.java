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
            new RangeValue(1, 1.5, 0.27),
            new RangeValue(1.5, 2.5, .17),
            new RangeValue(2.5, 5, -.12),
            new RangeValue(5, 10, -.056),
            new RangeValue(10, 15, -.04),
            new RangeValue(15, 30, -.025),
            new RangeValue(30, 999, -0.05)
    );

    LimelightCamera limelightCamera = new LimelightCamera();
    double cameraFail;
    LimeLightValues limeLightValues;

    FaceOffCommand.Target target;
    private double limelightDistance;

    private long commandStartTime;

    public FaceOffCommand(Target atarget) {
        this.target = atarget;
    }

    @Override
    public void initialize() {
        //finished = false;
        SmartDashboard.putString("Faceoffcommand", "initializing");
        LimelightCamera.setLightMode(LimelightCamera.ledMode.ON);
        LimelightCamera.setPipeline(1);
        LimelightCamera.setCameraMode(LimelightCamera.cameraMode.VISION);

        commandStartTime = System.currentTimeMillis();

        super.initialize();
    }

    @Override
    public void execute() {
        limeLightValues = limelightCamera.poll();
        //Gets the distance from the camera to the target
        limelightDistance = LimelightCamera.getDistance(target.getHeight(), limeLightValues.getTargetVertical());
        
        SmartDashboard.putNumber("distance", limelightDistance);
        SmartDashboard.putNumber("cachedTA", limeLightValues.ta);
        SmartDashboard.putNumber("cachedTX", limeLightValues.tx);
        SmartDashboard.putNumber("cachedTY", limeLightValues.ty);
        SmartDashboard.putNumber("cachedTS", limeLightValues.ts);
        SmartDashboard.putString("Faceoffcommand", "executing");
        if (!limeLightValues.hasTarget()) {
            cameraFail = cameraFail + 1;
            SmartDashboard.putNumber("CameraFail", cameraFail);
        } else {
            double turnSpeed = getTurnSpeed(limeLightValues);

            SmartDashboard.putNumber("limelightSkew", limeLightValues.getTargetSkew());
            SmartDashboard.putNumber("faceOffTTurnSpeed", turnSpeed);

            RobotMap.driveSubsystem.arcadeDrive(0, -turnSpeed);
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
        float tx = (float) limeLightValues.getTargetHorizontal();
        float angle = Math.abs(tx);
        
        if (angle < (1.5 - (0.005 * limelightDistance)) && commandStartTime + 1750 < System.currentTimeMillis() && limeLightValues.hasTarget()) {
            LimelightCamera.setLightMode(LimelightCamera.ledMode.OFF);
            LimelightCamera.setPipeline(1);
            LimelightCamera.setCameraMode(LimelightCamera.cameraMode.CAMERA);
            RobotMap.driveSubsystem.arcadeDrive(0, 0);
            SmartDashboard.putString("Faceoffcommand", "finished");
            return true;
        }
        return false;
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