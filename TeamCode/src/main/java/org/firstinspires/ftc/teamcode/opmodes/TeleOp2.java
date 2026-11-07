package org.firstinspires.ftc.teamcode.opmodes;

import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.arcrobotics.ftclib.gamepad.GamepadKeys;
import com.pedropathing.follower.Follower;
import com.qualcomm.hardware.lynx.LynxModule;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.teamcode.hardware.Robot;
import org.firstinspires.ftc.teamcode.pedro.Constants;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import java.util.List;

@TeleOp(group = "!", name = "new Tele")
public class TeleOp2 extends OpMode {
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

        // Drive
        robot.follower.manual(
                -g1.getLeftY(),
                g1.getLeftX() * 1.1,
                g1.getRightX()
        );
        robot.follower.update();

        // Intake
        robot.intake.run(g2.isDown(GamepadKeys.Button.A));
        robot.intake.reverse(g2.isDown(GamepadKeys.Button.LEFT_BUMPER));

        // Shooter
        robot.shooter.reverse(g2.isDown(GamepadKeys.Button.B));
        robot.shooter.run(g2.isDown(GamepadKeys.Button.RIGHT_BUMPER));

        // Telemetry
        robot.tC.update();
    }
}
