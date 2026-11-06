package org.firstinspires.ftc.teamcode.opmodes;

import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.arcrobotics.ftclib.gamepad.GamepadKeys;
import com.qualcomm.hardware.lynx.LynxModule;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.teamcode.hardware.Robot;

import java.util.List;

public class TeleOp extends OpMode {
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

        assert robot.follower != null;
        robot.follower.manual(-g1.getLeftY(), g1.getLeftX(), g1.getRightX());

        robot.intake.run(g2.isDown(GamepadKeys.Button.A));
        robot.intake.reverse(g2.isDown(GamepadKeys.Button.LEFT_BUMPER));

        robot.shooter.run(g2.getTrigger(GamepadKeys.Trigger.RIGHT_TRIGGER) >= 0.5);
        robot.shooter.reverse(g2.isDown(GamepadKeys.Button.RIGHT_BUMPER));
    }
}
