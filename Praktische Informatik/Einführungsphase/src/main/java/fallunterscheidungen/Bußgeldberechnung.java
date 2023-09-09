package main.java.fallunterscheidungen;

import java.text.DecimalFormat;
import java.util.Scanner;

/*
    Berechnung des Bußgeldes
*/

public class Bußgeldberechnung {
    String eingabeAuswahl;
    double erlaubteHöchstgeschwindigkeit;
    double gefahreneGeschwindigkeit;

    double bußgeld;
    int strafpunkte;
    int fahrverbot;

    double geschwindigkeitsübertretung;
    String konsequenzen;

    DecimalFormat formatter = new DecimalFormat("#.#");

    public void titel() {
        System.out.println("*********************************************************");
        System.out.println("\t\t    Bußgeldberechnung");
        System.out.println("*********************************************************\n");
    }

    public void eingabe() { // Abfrage der benötigten Daten
        Scanner scanner = new Scanner(System.in);

        System.out.println("Eingabe:");
        System.out.println("*********************************************************");

        System.out.println("Wo haben Sie die Geschwindigkeitsübertretung begangen? ");
        System.out.println("1: innerhalb geschlossener Ortschaften,\n2: außerhalb geschlossener Ortschaften");

        System.out.print("Bitte geben Sie die entsprechende Kennziffer ein: ");
        eingabeAuswahl = scanner.next();

        System.out.print("Wie hoch ist die erlaubte Höchstgeschwindigkeit (in km/h)\nauf dieser Straße? ");
        erlaubteHöchstgeschwindigkeit = scanner.nextInt();

        System.out.print("Wie schnell sind Sie gefahren in (in km/h)? ");
        gefahreneGeschwindigkeit = scanner.nextInt();

        scanner.close();
    }

    public void berechnen() { // Berechnung der Ergebnisse
        if (gefahreneGeschwindigkeit < 100) {
            gefahreneGeschwindigkeit = gefahreneGeschwindigkeit - 3;
        } else if (gefahreneGeschwindigkeit > 100) {
            gefahreneGeschwindigkeit = gefahreneGeschwindigkeit - (gefahreneGeschwindigkeit / 100 * 3);
        }

        geschwindigkeitsübertretung = gefahreneGeschwindigkeit - erlaubteHöchstgeschwindigkeit;

        switch (eingabeAuswahl) {
            case "1":
                if (geschwindigkeitsübertretung > 70) {
                    konsequenzen = "Sie müssen 800,00 Euro bezahlen, bekommen 2 Strafpunkte\nund 3 Monate Fahrverbot.";
                } else if (geschwindigkeitsübertretung >= 60) {
                    konsequenzen = "Sie müssen 700,00 Euro bezahlen, bekommen 2 Strafpunkte\nund 3 Monate Fahrverbot.";
                } else if (geschwindigkeitsübertretung >= 50) {
                    konsequenzen = "Sie müssen 600,00 Euro bezahlen, bekommen 2 Strafpunkte\nund 2 Monate Fahrverbot.";
                } else if (geschwindigkeitsübertretung >= 40) {
                    konsequenzen = "Sie müssen 400,00 Euro bezahlen, bekommen 2 Strafpunkte\nund 1 Monat Fahrverbot.";
                } else if (geschwindigkeitsübertretung >= 30) {
                    konsequenzen = "Sie müssen 260,00 Euro bezahlen, bekommen 2 Strafpunkte\nund 1 Monat Fahrverbot.";
                } else if (geschwindigkeitsübertretung >= 25) {
                    konsequenzen = "Sie müssen 180,00 Euro bezahlen, bekommen 1 Strafpunkt.";
                } else if (geschwindigkeitsübertretung >= 20) {
                    konsequenzen = "Sie müssen 115,00 Euro bezahlen, bekommen 1 Strafpunkt.";
                } else if (geschwindigkeitsübertretung >= 15) {
                    konsequenzen = "Sie müssen 70,00 Euro bezahlen, bekommen 1 Strafpunkt.";
                } else if (geschwindigkeitsübertretung >= 10) {
                    konsequenzen = "Sie müssen 50,00 Euro bezahlen.";
                } else if (geschwindigkeitsübertretung < 10) {
                    konsequenzen = "Sie müssen 30,00 Euro bezahlen.";
                }
                break;
            case "2":
                if (geschwindigkeitsübertretung > 70) {
                    konsequenzen = "Sie müssen 700,00 Euro bezahlen, bekommen 2 Strafpunkte\nund 3 Monate Fahrverbot.";
                } else if (geschwindigkeitsübertretung >= 60) {
                    konsequenzen = "Sie müssen 600,00 Euro bezahlen, bekommen 2 Strafpunkte\nund 2 Monate Fahrverbot.";
                } else if (geschwindigkeitsübertretung >= 50) {
                    konsequenzen = "Sie müssen 480,00 Euro bezahlen, bekommen 2 Strafpunkte\nund 1 Monate Fahrverbot.";
                } else if (geschwindigkeitsübertretung >= 40) {
                    konsequenzen = "Sie müssen 320,00 Euro bezahlen, bekommen 2 Strafpunkte\nund 1 Monat Fahrverbot.";
                } else if (geschwindigkeitsübertretung >= 30) {
                    konsequenzen = "Sie müssen 200,00 Euro bezahlen, bekommen 2 Strafpunkte\nund 1 Monat Fahrverbot.";
                } else if (geschwindigkeitsübertretung >= 25) {
                    konsequenzen = "Sie müssen 150,00 Euro bezahlen, bekommen 1 Strafpunkt.";
                } else if (geschwindigkeitsübertretung >= 20) {
                    konsequenzen = "Sie müssen 100,00 Euro bezahlen, bekommen 1 Strafpunkt.";
                } else if (geschwindigkeitsübertretung >= 15) {
                    konsequenzen = "Sie müssen 60,00 Euro bezahlen.";
                } else if (geschwindigkeitsübertretung >= 10) {
                    konsequenzen = "Sie müssen 40,00 Euro bezahlen.";
                } else if (geschwindigkeitsübertretung < 10) {
                    konsequenzen = "Sie müssen 20,00 Euro bezahlen.";
                }
                break;
            default:
                System.out.println("\nSie haben eine falsche Kennziffer eingegeben! Das Programm wird abgebrochen...");
                System.exit(500);
        }
    }

    public void ausgabe() { // Ausgabe der Ergenisse
        System.out.println("\nAusgabe:");
        System.out.println("*********************************************************");
        System.out.println("Sie haben sich nicht an die Vorschriften gehalten und\nsind außerorts "
                + formatter.format(geschwindigkeitsübertretung) + " km/h zu schnell gefahren.\n");
        System.out.println(konsequenzen);
    }

    public static void main(String[] args) {
        Bußgeldberechnung bußgeldberechnung = new Bußgeldberechnung();
        bußgeldberechnung.titel();
        bußgeldberechnung.eingabe();
        bußgeldberechnung.berechnen();
        bußgeldberechnung.ausgabe();
    }
}