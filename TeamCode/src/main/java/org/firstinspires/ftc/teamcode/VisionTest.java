package org.firstinspires.ftc.teamcode;

import android.graphics.Camera;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.vision.VisionPortal;
import org.firstinspires.ftc.vision.apriltag.AprilTagDetection;
import org.firstinspires.ftc.vision.apriltag.AprilTagGameDatabase;
import org.firstinspires.ftc.vision.apriltag.AprilTagLibrary;
import org.firstinspires.ftc.vision.apriltag.AprilTagProcessor;

import java.util.List;

@TeleOp
public final class VisionTest extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {
        WebcamName webcam = hardwareMap.get(WebcamName.class, "Webcam 1");
        AprilTagProcessor aprilTest = new AprilTagProcessor.Builder()
                .setTagLibrary(AprilTagGameDatabase.getCurrentGameTagLibrary())
                .setDrawTagID(true)
                .setDrawTagOutline(true)
                .setDrawAxes(true)
                .setDrawCubeProjection(true)

                .build();
        VisionPortal visTest = VisionPortal.easyCreateWithDefaults(webcam, aprilTest);
        AprilTagDetection finDet;
        String order = null;
        int finID=1;

        waitForStart();

        while (opModeIsActive()) {
            List<AprilTagDetection> detects = aprilTest.getDetections();
            for (AprilTagDetection det : detects){
                if (det.id >=21 && det.id <=23 && order == null){
                    if (det.id==21){
                        order="gpp";
                    }
                    if (det.id==22){
                        order="pgp";
                    }
                    if (det.id==23){
                        order="ppg";
                    }
                    finID=det.id;


                }
                if(det.id==finID && det.metadata!=null){
                    telemetry.addData("Range", det.ftcPose.range*0.118279569892);
                }

            }
            telemetry.addData("Order", order);
            telemetry.update();
        }
    }
}
