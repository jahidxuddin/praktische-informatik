package array;

import java.util.Scanner;

/*
    Berechnung von Quartalsumsätzen
*/

public class Filialen {
    static Scanner scanner = new Scanner(System.in);

    double[][] umsatz;
    double[] summeProFiliale;
    double[] summenAllerQuartale = new double[5];

    public void titel() {
        System.out.println("*********************************************");
        System.out.println("                 Quartalsumsätze             ");
        System.out.println("*********************************************\n");
    }

    public void eingabe() {
        System.out.print("Bitte geben Sie die Anzahl der Filialen ein: ");
        int anzahlFilialen = scanner.nextInt();
        umsatz = new double[anzahlFilialen][4];
        summeProFiliale = new double[anzahlFilialen];
        for (int i = 0; i < umsatz.length; i++) {
            for (int j = 0; j < umsatz[i].length; j++) {
                System.out.print("Umsatz für Filiale " + (i + 1) + " (Quartal " + (j + 1) + "): ");
                umsatz[i][j] = scanner.nextDouble();
            }
        }
    }

    public void verarbeitung() {
        for (int i = 0; i < umsatz.length; i++) {
            for (int j = 0; j < umsatz[i].length; j++) {
                summeProFiliale[i] += umsatz[i][j];
            }
        }
        for (int i = 0; i < summenAllerQuartale.length - 1; i++) {
            for (int j = 0; j < umsatz.length; j++) {
                summenAllerQuartale[i] += umsatz[j][i];
            }
        }
        for (int i = 0; i < summeProFiliale.length; i++) {
            summenAllerQuartale[4] += summeProFiliale[i];
        }
    }

    public void ausgabe() {
        System.out.println("\n\t\t\t\t\tQuartalsumsätze\n");
        System.out.println("\t\t1. Quartal\t2. Quartal\t3. Quartal\t4. Quartal\tSumme");
        System.out
                .println("------------------------------------------------------------------------------------------");
        for (int i = 0; i < umsatz.length; i++) {
            System.out.println("Filiale " + (i + 1) + "\t" + umsatz[i][0] + " TEUR\t" + umsatz[i][1] + " TEUR\t"
                    + umsatz[i][2] + " TEUR\t"
                    + umsatz[i][3] + " TEUR\t" + summeProFiliale[i] + " TEUR");
        }
        System.out
                .println("------------------------------------------------------------------------------------------");
        System.out.println("Summe" + "\t\t" + summenAllerQuartale[0] + " TEUR\t" + summenAllerQuartale[1] + " TEUR\t"
                + summenAllerQuartale[2]
                + " TEUR\t" + summenAllerQuartale[3] + " TEUR\t" + summenAllerQuartale[4] + " TEUR\t");
        System.out.println(
                "==========================================================================================");
    }

    public static void main(String[] args) {
        Filialen filialen = new Filialen();
        filialen.titel();
        char loop = 'J';
        while (loop == 'J' || loop == 'j') {
            filialen.eingabe();
            filialen.verarbeitung();
            filialen.ausgabe();
            System.out.print("\nMöchten Sie die Eingaben wiederholen? (J/N) ");
            loop = scanner.next().charAt(0);
        }
        scanner.close();
    }
}
