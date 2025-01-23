package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.Servo;

@Autonomous(name="dump", group="test")
public class DumpAuto extends LinearOpMode {

    private Servo servoLeft;
    private Servo servoRight;

    @Override
    public void runOpMode() {
        // Initialize servos
        servoLeft = hardwareMap.get(Servo.class, "servo_left");
        servoRight = hardwareMap.get(Servo.class, "servo_right");

        // Set initial positions
        servoLeft.setPosition(0);
        servoRight.setPosition(0);

        telemetry.addData("Status", "Initialized");
        telemetry.update();

        waitForStart();

        while (opModeIsActive()) {
            // Rotate servos 60 degrees clockwise
            servoLeft.setPosition(0.333); // 60 degrees CW
            servoRight.setPosition(0.333);
            sleep(1000); // Adjusted sleep time

            // Return servos to 0
            servoLeft.setPosition(0);
            servoRight.setPosition(0);
            sleep(1000);

            // Rotate servos 60 degrees counter-clockwise
            servoLeft.setPosition(0.667); // 60 degrees CCW
            servoRight.setPosition(0.667);
            sleep(1000);

            // Return servos to 0
            servoLeft.setPosition(0);
            servoRight.setPosition(0);
            sleep(1000);

            // Telemetry data
            telemetry.addData("Servo Left Position", servoLeft.getPosition());
            telemetry.addData("Servo Right Position", servoRight.getPosition());
            telemetry.update();
        }
    }
}
