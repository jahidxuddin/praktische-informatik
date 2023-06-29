package main.java.fallunterscheidungen;

import java.util.Scanner;

/*
    Grundrechenarten mit zwei Zahlen
*/

public class GrundrechenartenMitZweiZahlen {
    double zahl1;
    double zahl2;
    String eingabeAuswahl;

    double ergebnis;

    public void titel() {
        System.out.println("*********************************************************");
        System.out.println("\t\t    Grundrechenarten");
        System.out.println("*********************************************************\n");
    }

    public void eingabe() { // Abfrage der geschriebenen Noten
        Scanner scanner = new Scanner(System.in);

        System.out.println("Zahleneingabe:");
        System.out.println("---------------------------------------------------------");

        System.out.print("Bitte geben Sie die 1. Zahl ein: ");
        zahl1 = scanner.nextDouble();

        System.out.print("Bitte geben Sie die 2. Zahl ein: ");
        zahl2 = scanner.nextDouble();

        System.out.println("\nAuswahl:");
        System.out.println("---------------------------------------------------------");
        System.out.println("Welche Grundrechenart möchten Sie auswählen:");
        System.out.println("1. Addition\n2. Subtraktion\n3. Multiplikation\n4. Division");

        System.out.print("Eingabe Auswahl: ");
        eingabeAuswahl = scanner.next();

        scanner.close();
    }

    public void berechnen() { // Berechnung des Ergebnisses
        switch (eingabeAuswahl) {
            case "1":
                eingabeAuswahl = "+";
                ergebnis = zahl1 + zahl2;
                break;
            case "2":
                eingabeAuswahl = "-";
                ergebnis = zahl1 - zahl2;
                break;
            case "3":
                eingabeAuswahl = "*";
                ergebnis = zahl1 * zahl2;
                break;
            case "4":
                eingabeAuswahl = "/";
                ergebnis = zahl1 / zahl2;
                break;
            default:
                System.out.println("\nSie haben eine falsche Kennziffer eingegeben! Das Programm wird abgebrochen...");
                System.exit(500);
        }
    }

    public void ausgabe() { // Ausgabe des Ergebnisses
        System.out.println("\nAusgabe Ergebnis:");
        System.out.println("---------------------------------------------------------");
        System.out.println(zahl1 + " " + eingabeAuswahl + " " + zahl2 + " = " + ergebnis);
    }

    public static void main(String[] args) {
        GrundrechenartenMitZweiZahlen grundrechenartenMitZweiZahlen = new GrundrechenartenMitZweiZahlen();
        grundrechenartenMitZweiZahlen.titel();
        grundrechenartenMitZweiZahlen.eingabe();
        grundrechenartenMitZweiZahlen.berechnen();
        grundrechenartenMitZweiZahlen.ausgabe();
    }
}