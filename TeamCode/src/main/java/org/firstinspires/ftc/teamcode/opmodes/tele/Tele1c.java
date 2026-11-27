package org.firstinspires.ftc.teamcode.opmodes.tele;

import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.arcrobotics.ftclib.gamepad.GamepadKeys;
import com.qualcomm.hardware.lynx.LynxModule;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.hardware.Robot;

import java.util.List;

@TeleOp(name = "Teleop 1c", group = "!")
public class Tele1c extends OpMode {
    private Robot robot;
    private GamepadEx g1;

    private List<LynxModule> hubs;
    @Override
    public void init() {
        hubs = hardwareMap.getAll(LynxModule.class);
        for (LynxModule hub : hubs) {
            hub.setBulkCachingMode(LynxModule.BulkCachingMode.MANUAL);
        }

        robot = new Robot(hardwareMap, telemetry);
        g1 = new GamepadEx(gamepad1);
        robot.intake.toggleGate(false);
    }

    @Override
    public void loop() {
        for (LynxModule hub : hubs) {
            hub.clearBulkCache();
        }
        boolean bugs = false;
        robot.intake.run(g1.getTrigger(GamepadKeys.Trigger.LEFT_TRIGGER) >= 0.1);
        robot.intake.reverse(g1.isDown(GamepadKeys.Button.LEFT_BUMPER));

        robot.shooter.run(g1.getTrigger(GamepadKeys.Trigger.RIGHT_TRIGGER) >= 0.1);
        robot.shooter.reverse(g1.isDown(GamepadKeys.Button.RIGHT_BUMPER));

        robot.intake.toggleGate(g1.isDown(GamepadKeys.Button.A));

        double y = g1.getLeftY();
        double x = g1.getLeftX() * 1.1;
        double rx = g1.getRightX();
        robot.drivetrain.run(x, y, rx);

        robot.tC.update();
    }

    @Override
    public void stop() {
        robot.stop();
    }
}
