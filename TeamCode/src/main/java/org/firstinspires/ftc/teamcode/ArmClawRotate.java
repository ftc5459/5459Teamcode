package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp(name = "ArmClawRotate", group = "TeleOp")
public class ArmClawRotate extends OpMode {
    private Servo armServo;
    private final double forwardAngle = 0.5;  // 0.5 = 90° (middle position)
    private final double backwardAngle = 0.0; // 0.0 = 0° (fully backward)

    @Override
    public void init() {
        armServo = hardwareMap.get(Servo.class, "arm_servo");
        telemetry.addData("Status", "Arm Servo Initialized");
        telemetry.update();
    }

    @Override
    public void loop() {
        if (gamepad1.a) {
            armServo.setPosition(forwardAngle);  // Rotate to forward angle (90°)
            telemetry.addData("Arm Position", "Forward (90°)");
        } else if (gamepad1.b) {
            armServo.setPosition(backwardAngle);  // Rotate to backward angle (0°)
            telemetry.addData("Arm Position", "Backward (0°)");
        }

        telemetry.addData("Current Servo Position", armServo.getPosition());
        telemetry.update();
    }
}
