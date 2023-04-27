package schleifen;

import java.util.Scanner;

/*
    Berechnung der Fakultät
*/

public class Fakultät {
    static Scanner scanner = new Scanner(System.in);

    static double zahl;
    static final int MAXIMALE_FAKULTÄT = 63;
    static final int MINIMALE_FAKULTÄT = 0;

    String ausgabe;

    public void titel() {
        System.out.println("*********************************************");
        System.out.println("             Fakultät-Berechnung             ");
        System.out.println("*********************************************");
    }

    public void eingabe() { // Abfrage der benötigten Daten
        System.out.print("Bitte geben Sie eine Zahl zwischen 0 und 63 ein: ");
        zahl = scanner.nextDouble();
    }

    public void verarbeitung() { // Verarbeitung der Daten
        ausgabe = "\nDie Fakultät " + zahl + "! beträgt: ";
        long ergebnis = 1L;
        for (int i = 1; i <= zahl; i++) {
            if (i == zahl) {
                ausgabe += i + " = ";
            } else {
                ausgabe += i + " * ";
            }
            ergebnis *= i;
        }
        ausgabe += ergebnis;
    }

    public void ausgabe() { // Ausgabe der Ergebnisse
        System.out.println(ausgabe);
    }

    public static void main(String[] args) {
        Fakultät fakultät = new Fakultät();
        fakultät.titel();
        char loop = 'J';
        while (loop == 'J' || loop == 'j') {
            fakultät.eingabe();
            if (zahl > MAXIMALE_FAKULTÄT || zahl < MINIMALE_FAKULTÄT) {
                System.out.println(
                        "Geben Sie eine Zahl zwischen 0 und 63 ein, aus der die Fakultät berechnet werden soll!");
                continue;
            }
            fakultät.verarbeitung();
            fakultät.ausgabe();
            System.out.print("\nMöchten Sie eine neue Berechnung durchführen? (J/N) ");
            loop = scanner.next().charAt(0);
        }
        scanner.close();
    }
}