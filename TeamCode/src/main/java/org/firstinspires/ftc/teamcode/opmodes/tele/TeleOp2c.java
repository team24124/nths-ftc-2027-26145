package org.firstinspires.ftc.teamcode.opmodes.tele;

import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.arcrobotics.ftclib.gamepad.GamepadKeys;
import com.qualcomm.hardware.lynx.LynxModule;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.teamcode.hardware.Robot;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import java.util.List;

@TeleOp(name = "Teleop 2 c")
public class TeleOp2c extends OpMode {
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

        robot.intake.run(g2.getTrigger(GamepadKeys.Trigger.LEFT_TRIGGER) >= 0.5);
        robot.intake.reverse(g2.isDown(GamepadKeys.Button.LEFT_BUMPER));

        robot.shooter.run(g2.getTrigger(GamepadKeys.Trigger.RIGHT_TRIGGER) >= 0.5);
        robot.shooter.reverse(g2.isDown(GamepadKeys.Button.RIGHT_BUMPER));


        double y = -g1.getLeftY(); // Remember, Y stick value is reversed
        double x = g1.getLeftX() * 1.1; // Counteract imperfect strafing
        double rx = g1.getRightX();

        robot.drivetrain.run(x, y, rx);

        robot.tC.update();
    }
}
