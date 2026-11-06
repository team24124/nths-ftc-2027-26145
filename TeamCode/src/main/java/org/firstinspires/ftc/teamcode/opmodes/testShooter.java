package org.firstinspires.ftc.teamcode.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.hardware.Robot;
import org.firstinspires.ftc.teamcode.hardware.subSystems.Shooter;

@TeleOp (name = "shooterTest")
public class testShooter extends OpMode {
    Robot robot;
    @Override
    public void init() {
        robot = new Robot(hardwareMap, telemetry);
    }

    @Override
    public void loop() {
        robot.shooter.run(true);
    }

    @Override
    public void stop() {
        robot.tC.unsubscribeAll();
    }
}
