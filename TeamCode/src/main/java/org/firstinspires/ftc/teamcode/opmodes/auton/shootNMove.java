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
            while (opModeIsActive() && timer.milliseconds() < 3000) {
                robot.shooter.run(true);
            }
            timer.reset();

            while (opModeIsActive() && timer.milliseconds() < 5000) {
                robot.shooter.run(true);
                robot.intake.run(true);

                double currentTime = timer.milliseconds();

                if (currentTime < 3000) {
                    int phase = (int) (currentTime / 500);
                    boolean isGateOpen = (phase % 2 == 0);
                    robot.intake.toggleGate(isGateOpen);
                } else {
                    robot.intake.toggleGate(true);
                }
            }
            timer.reset();

            robot.intake.toggleGate(false);
            robot.intake.run(false);
            robot.shooter.run(false);

            robot.drivetrain.fl.setPower(-0.5);
            robot.drivetrain.fr.setPower(-0.5);
            robot.drivetrain.bl.setPower(-0.5);
            robot.drivetrain.br.setPower(-0.5);

            while (opModeIsActive() && timer.milliseconds() < 600) {idle();}

            robot.drivetrain.fl.setPower(0);
            robot.drivetrain.fr.setPower(0);
            robot.drivetrain.bl.setPower(0);
            robot.drivetrain.br.setPower(0);

        }
    }
}
