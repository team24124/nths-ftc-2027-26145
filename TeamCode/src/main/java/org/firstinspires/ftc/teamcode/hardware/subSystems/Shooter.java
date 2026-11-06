package org.firstinspires.ftc.teamcode.hardware.subSystems;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.VoltageSensor;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.utility.PIDF;
import org.firstinspires.ftc.teamcode.utility.interfaces.SubsystemBase;
import org.firstinspires.ftc.teamcode.utility.interfaces.TelemetryObservable;

@Config
public class Shooter implements SubsystemBase, TelemetryObservable {
    private final DcMotorEx shooterMotor;
    private boolean reverse = false;
    public static double speed = 1;
    private boolean isReady = false;
    private final VoltageSensor vs;
    private final PIDF pid = new PIDF();

    public Shooter(HardwareMap hw){
        this.shooterMotor = hw.get(DcMotorEx.class, "shooter");
        this.shooterMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        this.shooterMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        this.vs = hw.get(VoltageSensor.class, "Control Hub");
    }

    public void run(boolean thing){
        if(thing){
            //double power = pid.calculate(this.shooterMotor.getVelocity(), this.speed, this.vs.getVoltage());
            double power = speed;
            if (reverse) {power *= -1;}
            this.shooterMotor.setPower(power);
        }else{
            this.shooterMotor.setPower(0);
        }
        //this.isReady = this.shooterMotor.getVelocity() < speed;
    }

    public void setReverse(boolean reverse) {
        this.reverse = reverse;
    }

    public void setPV(double p, double v) {
        this.pid.setPV(p,v);
    }

    @Override
    public String getName() {
        return "Flywheel";
    }

    @Override
    public void updateTelemetry(Telemetry telemetry) {
        telemetry.addLine("------- Shooter -------");
        telemetry.addData("Shooter RPM", this.shooterMotor.getVelocity());
        telemetry.addData("Shooter is ready?", this.isReady);
        telemetry.addLine();
    }

}

