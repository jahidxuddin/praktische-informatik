package parameterübergabeAnEineMethode;

import java.util.Scanner;

/*
    Parameterübergabe mit Rückgabewert
*/

public class ParameterübergabeMitRückgabewert {
    Scanner scanner = new Scanner(System.in);

    int zahl;
    int divisor;

    public void titel() {
        System.out.println("*********************************************");
        System.out.println("            Teilbarkeits-Bestimmung          ");
        System.out.println("*********************************************\n");
    }

    public void eingabe() {
        System.out.print("Zahl: ");
        zahl = scanner.nextInt();
        System.out.print("Teiler: ");
        divisor = scanner.nextInt();
    }

    public boolean teilbarkeit(int zahl, int divisor) {
        return zahl % divisor == 0;
    }

    public int[] teilbarkeit(int zahl) {
        int[] teilbarkeitsZahlen = new int[zahl];
        for (int i = 1; i <= zahl; i++) {
            if (zahl % i == 0) {
                teilbarkeitsZahlen[i - 1] = i;
            }
        }
        return teilbarkeitsZahlen;
    }

    public void ausgabe() {
        System.out.println("\nTestergebnis: " + zahl + " ist durch " + divisor
                + (!teilbarkeit(zahl, divisor) ? " nicht " : " ") + "glatt teilbar!");
        System.out.println("\nDie Zahl " + zahl + " ist durch folgende Zahlen teilbar:");
        String ausgabe = "";
        int[] teilbarkeitsZahlen = teilbarkeit(zahl);
        for (int i = 0; i < teilbarkeitsZahlen.length; i++) {
            if (teilbarkeitsZahlen[i] == 0) {
                continue;
            }
            ausgabe += teilbarkeitsZahlen[i] + " ";
        }
        System.out.println(ausgabe);
    }

    public static void main(String[] args) {
        ParameterübergabeMitRückgabewert parameterübergabeMitRückgabewert = new ParameterübergabeMitRückgabewert();
        parameterübergabeMitRückgabewert.titel();
        parameterübergabeMitRückgabewert.eingabe();
        parameterübergabeMitRückgabewert.ausgabe();
    }
}
