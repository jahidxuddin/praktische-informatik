package fallunterscheidungen;

import java.text.DecimalFormat;
import java.util.Scanner;

/*
    Berechnung des Body-Mass-Indexes
*/

public class BodyMassIndex {
    double gewicht;
    double körpergröße;

    double bodyMassIndex;
    String gewichtsLage;

    DecimalFormat formatter = new DecimalFormat("#.##");

    public void titel() {
        System.out.println("************************************************");
        System.out.println("\t\tBody-Mass-Index");
        System.out.println("************************************************\n");
    }

    public void eingabe() { // Abfrage der benötigten Daten
        Scanner scanner = new Scanner(System.in);

        System.out.print("Bitte geben Sie ihr Gewicht (in kg) ein: ");
        gewicht = scanner.nextDouble();

        System.out.print("Bitte geben Sie ihre Körpergröße (in m) ein: ");
        körpergröße = scanner.nextDouble();

        scanner.close();
    }

    public void berechnen() { // Berechnung des Body-Mass-Indexes
        bodyMassIndex = gewicht / Math.pow(körpergröße, 2);
        if (bodyMassIndex < 19) {
            gewichtsLage = "Sie haben Untergewicht!";
        } else if (bodyMassIndex < 25) {
            gewichtsLage = "Sie haben Normalgewicht!";
        } else if (bodyMassIndex < 30) {
            gewichtsLage = "Sie haben Übergewicht!";
        } else {
            gewichtsLage = "Sie haben starkes Übergewicht!";
        }
    }

    public void ausgabe() { // Ausgabe der Ergebnisse
        System.out.println("\nIhr Body-Mass-Index (BMI) beträgt: " + formatter.format(bodyMassIndex));
        System.out.println(gewichtsLage);
    }

    public static void main(String[] args) {
        BodyMassIndex bodyMassIndex = new BodyMassIndex();
        bodyMassIndex.titel();
        bodyMassIndex.eingabe();
        bodyMassIndex.berechnen();
        bodyMassIndex.ausgabe();
    }
}