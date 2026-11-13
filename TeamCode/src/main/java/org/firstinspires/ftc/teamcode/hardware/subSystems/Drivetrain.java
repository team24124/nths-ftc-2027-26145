package org.firstinspires.ftc.teamcode.hardware.subSystems;

import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.utility.interfaces.SubsystemBase;


//short trem fix by not using pedro fix later
public class Drivetrain implements SubsystemBase {
    public final DcMotorEx fl;
    public final DcMotorEx fr;
    public final DcMotorEx bl;
    public final DcMotorEx br;
    public Drivetrain(HardwareMap HM){
        this.fl = HM.get(DcMotorEx.class, "fl");
        this.fr = HM.get(DcMotorEx.class, "fr");
        this.bl = HM.get(DcMotorEx.class, "bl");
        this.br = HM.get(DcMotorEx.class, "br");
        this.fl.setDirection(DcMotorSimple.Direction.REVERSE);
        this.fr.setDirection(DcMotorSimple.Direction.FORWARD);
        this.bl.setDirection(DcMotorSimple.Direction.REVERSE);
        this.br.setDirection(DcMotorSimple.Direction.FORWARD);
    }

    public void run(double x, double y, double rx) {
        double denominator = Math.max(Math.abs(y) + Math.abs(x) + Math.abs(rx), 1) * 1.5;
        double frontLeftPower = (y + x + rx) / denominator;
        double backLeftPower = (y - x + rx) / denominator;
        double frontRightPower = (y - x - rx) / denominator;
        double backRightPower = (y + x - rx) / denominator;

        fl.setPower(frontLeftPower);
        bl.setPower(backLeftPower);
        fr.setPower(frontRightPower);
        br.setPower(backRightPower);
    }

    @Override
    public String getName() {
        return "backupDrivetrain";
    }
}
