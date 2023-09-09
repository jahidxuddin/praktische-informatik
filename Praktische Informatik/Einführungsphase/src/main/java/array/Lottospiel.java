package main.java.array;

import java.util.Scanner;

/*
  Lottospiel
*/

public class Lottospiel {
    static Scanner scanner = new Scanner(System.in);

    double virtuellesGeldKonto;

    int[] getippteZahlen = new int[6];
    int[] gezogeneZahlen = new int[6];

    int anzahlTreffer;

    int[] gewinnausschüttungen = { 0, 3, 10, 500, 10_000, 1_000_000 };

    public void titel() {
        System.out.println("*********************************************");
        System.out.println("                 Lottospiel                  ");
        System.out.println("*********************************************\n");
    }

    public void eingabe() {
        System.out.print("Wie viel möchtest du auf dein Geldkonto einzahlen? ");
        virtuellesGeldKonto = scanner.nextDouble();
        for (int i = 0; i < getippteZahlen.length; i++) {
            System.out.print("Eingabe " + (i + 1) + ". Zahl: ");
            int eingabeZahl = scanner.nextInt();
            if (eingabeZahl < 1 || eingabeZahl > 49) {
                System.out.println("Bitte nur Zahlen zwischen 1 und 49 eingeben!");
                i -= 1;
                continue;
            }
            boolean hatDoppelteZahlEingegeben = false;
            for (int j = 0; j < getippteZahlen.length; j++) {
                if (getippteZahlen[j] == eingabeZahl) {
                    System.out.println("\n***Fehler***");
                    System.out.println(" Sie haben eine doppelte Zahl eingegeben!");
                    hatDoppelteZahlEingegeben = true;
                    break;
                }
            }
            if (hatDoppelteZahlEingegeben) {
                i -= 1;
                continue;
            }
            getippteZahlen[i] = eingabeZahl;
        }
    }

    public void ziehungZufallszahlen() {
        for (int i = 0; i < gezogeneZahlen.length; i++) {
            int zufallszahl = (int) ((49 - 1 + 1) * Math.random() + 1);
            boolean hatDoppelteZufallszahl = false;
            for (int j = 0; j < gezogeneZahlen.length; j++) {
                if (gezogeneZahlen[j] == zufallszahl) {
                    hatDoppelteZufallszahl = true;
                    break;
                }
            }
            if (hatDoppelteZufallszahl) {
                i -= 1;
                continue;
            }
            gezogeneZahlen[i] = zufallszahl;
            virtuellesGeldKonto -= 1.50;
        }
    }

    public void sortierung(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 0; j < arr.length - 1 - i; j++) {
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
    }

    public void ermittlungDerRichtigenZahlen() {
        for (int i = 0; i < getippteZahlen.length; i++) {
            for (int j = 0; j < gezogeneZahlen.length; j++) {
                if (getippteZahlen[i] == gezogeneZahlen[i]) {
                    anzahlTreffer += 1;
                    break;
                }
            }
        }
    }

    public void verarbeitung() {
        ziehungZufallszahlen();
        sortierung(getippteZahlen);
        sortierung(gezogeneZahlen);
        ermittlungDerRichtigenZahlen();
    }

    public void ausgabe() {
        System.out.println("\ngetippte Zahlen:");
        for (int i = 0; i < getippteZahlen.length; i++) {
            System.out.println((i + 1) + ". getippte Zahl: " + getippteZahlen[i]);
        }
        System.out.println("\ngezogene Zahlen:");
        for (int i = 0; i < gezogeneZahlen.length; i++) {
            System.out.println((i + 1) + ". gezogene Zahl: " + gezogeneZahlen[i]);
        }
        System.out.println("\nSie haben " + anzahlTreffer + " richtige Zahl"
                + (anzahlTreffer > 1 || anzahlTreffer == 0 ? "en." : "."));
        if (anzahlTreffer > 0) {
            virtuellesGeldKonto += gewinnausschüttungen[anzahlTreffer - 1];
            System.out.println("\nSie haben " + gewinnausschüttungen[anzahlTreffer - 1] + "€ gewonnen!");
        }
    }

    public static void main(String[] args) {
        Lottospiel lottospiel = new Lottospiel();
        lottospiel.titel();
        char loop = 'J';
        while (loop == 'J' || loop == 'j') {
            lottospiel.eingabe();
            lottospiel.verarbeitung();
            lottospiel.ausgabe();
            System.out.print("\nMöchten Sie noch einmal spielen? (J/N) ");
            loop = scanner.next().charAt(0);
        }
        scanner.close();
    }
}
