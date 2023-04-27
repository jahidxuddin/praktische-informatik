package aufbauEinerApplikation;

import java.util.Scanner;
import java.text.DecimalFormat;

/*
    Berechnung der Zinsen
*/

public class Zinsen {
    double kapital;
    double zinssatz;
    int laufzeitInJahre;

    String zusatz;
    double zinsen;
    double endKapital;

    DecimalFormat oneDecimal = new DecimalFormat("#.#");
    DecimalFormat twoDecimal = new DecimalFormat("#.##");

    public void titel() {
        System.out.println("********************************************");
        System.out.println("\t     Berechnung Zinsen");
        System.out.println("********************************************\n");
    }

    public void eingabe() { // Abfrage benötigten Daten
        Scanner scanner = new Scanner(System.in);

        System.out.print("Bitte geben Sie das Startkapital ein: ");
        kapital = scanner.nextDouble();

        System.out.print("Bitte geben Sie den Zinssatz ein: ");
        zinssatz = scanner.nextDouble();

        System.out.print("Bitte geben Sie die Laufzeit in Jahre ein: ");
        laufzeitInJahre = scanner.nextInt();

        scanner.close();
    }

    public void berechnen() { // Berechnung der Zinsen
        if (laufzeitInJahre == 1) {
            zusatz = "Jahr";
        } else {
            zusatz = "Jahren";
        }
        zinsen = (kapital * zinssatz * laufzeitInJahre) / 100;
        endKapital = kapital * (1 + laufzeitInJahre * (zinssatz / 100));
    }

    public void ausgabe() { // Ausgabe der Ergebnisse
        System.out.println("\nBei einem Startkapital von " + kapital + " EUR und Zins-");
        System.out.println("satz von " + oneDecimal.format(zinssatz) + "% liegt das Kaptial nach ");
        System.out.println(laufzeitInJahre + " " + zusatz + " und Zinsen von ");
        System.out.println(twoDecimal.format(zinsen) + " EUR bei " + twoDecimal.format(endKapital) + " EUR.");
    }

    public static void main(String[] args) {
        Zinsen zinsen = new Zinsen();
        zinsen.titel();
        zinsen.eingabe();
        zinsen.berechnen();
        zinsen.ausgabe();
    }
}
