package org.firstinspires.ftc.teamcode.hardware.subsystems;

import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.utility.interfaces.SubsystemBase;
import org.firstinspires.ftc.teamcode.utility.interfaces.TelemetryObservable;


//short trem fix by not using pedro fix later
public class Drivetrain implements SubsystemBase, TelemetryObservable {
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
        double denominator = Math.max(Math.abs(y) + Math.abs(x) + Math.abs(rx), 1);
        double frontLeftPower = (y + x + rx) / denominator;
        double backLeftPower = (y - x + rx) / denominator;
        double frontRightPower = (y - x - rx) / denominator;
        double backRightPower = (y + x - rx) / denominator;

        fl.setPower(frontLeftPower);
        bl.setPower(backLeftPower);
        fr.setPower(frontRightPower);
        br.setPower(backRightPower);
    }

    public void setPowers(double fl, double fr, double bl, double br) {
        this.fl.setPower(fl);
        this.fr.setPower(fr);
        this.bl.setPower(bl);
        this.br.setPower(br);
    }

    @Override
    public String getName() {
        return "backupDrivetrain";
    }

    @Override
    public void updateTelemetry(Telemetry telemetry) {
        telemetry.addData("FL speed", fl.getPower());
        telemetry.addData("FR speed", fr.getPower());
        telemetry.addData("BL speed", bl.getPower());
        telemetry.addData("BR speed", br.getPower());
        telemetry.addLine();
    }
}
