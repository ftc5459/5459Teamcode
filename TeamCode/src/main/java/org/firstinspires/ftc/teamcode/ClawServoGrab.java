

package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp(name = "ClawServoGrab", group = "TeleOp")
@SuppressWarnings("unused")
public class ClawServoGrab extends OpMode {

    private Servo clawServo;

    @Override
    public void init() {
        // Initialize the claw servo using the hardware map
        clawServo = hardwareMap.get(Servo.class, "claw_servo");
        telemetry.addData("Status", "Claw Servo Initialized");
        telemetry.update();
    }

    @Override
    public void loop() {
        // Check gamepad buttons and control the claw servo
        if (gamepad1.a) {//boolean? binary control
            // Adjust
            double openPosition = 0.8;
            clawServo.setPosition(openPosition);  // Open claw when 'A' is pressed
        } else if (gamepad1.b) {
            double closePosition = 0.2;
            clawServo.setPosition(closePosition); // Close claw when 'B' is pressed
        }

        // Add telemetry feedback for debugging
        telemetry.addData("Claw Position", clawServo.getPosition());
        telemetry.addData("Press A", "To Open Claw");
        telemetry.addData("Press B", "To Close Claw");
        telemetry.update();
    }
}
