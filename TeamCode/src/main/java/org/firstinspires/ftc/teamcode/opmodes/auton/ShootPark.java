package org.firstinspires.ftc.teamcode.opmodes.auton;

import static com.pedropathing.api.Paths.*;
import com.pedropathing.api.Paths;

import com.pedropathing.api.PoseFactory;
import com.pedropathing.follower.Follower;
import com.pedropathing.math.Pose;
import com.pedropathing.paths.Path;
import com.pedropathing.ivy.Command;
import com.pedropathing.ivy.Scheduler;
import static com.pedropathing.ivy.Scheduler.schedule;
import static com.pedropathing.ivy.commands.Commands.*;
import static com.pedropathing.ivy.groups.Groups.sequential;
import static com.pedropathing.ivy.pedro.PedroCommands.follow;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.hardware.Robot;
import org.firstinspires.ftc.teamcode.pedro.Constants;

@Autonomous(name = "Shoot Park", group = "Autonomous")
public class ShootPark extends LinearOpMode {

    private Follower follower;

    private final PoseFactory poseFactory = PoseFactory.degrees();

    private final Pose start = poseFactory.of(58.1264, 7.9037, -90);
    private final Pose backup = poseFactory.of(58.1269, 22.9496, -90);
    private final Pose rotateStart = poseFactory.of(58.1269, 22.9496, -90);
    private final Pose rotate = poseFactory.of(58.1639, 22.8386, 160);
    private final Pose park = poseFactory.of(13.7527, 94.7134, 90.7033);
    private final Pose parkControl1 = poseFactory.of(14.5122, 32.8436, 0);

    private Robot robot;

    // Autonomous routine
    public Command autoRoutine() {
        return sequential(
                follow(follower, backup()),
                follow(follower, rotate()),
                follow(follower, park())
        );
    }

    @Override
    public void runOpMode() {
        Scheduler.reset();
        follower = Constants.create(hardwareMap);
        follower.setPose(start);
        follower.update();

        robot = new Robot(hardwareMap, telemetry);

        waitForStart();
        schedule(autoRoutine());

        while (opModeIsActive()) {
            follower.update();
            Scheduler.execute();

            telemetry.addData("x", follower.pose().x());
            telemetry.addData("y", follower.pose().y());
            telemetry.addData("heading", follower.pose().heading());

            if (follower.currentPath() != null) {
                telemetry.addData("Current path distance remaining", follower.distanceToEndpoint());
                telemetry.addData("Path number", follower.pathIndex());
            }

            telemetry.update();
            robot.tC.update();
        }

        robot.stop();
    }

    public Path backup() {
        return Paths.line(start, backup).linear(start, backup);
    }

    public Path rotate() {
        return Paths.line(rotateStart, rotate).linear(rotateStart, rotate);
    }

    public Path park() {
        return Paths.curve(rotate, parkControl1, park).tangent();
    }
}
