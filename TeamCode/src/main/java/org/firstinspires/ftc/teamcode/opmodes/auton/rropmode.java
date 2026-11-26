package org.firstinspires.ftc.teamcode.opmodes.auton;

import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.Vector2d;
import com.acmerobotics.roadrunner.ftc.Actions;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

// NOTE: Replace MecanumDrive with your drive class from the RR v1 quickstart
import org.firstinspires.ftc.teamcode.roadrunner.MecanumDrive;

@Autonomous(name = "rropmode", group = "Autonomous")
@Config
public class rropmode extends LinearOpMode {

    @Override
    public void runOpMode() {
        // Define start pose
        Pose2d startPose = new Pose2d(62.06, -12.98, Math.toRadians(180.00-360));

        // Initialize drive with start pose
        MecanumDrive drive = new MecanumDrive(hardwareMap, startPose);

        // Build trajectory using TrajectoryActionBuilder (replaces TrajectorySequenceBuilder)
        Action traj = drive.actionBuilder(new Pose2d(62.06, -12.98, Math.toRadians(180.00-360)))

                .splineTo(new Vector2d(37.89, -35.96), Math.toRadians(232.44-360))
                .splineTo(new Vector2d(-22.91, -55.84), Math.toRadians(180.68-360))
                .build();



        // Wait for start signal
        waitForStart();

        if (opModeIsActive()) {
            // runBlocking executes the action synchronously (replaces followTrajectorySequence)
            Actions.runBlocking(traj);
        }
    }
}