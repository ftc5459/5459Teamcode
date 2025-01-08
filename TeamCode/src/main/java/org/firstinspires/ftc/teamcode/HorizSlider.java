package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
//Uses left/right triggers

@TeleOp(name = "SliderControl", group = "TeleOp")
@SuppressWarnings("unused")
@Disabled
public class HorizSlider extends OpMode {

    private Servo servoSliderLeft;
    private Servo servoSliderRight;

    @Override
    public void init() {
        servoSliderLeft = hardwareMap.get(Servo.class, "servo_slider_l");
        servoSliderRight = hardwareMap.get(Servo.class, "servo_slider_r");

        telemetry.addData("Status", "Initialized");
    }

    @Override
    public void loop() {
        double forwardPower = gamepad1.right_trigger;   // Forward control
        double backwardPower = gamepad1.left_trigger;   // Backward control

        double position = forwardPower - backwardPower; // Net position: positive = forward, negative = backward

        // Map position to servo range (0.0 to 1.0)
        double servoPosition = 0.0 + (position * 0.5);  // Center at 0.0, adjust by half range

        // Set the same position for both servos
        servoSliderLeft.setPosition(servoPosition);
        servoSliderRight.setPosition(servoPosition);

        telemetry.addData("Slider Position", servoPosition);
        telemetry.update();
    }
}

