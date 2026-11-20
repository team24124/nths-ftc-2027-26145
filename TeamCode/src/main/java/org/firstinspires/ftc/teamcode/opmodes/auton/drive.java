package org.firstinspires.ftc.teamcode.opmodes.auton;

import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.hardwareMap;
import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.telemetry;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.hardware.Robot;

@Autonomous(name = "move Forward", group = "Autonomous")
public class drive  extends LinearOpMode {
    @Override
    public void runOpMode() {
        Robot robot = new Robot(hardwareMap, telemetry);
        ElapsedTime timer = new ElapsedTime();

        waitForStart();


        robot.drivetrain.fl.setPower(-0.5);
        robot.drivetrain.fr.setPower(-0.5);
        robot.drivetrain.bl.setPower(-0.5);
        robot.drivetrain.br.setPower(-0.5);

        while (opModeIsActive() && timer.milliseconds() < 350) {
            idle();
        }

        robot.drivetrain.fl.setPower(0);
        robot.drivetrain.fr.setPower(0);
        robot.drivetrain.bl.setPower(0);
        robot.drivetrain.br.setPower(0);
    }
}
