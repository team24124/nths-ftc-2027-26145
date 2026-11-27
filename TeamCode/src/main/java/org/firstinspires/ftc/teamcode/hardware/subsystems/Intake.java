package org.firstinspires.ftc.teamcode.hardware.subsystems;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.utility.interfaces.SubsystemBase;
import org.firstinspires.ftc.teamcode.utility.interfaces.TelemetryObservable;

@Config
public class Intake implements SubsystemBase, TelemetryObservable {
    private final DcMotorEx intakemotor;
    private final Servo gate;
    private boolean running = false;
    private boolean reverse = false;
    private double speed = 1;
    private boolean gateOpen = false;
    public static double pos = 0;

    public Intake(HardwareMap HM){
        this.intakemotor = HM.get(DcMotorEx.class, "intake");
        this.intakemotor.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.FLOAT);
        this.intakemotor.setDirection(DcMotorSimple.Direction.REVERSE);
        this.gate = HM.get(Servo.class, "gate");
        this.gate.setPosition(1);
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

    public void toggleGate(boolean runGate) {
        if (runGate) {
            this.gate.setPosition(0.7);
        } else {
            this.gate.setPosition(1);
        }
        this.gateOpen = runGate;
    }

    public void setGate() {
        this.gate.setPosition(this.pos);
    }
    public void setPower(double p) {
        this.intakemotor.setPower(0);
    }
    public void reverse(boolean reverse) {
        this.reverse = reverse;
    }

    @Override
    public String getName() {
        return "Intake";
    }

    @Override
    public void updateTelemetry(Telemetry telemetry) {
        telemetry.addData("Is running?", this.running);
        telemetry.addData("Reverse?", this.reverse);
        telemetry.addData("Gate position", this.gate.getPosition());
        telemetry.addData("Gate state", this.gateOpen);
        telemetry.addLine();
    }
}
