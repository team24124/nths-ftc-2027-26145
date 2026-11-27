package org.firstinspires.ftc.teamcode.opmodes.auton;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.hardware.Robot;

@Autonomous(name = "Shoot Move Forward", group = "!")
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

            final double CLOSE_MS      = 500;   // every closed phase
            final double OPEN_PULSE_MS = 200;   // each short open pulse
            final int    PULSES        = 3;     // number of short open pulses
            final double FINAL_OPEN_MS = 2000;  // last long open

            double cycleMs       = CLOSE_MS + OPEN_PULSE_MS;        // 700 (close then open)
            double pulsesEnd     = PULSES * cycleMs;                // 2100
            double finalCloseEnd = pulsesEnd + CLOSE_MS;            // 2600
            double totalTime     = finalCloseEnd + FINAL_OPEN_MS;   // 4600

            timer.reset();

            while (opModeIsActive() && timer.milliseconds() < totalTime) {
                robot.shooter.run(true);
                robot.intake.run(true);

                double t = timer.milliseconds();
                boolean isGateOpen;

                if (t < pulsesEnd) {
                    // Repeating 700ms cycle: first 500ms closed, last 200ms open
                    double timeInCycle = t % cycleMs;
                    isGateOpen = (timeInCycle >= CLOSE_MS);
                } else if (t < finalCloseEnd) {
                    isGateOpen = false;   // final 500ms close
                } else {
                    isGateOpen = true;    // final 2000ms open
                }

                robot.intake.toggleGate(isGateOpen);
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

            robot.stop();

        }
    }
}
