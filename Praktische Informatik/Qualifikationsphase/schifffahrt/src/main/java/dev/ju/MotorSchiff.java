package dev.ju;

public class MotorSchiff extends Schiff {
    private double motorleistung;

    public MotorSchiff(String name, double tonnage, double motorleistung) {
        super(name, tonnage);
        this.motorleistung = motorleistung;
    }
}
