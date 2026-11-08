package org.firstinspires.ftc.teamcode.hardware.subSystems;

import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.utility.interfaces.SubsystemBase;


//short trem fix by not using pedro fix latery
public class backupDrivetrain implements SubsystemBase {
    private final DcMotorEx fl;
    private final DcMotorEx fr;
    private final DcMotorEx bl;
    private final DcMotorEx br;
    public backupDrivetrain(HardwareMap HM){
        this.fl = HM.get(DcMotorEx.class, "fl");
        this.fr = HM.get(DcMotorEx.class, "fr");
        this.bl = HM.get(DcMotorEx.class, "bl");
        this.br = HM.get(DcMotorEx.class, "br");
        this.fl.setDirection(DcMotorSimple.Direction.REVERSE);
        this.fr.setDirection(DcMotorSimple.Direction.FORWARD);
        this.bl.setDirection(DcMotorSimple.Direction.REVERSE);
        this.br.setDirection(DcMotorSimple.Direction.FORWARD);

    }

    public void run(boolean hi, String name){
        if(hi){
            if(name.equals("bl")) {
                this.bl.setPower(1);
            } else if (name.equals("br")) {
                this.br.setPower(1);
            } else if (name.equals("fr")) {
                this.fr.setPower(1);
            } else if (name.equals("fl")) {
                this.fl.setPower(1);
            }
        }else {
            this.bl.setPower(0);
            this.br.setPower(0);
            this.fl.setPower(0);
            this.fr.setPower(0);
        }
    }

    public void reverse(boolean hi, String name){
        if(hi){
            if(name.equals("bl")) {
                this.bl.setPower(-1);
            } else if (name.equals("br")) {
                this.br.setPower(-1);
            } else if (name.equals("fr")) {
                this.fr.setPower(-1);
            } else if (name.equals("fl")) {
                this.fl.setPower(-1);
            }
        }else {
            this.bl.setPower(0);
            this.br.setPower(0);
            this.fl.setPower(0);
            this.fr.setPower(0);
        }
    }

    @Override
    public String getName() {
        return "backupDrivetrain";
    }
}
