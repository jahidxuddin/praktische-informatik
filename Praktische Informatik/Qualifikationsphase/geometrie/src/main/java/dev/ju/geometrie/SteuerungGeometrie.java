package dev.ju.geometrie;

import dev.ju.geometrie.formen.Kreis;
import dev.ju.geometrie.formen.Rechteck;
import dev.ju.geometrie.koerper.Kugel;
import dev.ju.geometrie.koerper.Quader;

import java.util.Scanner;

public class SteuerungGeometrie {
    private Rechteck rechteck;
    private Kreis kreis;
    private Kugel kugel;
    private Quader quader;

    public void eingabe() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Bitte geben Sie die Länge der Seite A des Rechteckes ein: ");
        double rechteckSeiteA = scanner.nextDouble();
        System.out.print("Bitte geben Sie die Länge der Seite B des Rechteckes ein: ");
        double rechteckSeiteB = scanner.nextDouble();

        System.out.print("\nBitte geben Sie den Radius des Kreies ein: ");
        double kreisRadius = scanner.nextDouble();

        System.out.print("\nBitte geben Sie den Radius der Kugel ein: ");
        double kugelRadius = scanner.nextDouble();

        System.out.print("\nBitte geben Sie die Länge der Seite A des Quaders ein: ");
        double quaderSeiteA = scanner.nextDouble();
        System.out.print("Bitte geben Sie die Länge der Seite B des Quaders ein: ");
        double quaderSeiteB = scanner.nextDouble();
        System.out.print("Bitte geben Sie die Länge der Seite C des Quaders ein: ");
        double quaderSeiteC = scanner.nextDouble();

        scanner.close();

        rechteck = new Rechteck();
        rechteck.setA(rechteckSeiteA);
        rechteck.setB(rechteckSeiteB);

        kreis = new Kreis();
        kreis.setRadius(kreisRadius);

        kugel = new Kugel();
        kugel.setRadius(kugelRadius);

        quader = new Quader();
        quader.setA(quaderSeiteA);
        quader.setB(quaderSeiteB);
        quader.setC(quaderSeiteC);
    }

    public void ausgabe() {
        System.out.println("\n\nFläche des Rechtecks: " + rechteck.berechneFlaeche() + " (=" + rechteck.getA() + " x " + rechteck.getB() + ")");
        System.out.println("Umfang des Rechtecks: " + rechteck.berechneUmfang());

        System.out.println("\n\nFläche des Kreises: " + kreis.berechneFlaeche() + " (Radius: " + kreis.getRadius() + ")");
        System.out.println("Umfang des Kreises: " + kreis.berechneUmfang());

        System.out.println("\n\nOberfläche der Kugel: " + kugel.berechneOberflaeche() + " (Radius: " + kugel.getRadius() + ")");
        System.out.println("Volumen der Kugel: " + kugel.berechneVolumen());

        System.out.println("\n\nOberfläche des Quaders: " + quader.berechneOberflaeche());
        System.out.println("Volumen des Quaders: " + quader.berechneVolumen() + " (=" + quader.getA() + " x " + quader.getB() + " x " + quader.getC() + ")");
    }

    public static void main(String[] args) {
        SteuerungGeometrie steuerungGeometrie = new SteuerungGeometrie();
        steuerungGeometrie.eingabe();
        steuerungGeometrie.ausgabe();
    }
}