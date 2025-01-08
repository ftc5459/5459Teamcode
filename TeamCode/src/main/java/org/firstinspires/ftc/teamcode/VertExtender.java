package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp(name = "Vertical Extender Control", group = "TeleOp")
public class VertExtender extends OpMode {

    private DcMotor extenderMotor;

    @Override
    public void init() {
        extenderMotor = hardwareMap.get(DcMotor.class, "extender_motor");
        extenderMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        extenderMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        telemetry.addData("Status", "Initialized");
    }

    @Override
    public void loop() {
        double upwardPower = gamepad1.right_trigger;   // Upward control
        double downwardPower = gamepad1.left_trigger;  // Downward control

        double power = upwardPower - downwardPower;    // Net power: positive = up, negative = down
        extenderMotor.setPower(power);

        telemetry.addData("Extender Motor Power", power);
        telemetry.update();
    }
}

