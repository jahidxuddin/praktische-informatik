package main.java.fallunterscheidungen;

import java.text.DecimalFormat;
import java.util.Scanner;

/*
    Berechnung des Notendurchschnitts
*/

public class Notenberechnung2 {
    double maximalePunktezahl;
    double erreichtePunktezahl;

    double noteProzentual;
    int notePunktesystem;

    DecimalFormat formatter = new DecimalFormat("#.#");

    public void titel() {
        System.out.println("*********************************************************");
        System.out.println("\t\t    Notenberechnung");
        System.out.println("*********************************************************\n");
    }

    public void eingabe() { // Abfrage der geschriebenen Noten
        Scanner scanner = new Scanner(System.in);

        System.out.println("Eingabe:");
        System.out.println("---------------------------------------------------------");

        System.out.print("Wie viele Punkte konnten Sie maximal erreichten? ");
        maximalePunktezahl = scanner.nextDouble();

        System.out.print("Wie viele Punkte haben Sie erreicht? ");
        erreichtePunktezahl = scanner.nextDouble();

        scanner.close();
    }

    public void berechnen() { // Berechnung des Notendurchschnitts
        /*
         * maximalePunktezahl <-> 100%
         * 1 <-> 100% / maximalePunktezahl
         * erreichtePunktezahl <-> 100% / maximalePunktezahl * erreichtePunktezahl
         */
        noteProzentual = 100 / maximalePunktezahl * erreichtePunktezahl;

        if (noteProzentual >= 95.0)
            notePunktesystem = 15;
        else if (noteProzentual >= 90.0)
            notePunktesystem = 14;
        else if (noteProzentual >= 85.0)
            notePunktesystem = 13;
        else if (noteProzentual >= 80.0)
            notePunktesystem = 12;
        else if (noteProzentual >= 75.0)
            notePunktesystem = 11;
        else if (noteProzentual >= 70.0)
            notePunktesystem = 10;
        else if (noteProzentual >= 65.0)
            notePunktesystem = 9;
        else if (noteProzentual >= 60.0)
            notePunktesystem = 8;
        else if (noteProzentual >= 55.0)
            notePunktesystem = 7;
        else if (noteProzentual >= 50.0)
            notePunktesystem = 6;
        else if (noteProzentual >= 45.0)
            notePunktesystem = 5;
        else if (noteProzentual >= 40.0)
            notePunktesystem = 4;
        else if (noteProzentual >= 33.0)
            notePunktesystem = 3;
        else if (noteProzentual >= 27.0)
            notePunktesystem = 2;
        else if (noteProzentual >= 20.0)
            notePunktesystem = 1;
        else if (noteProzentual < 20.0)
            notePunktesystem = 0;
    }

    public void ausgabe() { // Ausgabe der Ergebnisse
        System.out.println("\nAusgabe:");
        System.out.println("---------------------------------------------------------");
        System.out.println("Sie haben " + erreichtePunktezahl + " von " + maximalePunktezahl + " Punkten erreicht!");
        System.out.println("\nDas entspricht " + formatter.format(noteProzentual) + " Prozent und damit der Note: "
                + notePunktesystem + " Punkte.");
    }

    public static void main(String[] args) {
        Notenberechnung2 notenberechnung2 = new Notenberechnung2();
        notenberechnung2.titel();
        notenberechnung2.eingabe();
        notenberechnung2.berechnen();
        notenberechnung2.ausgabe();
    }
}