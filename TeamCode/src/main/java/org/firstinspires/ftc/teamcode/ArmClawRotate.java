package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

public class ArmClawRotate extends OpMode {
    private Servo armServo;
    private final double forwardAngle = 0.5;  // 0.5 = 90°
    private final double backwardAngle = 0.0; // back(0.0 to 1.0= 0° to 180°)

    @Override
    public void init() {
        armServo = hardwareMap.get(Servo.class, "arm_servo");
        telemetry.addData("Status", "Initialized");
    }

    @Override
    public void loop() {
        if (gamepad1.a) {
            armServo.setPosition(forwardAngle);  // Rotate to forward angle
            telemetry.addData("Arm Position", "Forward");
        } else if (gamepad1.b) {
            armServo.setPosition(backwardAngle);  // Rotate to backward angle
            telemetry.addData("Arm Position", "Backward");
        }

        telemetry.update();
    }
}
