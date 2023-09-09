package main.java.array;

import java.util.Scanner;

/*
  Mathe-Trainer
*/

public class MatheTrainer {
    static Scanner scanner = new Scanner(System.in);

    int[] zufallszahlen;
    int lösung;

    int grundrechenart;

    int eingabeErgebnis;

    public void titel() {
        System.out.println("*********************************************");
        System.out.println("                 Mathe-Trainer               ");
        System.out.println("*********************************************\n");
    }

    public void eingabe() {
        System.out.print("Welche Grundrechenart möchten Sie trainieren?\n(1: Addition, 2: Multiplikation): ");
        grundrechenart = scanner.nextInt();
        System.out.print("Mit wie vielen Zahlen möchten Sie rechnen? ");
        zufallszahlen = new int[scanner.nextInt()];
        ziehungZufallszahlen();
        berechneLösung();
        if (grundrechenart == 1) {
            System.out.println("\nAddieren Sie die folgenden Zahlen:");
        } else if (grundrechenart == 2) {
            System.out.println("\nMultiplizieren Sie die folgenden Zahlen:");
        } else {
            System.exit(501);
        }
        for (int i = 0; i < zufallszahlen.length; i++) {
            System.out.println("Zahl " + (i + 1) + ": " + zufallszahlen[i]);
        }
        System.out.print("\nEingabe Ergebnis: ");
        eingabeErgebnis = scanner.nextInt();
    }

    public void ziehungZufallszahlen() {
        for (int i = 0; i < zufallszahlen.length; i++) {
            // max: 100 min: 0
            zufallszahlen[i] = (int) ((100 - 0 + 1) * Math.random() + 0);
        }
    }

    public void berechneLösung() {
        for (int i = 0; i < zufallszahlen.length; i++) {
            if (grundrechenart == 1) {
                lösung += zufallszahlen[i];
                continue;
            }
            if (grundrechenart == 2) {
                lösung *= zufallszahlen[i];
            }
        }
    }

    public String generiereLösungsweg() {
        String lösungsweg = "";
        String operator = grundrechenart == 1 ? " + " : " * ";
        for (int i = 0; i < zufallszahlen.length; i++) {
            lösungsweg += zufallszahlen[i] + (i == zufallszahlen.length ? " = " : operator);
        }
        lösungsweg += lösung + " und nicht " + eingabeErgebnis + "!";
        return lösungsweg;
    }

    public void ausgabe() {
        System.out.println("Auswertung:");
        if (eingabeErgebnis == lösung) {
            System.out.println("Wow! Much impressiv! Much wow!");
            return;
        }
        System.out.println("Sie haben sich verrechnet!");
        System.out.println(generiereLösungsweg());
    }

    public static void main(String[] args) {
        MatheTrainer matheTrainer = new MatheTrainer();
        matheTrainer.titel();
        char loop = 'J';
        while (loop == 'J' || loop == 'j') {
            matheTrainer.eingabe();
            matheTrainer.ausgabe();
            System.out.print("\nMöchten Sie die Eingabe wiederholen? (J/N) ");
            loop = scanner.next().charAt(0);
        }
        scanner.close();
    }
}
