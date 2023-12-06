package dev.ju.schifffahrt;

public class Motorschiff extends Schiff {
    private double motorleistung;

    public Motorschiff(String name, double tonnage, double motorleistung) {
        super(name, tonnage);
        this.motorleistung = motorleistung;
    }

    public double getMotorleistung() {
        return motorleistung;
    }
}
