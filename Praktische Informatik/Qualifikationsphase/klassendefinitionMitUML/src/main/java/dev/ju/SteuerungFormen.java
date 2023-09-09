package dev.ju;

import java.util.Scanner;

public class SteuerungFormen {
    private Rechteck rechteck;

    public void eingabe() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Bitte geben Sie die Länge der Seite A ein: ");
        double seiteA = scanner.nextDouble();
        System.out.print("Bitte geben Sie die Länge der Seite B ein: ");
        double seiteB = scanner.nextDouble();

        rechteck = new Rechteck();
        rechteck.setA(seiteA);
        rechteck.setB(seiteB);

        scanner.close();
    }

    public void ausgabe() {
        System.out.println("\n\nFläche des Rechtecks: " + rechteck.berechneFlaeche() + " (=" + rechteck.getA() + " x " + rechteck.getB() + ")");
        System.out.println("Umfang des Rechtecks: " + rechteck.berechneUmfang());
    }

    public static void main(String[] args) {
        SteuerungFormen steuerungFormen = new SteuerungFormen();
        steuerungFormen.eingabe();
        steuerungFormen.ausgabe();
    }
}
