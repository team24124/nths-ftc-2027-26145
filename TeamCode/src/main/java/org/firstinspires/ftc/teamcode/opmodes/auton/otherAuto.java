package org.firstinspires.ftc.teamcode.opmodes.auton;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.hardware.Robot;

@Autonomous(name = "Just Move Forward", group = "Autonomous")
public class otherAuto extends LinearOpMode {
    private Robot robot;

    @Override
    public void runOpMode() {
        waitForStart();
        if (opModeIsActive()) {
            robot = new Robot(hardwareMap, telemetry);
            robot.shooter.run(true);
            sleep(3000);
            robot.intake.run(true);
            sleep(1000);
            robot.intake.run(false);
            robot.shooter.run(false);

            robot.drivefix.fl.setPower(-0.5);
            robot.drivefix.fr.setPower(-0.5);
            robot.drivefix.bl.setPower(-0.5);
            robot.drivefix.br.setPower(-0.5);

            sleep(3000);

            robot.drivefix.fl.setPower(0);
            robot.drivefix.fr.setPower(0);
            robot.drivefix.bl.setPower(0);
            robot.drivefix.br.setPower(0);
        }
    }
}
