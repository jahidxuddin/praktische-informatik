package schleifen;

import java.text.DecimalFormat;
import java.util.Scanner;

/*
    Berechnung des Abzahlungsdarlehen 
*/

public class Abzahlungsdarlehen {
    static Scanner scanner = new Scanner(System.in);
    DecimalFormat df1 = new DecimalFormat(",#");
    DecimalFormat df2 = new DecimalFormat(",##");

    double kreditbetrag, zinssatz;
    static int laufzeitInJahren;

    static final int MAXIMALE_LAUFZEIT = 10;
    static final int MINIMALE_LAUFZEIT = 1;

    String ausgabe;
    double summeZinsen, summeTilgung, summeAnuität, anteilKreditbetrag;

    public void titel() {
        System.out.println("******************************************************************");
        System.out.println("                        Abzahlungsdarlehen                        ");
        System.out.println("******************************************************************");
    }

    public void eingabe() { // Abfrage der benötigten Daten
        System.out.print("Bitte geben Sie den Kreditbetrag ein: ");
        kreditbetrag = scanner.nextDouble();
        System.out.print("Bitte geben Sie den Zinssatz ein: ");
        zinssatz = scanner.nextDouble();
        System.out.print("Bitte geben Sie die Kreditlaufzeit (in Jahren) ein: ");
        laufzeitInJahren = scanner.nextInt();
    }

    public void verarbeitung() { // Verarbeitung der Daten
        ausgabe = "Jahr\tAnfangskapital\tZinsen\tTilgung\tAnnuität\tRestkapital";
        double tilgung = kreditbetrag / laufzeitInJahren;
        double restKapital = kreditbetrag;
        double zinsen = 0.0;
        double annuität = 0.0;
        for (int i = 1; i <= laufzeitInJahren; i++) {
            zinsen = restKapital * zinssatz / 100;
            summeZinsen += zinsen;
            annuität = zinsen + tilgung;
            summeAnuität += annuität;
            summeTilgung += tilgung;
            ausgabe += "\n" + i + "\t" + restKapital + "\t\t" + zinsen + "\t" + tilgung + "\t" + annuität + "\t\t"
                    + (restKapital -= tilgung);
        }
        anteilKreditbetrag = summeZinsen / (kreditbetrag / 100);
    }

    public void ausgabe() { // Ausgabe der Ergebnisse
        System.out.println("\nKreditbetrag: " + kreditbetrag);
        System.out.println("Zinssatz: " + zinssatz + " %");
        System.out.println("Laufzeit: " + laufzeitInJahren + " Jahre\n");
        System.out.println(ausgabe);
        System.out.println(
                "\nSumme Zinsen: " + summeZinsen + " (= " + anteilKreditbetrag + " % vom ursprünglichen Kreditbetrag)");
        System.out.println("Summe Tilgung: " + summeTilgung);
        System.out.println("Summe Annuität: " + summeAnuität);
    }

    public static void main(String[] args) {
        Abzahlungsdarlehen abzahlungsdarlehen = new Abzahlungsdarlehen();
        abzahlungsdarlehen.titel();
        char loop = 'J';
        while (loop == 'J' || loop == 'j') {
            abzahlungsdarlehen.eingabe();
            if (laufzeitInJahren > MAXIMALE_LAUFZEIT || laufzeitInJahren < MINIMALE_LAUFZEIT) {
                System.out.println("\nDie Laufzeit beträgt minimal 1 und maximal 10 Jahre!\n");
                continue;
            }
            abzahlungsdarlehen.verarbeitung();
            abzahlungsdarlehen.ausgabe();
            System.out.print("\nMöchten Sie eine neue Berechnung durchführen? (J/N) ");
            loop = scanner.next().charAt(0);
        }
        scanner.close();
    }
}