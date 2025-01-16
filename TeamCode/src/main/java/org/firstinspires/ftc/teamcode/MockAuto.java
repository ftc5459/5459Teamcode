package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
//24 front, 24 back.
@Autonomous(name = "TestDrive", group = "Test")
public class MockAuto extends LinearOpMode {

    private DcMotor leftFrontMotor, leftBackMotor, rightFrontMotor, rightBackMotor;
    private static final double TICKS_PER_INCH = 45.27;

    @Override
    public void runOpMode() {
        // Initialize motors
        leftFrontMotor = hardwareMap.get(DcMotor.class, "left_front_motor");
        leftBackMotor = hardwareMap.get(DcMotor.class, "left_back_motor");
        rightFrontMotor = hardwareMap.get(DcMotor.class, "right_front_motor");
        rightBackMotor = hardwareMap.get(DcMotor.class, "right_back_motor");
        //set direction
        leftFrontMotor.setDirection(DcMotor.Direction.REVERSE);
        leftBackMotor.setDirection(DcMotor.Direction.REVERSE);
        rightFrontMotor.setDirection(DcMotor.Direction.FORWARD);
        rightBackMotor.setDirection(DcMotor.Direction.FORWARD);

        // Reset encoders and set run mode
        setMotorModes(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        setMotorModes(DcMotor.RunMode.RUN_USING_ENCODER);

        telemetry.addData("Status", "Initialized. Waiting for Start...");
        telemetry.update();

        waitForStart();

        if (opModeIsActive()) {
            // Move forward 24 inches
            moveForward(24, 0.5);

            // Pause for 1 second
            sleep(1000);

            // Move backward 24 inches
            moveForward(-24, 0.5);

            // Stop the motors
            stopMotors();
        }
    }

    // Method to move forward or backward
    private void moveForward(double inches, double power) {
        int ticks = (int) (inches * TICKS_PER_INCH);  // Convert inches to encoder ticks

        // Set target position
        leftFrontMotor.setTargetPosition(leftFrontMotor.getCurrentPosition() + ticks);
        leftBackMotor.setTargetPosition(leftBackMotor.getCurrentPosition() + ticks);
        rightFrontMotor.setTargetPosition(rightFrontMotor.getCurrentPosition() + ticks);
        rightBackMotor.setTargetPosition(rightBackMotor.getCurrentPosition() + ticks);

        // Set motors to run to position
        setMotorModes(DcMotor.RunMode.RUN_TO_POSITION);

        // Set motor power
        setMotorPowers(power, power, power, power);

        // Wait until motors finish moving
        while (opModeIsActive() && motorsBusy()) {
            telemetry.addData("Moving", "Distance: %.2f inches", inches);
            telemetry.update();
        }

        stopMotors();  // Stop motors after movement
        setMotorModes(DcMotor.RunMode.RUN_USING_ENCODER);
    }

    // Stop all motors
    private void stopMotors() {
        setMotorPowers(0, 0, 0, 0);
    }

    // Set motor powers
    private void setMotorPowers(double lf, double lb, double rf, double rb) {
        leftFrontMotor.setPower(lf);
        leftBackMotor.setPower(lb);
        rightFrontMotor.setPower(rf);
        rightBackMotor.setPower(rb);
    }

    // Set motor modes
    private void setMotorModes(DcMotor.RunMode mode) {
        leftFrontMotor.setMode(mode);
        leftBackMotor.setMode(mode);
        rightFrontMotor.setMode(mode);
        rightBackMotor.setMode(mode);
    }

    // Check if motors are busy
    private boolean motorsBusy() {
        return leftFrontMotor.isBusy() && leftBackMotor.isBusy() &&
                rightFrontMotor.isBusy() && rightBackMotor.isBusy();
    }
}
