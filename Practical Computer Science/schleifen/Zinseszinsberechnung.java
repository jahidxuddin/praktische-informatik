package schleifen;

import java.text.DecimalFormat;
import java.util.Scanner;

/*
    Berechnung des Zinsesinseffekts
*/

public class Zinseszinsberechnung {

    static Scanner scanner = new Scanner(System.in);
    DecimalFormat formatter = new DecimalFormat("#.##");

    double startKapital;
    double zinssatz;
    int anlageDauerInJahre;

    String jahreZusatz = " Jahre";
    double zinsen;
    double endKapital;

    public void titel() {
        System.out.println("*****************************************************");
        System.out.println("\t              Sparplan");
        System.out.println("*****************************************************\n");
    }

    public void eingabe() { // Abfrage der benötigten Daten
        System.out.print("Bitte geben Sie Ihr Kapital ein: ");
        startKapital = scanner.nextDouble();

        System.out.print("Bitte geben Sie den Zinssatz ein: ");
        zinssatz = scanner.nextDouble();

        System.out.print("Bitte geben Sie die Anlagedauer (in Jahren) ein: ");
        anlageDauerInJahre = scanner.nextInt();
    }

    public void berechnen() { // Berechnung des Zinsen
        if (anlageDauerInJahre == 1) {
            jahreZusatz = " Jahr";
        }
        endKapital = startKapital * Math.pow(1 + zinssatz / 100, anlageDauerInJahre);
        zinsen = endKapital - startKapital;
    }

    public void ausgabe() { // Ausgabe der Zinsen
        System.out.println("\nStartkapital: " + startKapital + " Euro\nZinssatz: " + zinssatz + " %\nAnlagedauer: "
                + anlageDauerInJahre + jahreZusatz + "\nZinsen: " + formatter.format(zinsen) + " Euro \nEndkapital: "
                + formatter.format(endKapital) + " Euro");
        System.out.print("\nMöchten Sie noch eine Berechnung durchführen (j/n)? ");
    }

    public static void main(String[] args) {
        Zinseszinsberechnung zinseszinsberechnung = new Zinseszinsberechnung();
        zinseszinsberechnung.titel();
        char loop = 'J';
        while (loop == 'J' || loop == 'j') {
            zinseszinsberechnung.eingabe();
            zinseszinsberechnung.berechnen();
            zinseszinsberechnung.ausgabe();
            loop = scanner.next().charAt(0);
        }
        scanner.close();
    }
}