package org.firstinspires.ftc.teamcode.opmodes.auton;

import com.pedropathing.api.Paths;

import com.pedropathing.api.PoseFactory;
import com.pedropathing.follower.Follower;
import com.pedropathing.math.Pose;
import com.pedropathing.paths.Path;
import com.pedropathing.ivy.Command;
import com.pedropathing.ivy.Scheduler;
import static com.pedropathing.ivy.Scheduler.schedule;
import static com.pedropathing.ivy.groups.Groups.sequential;
import static com.pedropathing.ivy.pedro.PedroCommands.follow;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.hardware.Robot;
import org.firstinspires.ftc.teamcode.pedro.Constants;

@Autonomous(name = "2 tip", group = "Autonomous")
public class TwoTip extends LinearOpMode {

    private Follower follower;

    private final PoseFactory poseFactory = PoseFactory.degrees();

    private final Pose start = poseFactory.of(58.1264, 7.9037, -90);
    private final Pose backup = poseFactory.of(58.1269, 22.9496, -90);
    private final Pose rotateStart = poseFactory.of(58.1269, 22.9496, -90);
    private final Pose rotate = poseFactory.of(58.1639, 22.8386, 146);
    private final Pose gotoflower1 = poseFactory.of(22.7562, 46.4166, 146.3404);
    private final Pose collectflower1 = poseFactory.of(13.3188, 46.438, 179.8699);
    private final Pose backoutofflower1 = poseFactory.of(22.9748, 46.4862, -179.7143);
    private final Pose rotate_2Start = poseFactory.of(22.9748, 46.4862, 180);
    private final Pose rotate_2 = poseFactory.of(22.8933, 46.5129, 90);
    private final Pose gotoshootingpointaStart = poseFactory.of(22.8933, 46.5129, 90);
    private final Pose gotoshootingpointa = poseFactory.of(57.6275, 107.4006, 0);
    private final Pose gotoshootingpointaControl1 = poseFactory.of(16.1717, 83.9316, 0);
    private final Pose gotoshootingpointaControl2 = poseFactory.of(30.3178, 106.8688, 0);
    private final Pose rotate_3Start = poseFactory.of(57.6275, 107.4006, 0);
    private final Pose rotate_3 = poseFactory.of(58.8979, 107.5632, 90);
    private final Pose gotoshootingpointbStart = poseFactory.of(58.8979, 107.5632, 90);
    private final Pose gotoshootingpointb = poseFactory.of(58.553, 133.2713, 90);
    private final Pose gotoflower2Start = poseFactory.of(58.553, 133.2713, 90);
    private final Pose gotoflower2 = poseFactory.of(46.7159, 118.5623, 90);
    private final Pose gotoflower2Control1 = poseFactory.of(59.6512, 119.2803, 0);
    private final Pose collectflower2 = poseFactory.of(46.6898, 128.4466, 90.1511);
    private final Pose backoutofflower2 = poseFactory.of(46.6483, 118.7655, 89.7547);
    private final Pose gotoshootingpointStart = poseFactory.of(46.6483, 118.7655, 90);
    private final Pose gotoshootingpoint = poseFactory.of(58.6065, 133.4136, 90);
    private final Pose gotoshootingpointControl1 = poseFactory.of(59.92, 119.2265, 0);
    private final Pose backup_2 = poseFactory.of(58.7562, 107.6453, 90.3328);
    private final Pose rotate_4Start = poseFactory.of(58.7562, 107.6453, 90);
    private final Pose rotate_4 = poseFactory.of(58.5709, 107.5761, -162);
    private final Pose park = poseFactory.of(13.7252, 92.672, -161.6162);

    private Robot robot;

    // Autonomous routine
    public Command autoRoutine() {
        return sequential(
                follow(follower, backup()),
                follow(follower, rotate()),
                follow(follower, gotoflower1()),
                follow(follower, collectflower1()),
                follow(follower, backoutofflower1()),
                follow(follower, rotate_2()),
                follow(follower, gotoshootingpointa()),
                follow(follower, rotate_3()),
                follow(follower, gotoshootingpointb()),
                follow(follower, gotoflower2()),
                follow(follower, collectflower2()),
                follow(follower, backoutofflower2()),
                follow(follower, gotoshootingpoint()),
                follow(follower, backup_2()),
                follow(follower, rotate_4()),
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

    public Path gotoflower1() {
        return Paths.line(rotate, gotoflower1).tangent();
    }

    public Path collectflower1() {
        return Paths.line(gotoflower1, collectflower1).tangent();
    }

    public Path backoutofflower1() {
        return Paths.line(collectflower1, backoutofflower1).reverseTangent();
    }

    public Path rotate_2() {
        return Paths.line(rotate_2Start, rotate_2).linear(rotate_2Start, rotate_2);
    }

    public Path gotoshootingpointa() {
        return Paths.curve(gotoshootingpointaStart, gotoshootingpointaControl1, gotoshootingpointaControl2, gotoshootingpointa).linear(gotoshootingpointaStart, gotoshootingpointa);
    }

    public Path rotate_3() {
        return Paths.line(rotate_3Start, rotate_3).linear(rotate_3Start, rotate_3);
    }

    public Path gotoshootingpointb() {
        return Paths.line(gotoshootingpointbStart, gotoshootingpointb).linear(gotoshootingpointbStart, gotoshootingpointb);
    }

    public Path gotoflower2() {
        return Paths.curve(gotoflower2Start, gotoflower2Control1, gotoflower2).linear(gotoflower2Start, gotoflower2);
    }

    public Path collectflower2() {
        return Paths.line(gotoflower2, collectflower2).tangent();
    }

    public Path backoutofflower2() {
        return Paths.line(collectflower2, backoutofflower2).reverseTangent();
    }

    public Path gotoshootingpoint() {
        return Paths.curve(gotoshootingpointStart, gotoshootingpointControl1, gotoshootingpoint).linear(gotoshootingpointStart, gotoshootingpoint);
    }

    public Path backup_2() {
        return Paths.line(gotoshootingpoint, backup_2).reverseTangent();
    }

    public Path rotate_4() {
        return Paths.line(rotate_4Start, rotate_4).linear(rotate_4Start, rotate_4);
    }

    public Path park() {
        return Paths.line(rotate_4, park).tangent();
    }
}
