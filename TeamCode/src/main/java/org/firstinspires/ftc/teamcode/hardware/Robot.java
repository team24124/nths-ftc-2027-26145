package org.firstinspires.ftc.teamcode.hardware;

import com.pedropathing.follower.Follower;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.hardware.subSystems.Intake;
import org.firstinspires.ftc.teamcode.hardware.subSystems.Shooter;
import org.firstinspires.ftc.teamcode.hardware.subSystems.Drivetrain;
import org.firstinspires.ftc.teamcode.pedro.Constants;
import org.firstinspires.ftc.teamcode.utility.TelemetryControl;

public class Robot {
    public final Intake intake;
    public final Shooter shooter;
    public final Drivetrain drivetrain;
    public final TelemetryControl tC;
    public Follower follower;
    public Robot(HardwareMap hm, Telemetry t) {
        intake = new Intake(hm);
        shooter = new Shooter(hm);
        drivetrain = new Drivetrain(hm);
        tC = new TelemetryControl(t);
        follower = Constants.create(hm);
        tC
                .subscribe(intake)
                .subscribe(shooter);
    }
}
