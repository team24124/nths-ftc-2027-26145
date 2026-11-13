package org.firstinspires.ftc.teamcode.hardware.subSystems;

import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.utility.interfaces.SubsystemBase;
import org.firstinspires.ftc.teamcode.utility.interfaces.TelemetryObservable;

public class Intake implements SubsystemBase, TelemetryObservable {
    private final DcMotorEx intakemotor;
    private boolean running = false;
    private boolean reverse = false;
    private double speed = 1;

    public Intake(HardwareMap HM){
        this.intakemotor = HM.get(DcMotorEx.class, "intake");
        this.intakemotor.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.FLOAT);
        this.intakemotor.setDirection(DcMotorSimple.Direction.REVERSE);
    }

    public void run(boolean run){
        this.running = run;
        if(running){
            if(this.reverse){
                this.intakemotor.setPower(this.speed*-1);
            }else{
                this.intakemotor.setPower(this.speed);
            }
        } else {
            this.intakemotor.setPower(0);
        }
    }
    public void setSpeed(double speed) {
        this.speed = speed;
    }
    public void reverse(boolean reverse) {
        this.reverse = reverse;
    }
    public void manualSetPowerOverride(double p) {
        this.intakemotor.setPower(p);
    }

    @Override
    public String getName() {
        return "Intake";
    }

    @Override
    public void updateTelemetry(Telemetry telemetry) {
        telemetry.addData("Is running?", this.running);
        telemetry.addData("Reverse?", this.reverse);
        telemetry.addLine();
    }
}
