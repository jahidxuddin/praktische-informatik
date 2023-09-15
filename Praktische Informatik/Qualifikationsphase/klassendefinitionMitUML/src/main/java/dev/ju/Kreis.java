package dev.ju;

/**
 *
 * Diese Klasse dient zur Darstellung eines Kreises
 * @version 1.0 from 13.09.2023
 * @author Jahid Uddin
 */

public class Kreis {
    private double radius;

    public double getRadius() {
        return radius;
    }

    public void setRadius(double radius) {
        this.radius = radius;
    }

    public double berechneFlaeche() {
        return Math.PI * Math.pow(radius, 2);
    }

    public double berechneFlaeche(double radius) {
        return Math.PI * Math.pow(radius, 2);
    }

    public double berechneUmfang() {
        return 2 * radius * Math.PI;
    }

    public double berechneUmfang(double radius) {
        return 2 * radius * Math.PI;
    }
}
