package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

@TeleOp
public class MecanumTest extends OpMode {
    DcMotor  fr, fl, br, bl;
    Visionator vision;
    @Override
    public void init(){
        fr=hardwareMap.get(DcMotor.class, "fr");
        fl=hardwareMap.get(DcMotor.class, "fl");
        bl=hardwareMap.get(DcMotor.class, "bl");
        br=hardwareMap.get(DcMotor.class, "br");
        vision = new Visionator(hardwareMap, true, telemetry);

    }

    @Override
    public void init_loop() {
        vision.readAprilTag();
    }

    @Override
    public void start() {
    }

    @Override
    public void loop() {
        double turn =  -(gamepad1.right_trigger - gamepad1.left_trigger);
        double strafe = -gamepad1.left_stick_x;
        double drive = gamepad1.left_stick_y;
        vision.readAprilTag();

        if (gamepad1.a && vision.readAprilTag()){

            turn+=0.1*Math.sqrt(Math.abs(vision.bearing))*(vision.bearing/(Math.abs(vision.bearing)));
        }

        double[] speeds = {
                (drive + strafe + turn),
                (drive - strafe - turn),
                (drive - strafe + turn),
                (drive + strafe - turn)
        };

        if (gamepad1.a && vision.readAprilTag()){
            vision.readAprilTag();

        }

        fl.setPower(speeds[0]);
        fr.setPower(-speeds[1]);
        bl.setPower(speeds[2]);
        br.setPower(-speeds[3]);
    }
}
