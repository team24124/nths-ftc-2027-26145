package org.firstinspires.ftc.teamcode.pedro;

import com.pedropathing.algorithm.Foresight;
import com.pedropathing.algorithm.ForesightConfig;
import com.pedropathing.controllers.Controller;
import com.pedropathing.follower.Follower;
import com.pedropathing.math.Matrix;
import com.pedropathing.math.Vector2D;
import com.pedropathing.revhub.drivetrains.Mecanum;
import com.pedropathing.revhub.drivetrains.MecanumConfig;
import com.pedropathing.revhub.localizers.PinpointConfig;
import com.pedropathing.revhub.localizers.PinpointLocalizer;
import com.qualcomm.hardware.gobilda.GoBildaPinpointDriver;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

// http://192.168.43.1:10158 tuning
public class Constants {

    //the constrants that got set throught auto set
    public static MecanumConfig drivetrainConfig = new MecanumConfig(c -> {
        c.frontLeftName.set("fl");
        c.frontRightName.set("fr");
        c.backLeftName.set("bl");
        c.backRightName.set("br");
        c.frontLeftDirection.set(DcMotorSimple.Direction.REVERSE);
        c.frontRightDirection.set(DcMotorSimple.Direction.FORWARD);
        c.backLeftDirection.set(DcMotorSimple.Direction.REVERSE);
        c.backRightDirection.set(DcMotorSimple.Direction.FORWARD);
    });

    public static PinpointConfig localizerConfig = new PinpointConfig(c -> {
        c.name.set("pinpoint");
        c.podType.set(GoBildaPinpointDriver.GoBildaOdometryPods.goBILDA_SWINGARM_POD);
        c.xPodOffset.set(1.3225477323757384);
        c.yPodOffset.set(4.078961770365558);
        c.xPodDirection.set(GoBildaPinpointDriver.EncoderDirection.FORWARD);
        c.yPodDirection.set(GoBildaPinpointDriver.EncoderDirection.FORWARD);
        c.globalDistanceUnit.set(DistanceUnit.INCH);
        c.offsetUnits.set(DistanceUnit.INCH);
    });


    public static ForesightConfig foresightConfig = new ForesightConfig(
            c -> {
                Controller primaryTranslationalForward = Controller.proportional(0.17907325981048064);
                Controller secondaryTranslationalForward = Controller.proportional(0.06616278457434523);
                Controller primaryTranslationalLateral = Controller.proportional(0.37000083408119766);
                Controller secondaryTranslationalLateral = Controller.proportional(0.1367054215886317);

                c.forwardTranslational.set(Controller.piecewise(secondaryTranslationalForward).put(2.5, primaryTranslationalForward));
                c.strafeTranslational.set(Controller.piecewise(secondaryTranslationalLateral).put(2.5, primaryTranslationalLateral));

                c.coast.set(Controller.proportionalFeedforward(0.018166664843129267));
                c.brake.set(Controller.proportionalFeedforward(0.015441665116659876));

                c.headingFeedback.set(Controller.proportional(3.9663614725421708));
                c.headingBrakeCoefficients.set(Vector2D.cartesian(0.055792555502621605, 0.008096703944341779));

                c.linearBrakeCoefficients.set(Matrix.diag(0.09879479485168437, 0.0327694304975308));
                c.quadraticBrakeCoefficients.set(Matrix.diag(0.001552443968822196, 0.0027671163070526247));

                c.maxAchievableForwardVelocity.set(61.930115124607426);
                c.maxAchievableStrafeVelocity.set(48.26330171858322);
                c.naturalForwardDeceleration.set(37.69028299425863);
                c.naturalStrafeDeceleration.set(62.97331638961428);
            }
    );
    public static Follower create(HardwareMap h) {
        return new Follower(
                new PinpointLocalizer(h, localizerConfig),
                new Mecanum(h, drivetrainConfig),
                new Foresight(foresightConfig)
        );
    }
}