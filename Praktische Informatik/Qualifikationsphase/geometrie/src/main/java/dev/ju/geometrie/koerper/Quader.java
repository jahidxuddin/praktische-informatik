package dev.ju.geometrie.koerper;

/**
 *
 * Diese Klasse dient zur Darstellung eines Quaders
 * @version 1.0 from 13.09.2023
 * @author Jahid Uddin
 */

public class Quader {
    private double a;
    private double b;
    private double c;

    public double getA() {
        return a;
    }

    public void setA(double a) {
        this.a = a;
    }

    public double getB() {
        return b;
    }

    public void setB(double b) {
        this.b = b;
    }

    public double getC() {
        return c;
    }

    public void setC(double c) {
        this.c = c;
    }

    public double berechneOberflaeche() {
        return (2 * a * b) + (2 * a * c) + (2 * b * c);
    }

    public double berechneOberflaeche(double a, double b, double c) {
        return (2 * a * b) + (2 * a * c) + (2 * b * c);
    }

    public double berechneVolumen() {
        return a * b * c;
    }

    public double berechneVolumen(double a, double b, double c) {
        return a * b * c;
    }
}
