package org.firstinspires.ftc.teamcode.opmodes.tele;

import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.arcrobotics.ftclib.gamepad.GamepadKeys;
import com.qualcomm.hardware.lynx.LynxModule;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.hardware.Robot;

import java.util.List;

@TeleOp(name = "Teleop 1 c")
public class TeleOp1c extends OpMode {
    private Robot robot;
    private GamepadEx g1, g2;

    private List<LynxModule> hubs;
    @Override
    public void init() {
        hubs = hardwareMap.getAll(LynxModule.class);
        for (LynxModule hub : hubs) {
            hub.setBulkCachingMode(LynxModule.BulkCachingMode.MANUAL);
        }

        robot = new Robot(hardwareMap, telemetry);
        g1 = new GamepadEx(gamepad1);
        g2 = new GamepadEx(gamepad2);
    }

    @Override
    public void loop() {
        for (LynxModule hub : hubs) {
            hub.clearBulkCache();
        }



        //assert robot.follower != null;
        /*
        robot.follower.manual(
                -gamepad1.left_stick_y,
                -gamepad1.left_stick_x,
                gamepad1.right_stick_x
        );
        robot.follower.update();

         */

        robot.intake.run(g1.getTrigger(GamepadKeys.Trigger.LEFT_TRIGGER) >= 0.5);
        robot.intake.reverse(g1.isDown(GamepadKeys.Button.LEFT_BUMPER));

        robot.shooter.run(g1.getTrigger(GamepadKeys.Trigger.RIGHT_TRIGGER) >= 0.5);
        robot.shooter.reverse(g1.isDown(GamepadKeys.Button.RIGHT_BUMPER));


        double y = -gamepad1.left_stick_y; // Remember, Y stick value is reversed
        double x = gamepad1.left_stick_x * 1.1; // Counteract imperfect strafing
        double rx = gamepad1.right_stick_x;

        // Denominator is the largest motor power (absolute value) or 1
        // This ensures all the powers maintain the same ratio,
        // but only if at least one is out of the range [-1, 1]
        double denominator = Math.max(Math.abs(y) + Math.abs(x) + Math.abs(rx), 1) * 1.5;
        double frontLeftPower = (y + x + rx) / denominator;
        double backLeftPower = (y - x + rx) / denominator;
        double frontRightPower = (y - x - rx) / denominator;
        double backRightPower = (y + x - rx) / denominator;

        robot.drivefix.fl.setPower(frontLeftPower);
        robot.drivefix.bl.setPower(backLeftPower);
        robot.drivefix.fr.setPower(frontRightPower);
        robot.drivefix.br.setPower(backRightPower);

        robot.tC.update();
    }
}
