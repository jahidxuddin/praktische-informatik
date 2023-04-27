package schleifen;

import java.util.Scanner;

public class Kostenvergleich {
    static Scanner scanner = new Scanner(System.in);

    double fixum;
    double provisionssatzHandlungsreisender;
    double provisionssatzHandelsvetreter;

    double umsatz;

    public void titel() {
        System.out.println("********************************************************************");
        System.out.println("        Kostenvergleich Handelsvetreter / Handlungsreisender");
        System.out.println("********************************************************************\n");
    }

    public void eingabe() { // Abfrage der benötigten Daten
        System.out.print("Fixum des Reisenden eingeben: ");
        fixum = scanner.nextDouble();

        System.out.print("Provisionssatz des Handlungsreisenden eingeben: ");
        provisionssatzHandlungsreisender = scanner.nextDouble();

        System.out.print("Provisionssatz des Handelsvetreter eingeben: ");
        provisionssatzHandelsvetreter = scanner.nextDouble();
    }

    public void ausgabe() { // Ausgabe der Ergebnisse
        System.out.println("\n   Umsatz     Kosten Handlungsreisender   Kosten Handlungsvetreter");
        System.out.println("  (in Euro)          (in Euro)                   (in Euro)");
        System.out.println("********************************************************************");
        int umsätze = 20000;
        while (umsätze <= 500000) {
            double kostenHandlungsreisender = fixum + (umsätze / 100 * provisionssatzHandlungsreisender);
            double kostenHandelsvertreter = umsätze / 100 * provisionssatzHandelsvetreter;

            String handlungsreisenderIstGünstiger = "";
            String handelsvertreterIstGünstiger = "";
            String kostenGleich = "";

            if (kostenHandelsvertreter > kostenHandlungsreisender) {
                handlungsreisenderIstGünstiger = "****";
            } else if (kostenHandelsvertreter < kostenHandlungsreisender) {
                handelsvertreterIstGünstiger = "****";
            } else if (kostenHandelsvertreter == kostenHandlungsreisender) {
                umsatz = umsätze;
                kostenGleich = "****";
            }

            System.out.println(" " + umsätze + " EUR" + "\t  " + kostenHandlungsreisender + " EUR"
                    + handlungsreisenderIstGünstiger + kostenGleich + " \t\t     " + kostenHandelsvertreter + " EUR"
                    + handelsvertreterIstGünstiger + kostenGleich);
            umsätze = umsätze + 40000;

            handlungsreisenderIstGünstiger = "";
            handelsvertreterIstGünstiger = "";
            kostenGleich = "";
        }

        System.out.println("\nBei einem Umsatz von " + umsatz + " EUR verdienen beide Personen gleich viel!");

        System.out.print("\nMöchten Sie noch eine Berechnung durchführen (j/n)? ");
    }

    public static void main(String[] args) {
        Kostenvergleich kostenvergleich = new Kostenvergleich();
        kostenvergleich.titel();
        char loop = 'J';
        while (loop == 'J' || loop == 'j') {
            kostenvergleich.eingabe();
            kostenvergleich.ausgabe();
            loop = scanner.next().charAt(0);
        }
        scanner.close();
    }
}