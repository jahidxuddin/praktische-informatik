package dev.ju;

/**
 *
 * Diese Klasse dient zur Darstellung einer Kugel
 * @version 1.0 from 13.09.2023
 * @author Jahid Uddin
 */

public class Kugel {
    private double radius;

    public double getRadius() {
        return radius;
    }

    public void setRadius(double radius) {
        this.radius = radius;
    }

    public double berechneOberflaeche() {
        return 4 * Math.PI * Math.pow(radius, 2);
    }

    public double berechneOberflaeche(double radius) {
        return 4 * Math.PI * Math.pow(radius, 2);
    }

    public double berechneVolumen() {
        return (4 * Math.PI * Math.pow(radius, 3)) / 3;
    }

    public double berechneVolumen(double radius) {
        return (4 * Math.PI * Math.pow(radius, 3)) / 3;
    }
}