package org.firstinspires.ftc.teamcode.utility.interfaces;

public interface SubsystemBase {

    default void periodic(){}

    String getName();
}
