package org.firstinspires.ftc.teamcode.opmodes.auton;

import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.hardwareMap;
import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.telemetry;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.hardware.Robot;

//just drive to park start at back wall right behind parking spot
@Autonomous(name = "Move Forward", group = "Autonomous")
public class drive extends LinearOpMode {
    @Override
    public void runOpMode() {
        Robot robot = new Robot(hardwareMap, telemetry);
        ElapsedTime timer = new ElapsedTime();

        waitForStart();

        if (opModeIsActive()) {
            robot.drivetrain.setPowers(-0.5,-0.5,-0.5,-0.5);

            timer.reset();
            while (opModeIsActive() && timer.milliseconds() < 350) {
                idle();
            }

            robot.drivetrain.setPowers(0,0,0,0);
        }
        robot.drivetrain.setPowers(0,0,0,0);
    }
}
