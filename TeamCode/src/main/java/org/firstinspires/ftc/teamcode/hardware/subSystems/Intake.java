package org.firstinspires.ftc.teamcode.subSystems;

import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.utility.interfaces.SubsystemShoot;

public class Intake implements SubsystemShoot {
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
        }
    }
    public void setSpeed(double speed) {
        this.speed = speed;
    }
    public void setReverse(boolean reverse) {
        this.reverse = reverse;
    }
    public void manualSetPowerOverride(double p) {
        this.intakemotor.setPower(p);
    }

    @Override
    public String getName() {
        return "Intake";
    }


}
