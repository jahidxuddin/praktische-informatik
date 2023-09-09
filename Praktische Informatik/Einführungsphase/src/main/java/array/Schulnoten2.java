package main.java.array;

import java.util.Scanner;

/*
    Notenerfassung
*/

public class Schulnoten2 {
    static Scanner scanner = new Scanner(System.in);

    final String[] FÄCHER = { "Deutsch", "Englisch", "Geschichte" };
    final String[] NOTEN_TYPEN = {
            "1. Klausur",
            "2. Klausur",
            "schriftl. Note",
            "mündliche Note",
            "Zeugnisnote"
    };

    String schülername;

    double[][] noten = new double[5][3];

    public void titel() {
        System.out.println("*********************************************");
        System.out.println("                 Notenerfassung              ");
        System.out.println("*********************************************\n");
    }

    public void eingabe() {
        System.out.print("Schülername: ");
        schülername = scanner.next();
        scanner.next();
        for (int i = 0; i < FÄCHER.length; i++) {
            System.out.println("Fach: " + FÄCHER[i]);
            for (int j = 0; j < noten.length; j++) {
                if (j == 2 || j == 4) {
                    continue;
                }
                System.out.print("Note: " + NOTEN_TYPEN[j] + ": ");
                int notenEingabe = scanner.nextInt();
                if (notenEingabe > 15 || notenEingabe < 0) {
                    System.out.println("\nEs können nur gültige Notenpunkte eingegeben werden!");
                    j -= 1;
                    continue;
                }
                noten[j][i] = notenEingabe;
            }
            System.out.println();
        }
    }

    public void verarbeitung() {
        for (int i = 0; i < noten[2].length; i++) {
            for (int j = 0; j < 2; j++) {
                noten[2][i] += noten[j][i];
            }
            noten[2][i] = Math.round(noten[2][i] /= 2);
        }
        for (int i = 0; i < noten[4].length; i++) {
            noten[4][i] += noten[2][i] + noten[3][i];
            noten[4][i] = Math.round(noten[4][i] /= 2);
        }
    }

    public void ausgabe() {
        System.out.println("\nNotenübersicht für " + schülername + "\n");
        System.out.println("\t\t\tDeutsch\t\tEnglisch\tGeschichte");
        System.out.println(
                "==========================================================================");
        for (int i = 0; i < noten.length; i++) {
            System.out
                    .println(NOTEN_TYPEN[i] + "\t\t " + noten[i][0] + "\t\t " + noten[i][1] + "\t\t " + noten[i][2]);
            if (i == 0) {
                continue;
            }
            if (i != 4) {
                System.out
                        .println("--------------------------------------------------------------------------");
            }
        }
        System.out.println(
                "==========================================================================");
    }

    public static void main(String[] args) {
        Schulnoten2 schulnoten2 = new Schulnoten2();
        schulnoten2.titel();
        char loop = 'J';
        while (loop == 'J' || loop == 'j') {
            schulnoten2.eingabe();
            schulnoten2.verarbeitung();
            schulnoten2.ausgabe();
            System.out.print("\nMöchten Sie das Programm wiederholen? (J/N) ");
            loop = scanner.next().charAt(0);
        }
        scanner.close();
    }
}
