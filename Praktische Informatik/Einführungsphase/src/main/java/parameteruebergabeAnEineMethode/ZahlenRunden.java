package main.java.parameteruebergabeAnEineMethode;

import java.util.Scanner;

/*
    Zahlen runden
*/

public class ZahlenRunden {
    Scanner scanner = new Scanner(System.in);

    double zahl;
    int dezimalstellen;

    public void titel() {
        System.out.println("*********************************************");
        System.out.println("            Zahlen runden                    ");
        System.out.println("*********************************************\n");
    }

    public void eingabe() {
        System.out.print("Eingabe Zahl: ");
        zahl = scanner.nextDouble();
        System.out.print("Eingabe Dezimalstellen: ");
        dezimalstellen = scanner.nextInt();
    }

    public double runden(double zahl, int stellen) {
        return ((int) ((zahl * 100) + 0.5)) / 100;
    }

    public void ausgabe() {
        System.out.println("Ausgabe gerundete Zahl: " + runden(zahl, dezimalstellen));
    }

    public static void main(String[] args) {
        ZahlenRunden zahlenRunden = new ZahlenRunden();
        zahlenRunden.titel();
        zahlenRunden.eingabe();
        zahlenRunden.ausgabe();
    }
}
