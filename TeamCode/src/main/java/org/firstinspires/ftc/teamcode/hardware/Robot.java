package org.firstinspires.ftc.teamcode.hardware;

import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.hardware.subsystems.Intake;
import org.firstinspires.ftc.teamcode.hardware.subsystems.Shooter;
import org.firstinspires.ftc.teamcode.hardware.subsystems.Drivetrain;
import org.firstinspires.ftc.teamcode.utility.TelemetryControl;

public class Robot {
    public final Intake intake;
    public final Shooter shooter;
    public final Drivetrain drivetrain;
    public final TelemetryControl tC;
    public Robot(HardwareMap hm, Telemetry t) {
        intake = new Intake(hm);
        shooter = new Shooter(hm);
        drivetrain = new Drivetrain(hm);
        tC = new TelemetryControl(t);
        tC
                .subscribe(intake)
                .subscribe(shooter)
                .subscribe(drivetrain);
    }

    public void stop() {
        tC.unsubscribeAll();
        intake.setPower(0);
        shooter.setPower(0);
        drivetrain.setPowers(0,0,0,0);
    }
}
