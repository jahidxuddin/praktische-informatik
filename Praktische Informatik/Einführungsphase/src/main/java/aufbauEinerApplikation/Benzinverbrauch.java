package main.java.aufbauEinerApplikation;

import java.text.DecimalFormat;
import java.util.Scanner;

/*
    Berechnung des Benzinvebrauches
*/

public class Benzinverbrauch {
    int letzterKilometerstand;
    int aktuellerKilometerstand;
    int getankeLiter;

    float zurückgelegteKilometer;
    String benzinverbauch;

    public void titel() {
        System.out.println("****************************************************************");
        System.out.println("\t\tBerechnung Benzinverbrauch");
        System.out.println("****************************************************************");
    }

    public void eingabe() { // Abfrage der benötigten Daten
        Scanner scanner = new Scanner(System.in);

        System.out.println("\nEingabe:");

        System.out.print("Bitte geben Sie den Kilometerstand beim letzten Tanken ein: ");
        letzterKilometerstand = scanner.nextInt();

        System.out.print("Bitte geben Sie den aktuellen Kilometerstand ein: ");
        aktuellerKilometerstand = scanner.nextInt();

        System.out.print("Bitte geben Sie die Menge der getankten Liter Benzin ein: ");
        getankeLiter = scanner.nextInt();

        scanner.close();
    }

    public void berechnen() { // Berechnung des Benzinverbrauchs
        zurückgelegteKilometer = aktuellerKilometerstand - letzterKilometerstand;

        DecimalFormat formatter = new DecimalFormat("#.##");
        benzinverbauch = formatter.format(getankeLiter * 100.00f / zurückgelegteKilometer);
    }

    public void ausgabe() { // Ausgabe des Benzinverbauchs
        System.out.println("\nAuswertung:");
        System.out.println("Der durchschnittliche Benzinverbrauch beträgt " + benzinverbauch + " Liter");
        System.out.println("pro gefahrene 100 Kilometer, wenn Sie " + zurückgelegteKilometer + " Kilometer gefahren");
        System.out.println("sind und " + (float) getankeLiter + " Liter getankt haben.");
    }

    public static void main(String[] args) {
        Benzinverbrauch benzinverbrauch = new Benzinverbrauch();
        benzinverbrauch.titel();
        benzinverbrauch.eingabe();
        benzinverbrauch.berechnen();
        benzinverbrauch.ausgabe();
    }
}
