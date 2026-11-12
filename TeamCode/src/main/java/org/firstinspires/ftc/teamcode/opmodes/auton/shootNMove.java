package org.firstinspires.ftc.teamcode.opmodes.auton;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.hardware.Robot;

@Autonomous(name = "Shoot Move Forward", group = "Autonomous")
public class shootNMove extends LinearOpMode {

    @Override
    public void runOpMode() {
        Robot robot = new Robot(hardwareMap, telemetry);
        ElapsedTime timer = new ElapsedTime();

        waitForStart();
        if (opModeIsActive()) {
            timer.reset();
            while (timer.milliseconds() < 3000) {
                robot.shooter.run(true);
            }
            timer.reset();

            while (timer.milliseconds() < 1000) {
                robot.shooter.run(true);
                robot.intake.run(true);
            }
            timer.reset();

            robot.intake.run(false);
            robot.shooter.run(false);

            robot.drivefix.fl.setPower(-0.5);
            robot.drivefix.fr.setPower(-0.5);
            robot.drivefix.bl.setPower(-0.5);
            robot.drivefix.br.setPower(-0.5);

            while (timer.milliseconds() < 3000) {idle();}

            robot.drivefix.fl.setPower(0);
            robot.drivefix.fr.setPower(0);
            robot.drivefix.bl.setPower(0);
            robot.drivefix.br.setPower(0);
        }
    }
}
