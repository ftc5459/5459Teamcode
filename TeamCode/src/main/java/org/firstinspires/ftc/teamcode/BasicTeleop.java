package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp
public class BasicTeleop extends LinearOpMode {

    public Servo LinkageL=null;
    public Servo LinkageR=null;
    public Servo IntakeArm=null;
    public Servo BucketL=null;
    public Servo BucketR= null;
    public CRServo Intake = null;
    public DcMotor FL,FR,BL,BR,DepoSlide;

    double INTAKEOUT = .4;



    @Override
    public void runOpMode() throws InterruptedException {

        FL  = hardwareMap.get(DcMotor.class, "left_front_motor");
        BL = hardwareMap.get(DcMotor.class, "left_back_motor");
        FR = hardwareMap.get(DcMotor.class, "right_front_motor");
        BR = hardwareMap.get(DcMotor.class, "right_back_motor");

        FL.setDirection(DcMotorSimple.Direction.REVERSE);
        BL.setDirection(DcMotorSimple.Direction.REVERSE);
        FR.setDirection(DcMotorSimple.Direction.FORWARD);
        BR.setDirection(DcMotorSimple.Direction.FORWARD);

        DepoSlide = hardwareMap.get(DcMotor.class, "slide_motor");

        DepoSlide.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        DepoSlide.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        LinkageL = hardwareMap.get(Servo.class, "servo_left");
        LinkageR = hardwareMap.get(Servo.class, "servo_right");

        BucketL = hardwareMap.get(Servo.class, "servo_slider_l");
        BucketR = hardwareMap.get(Servo.class, "servo_slider_r");
        IntakeArm = hardwareMap.get(Servo.class, "arm_servo");


        Intake = hardwareMap.get(CRServo.class,"claw_servo");


        BucketR.setDirection(Servo.Direction.FORWARD);
        BucketL.setDirection(Servo.Direction.REVERSE);

        LinkageL.setDirection(Servo.Direction.FORWARD);
        LinkageR.setDirection(Servo.Direction.REVERSE);

        waitForStart();
        while (opModeIsActive()){

            double Drive = gamepad1.left_stick_y;
            double Strafe = -gamepad1.left_stick_x;
            double Turn = -gamepad1.right_stick_x;

            FL.setPower(Drive+Strafe+Turn);
            BL.setPower(Drive-Strafe-Turn);
            BR.setPower(Drive-Strafe+Turn);
            FR.setPower(Drive+Strafe-Turn);




            if (gamepad1.dpad_up){
                //Slides go to bucket
                if (DepoSlide.getCurrentPosition()<1100) {
                    DepoSlide.setPower(1);
                }else {
                    DepoSlide.setPower(0);
                }
                if (gamepad1.left_bumper) {
                    //bucket tilt
                    BucketL.setPosition(.4);
                    BucketR.setPosition(.4);
                }
            }
            else if (gamepad1.dpad_down) {
                //slides go in
                if (DepoSlide.getCurrentPosition()>50){
                    DepoSlide.setPower(-1);}else DepoSlide.setPower(0);
            }else {
                //bucket no tilt
                BucketL.setPosition(.1);
                BucketR.setPosition(.1);
                //slides off
                DepoSlide.setPower(0);
            }
            Intake.setPower(gamepad1.right_trigger-gamepad1.left_trigger);


            //if trigger goes past half way lower intake and turn it on
            if (gamepad1.right_trigger > .5){
                //Linkage slides go all the way out
                LinkageL.setPosition(INTAKEOUT);
                LinkageR.setPosition(INTAKEOUT);
                //intake arm is down so you can intake
                IntakeArm.setPosition(.2);
                //intake wheel is spinning
            }
            // if trigger goes down at all intake goes out arm up
            else if (gamepad1.right_bumper){
                LinkageL.setPosition(INTAKEOUT);
                LinkageR.setPosition(INTAKEOUT);
                IntakeArm.setPosition(.4);

            } else if (gamepad1.x) {
                //brings slides in and arm up
                LinkageL.setPosition(0);
                LinkageR.setPosition(0);
                //ARM UP
                IntakeArm.setPosition(.75);
            }


            telemetry.addData("SlideTick:",DepoSlide.getCurrentPosition());
            telemetry.update();


        }
    }
}