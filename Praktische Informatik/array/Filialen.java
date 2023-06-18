import java.util.Scanner;

/*
    Berechnung von Quartalsumsätzen
*/

public class Filialen {

  static Scanner scanner = new Scanner(System.in);

  double[][] filialen;
  double[] summeEinesQuartal = new double[5];

  public void titel() {
    System.out.println("*********************************************");
    System.out.println("                 Quartalsumsätze             ");
    System.out.println("*********************************************\n");
  }

  public void eingabe() {
    System.out.print("Bitte geben Sie die Anzahl der Filialen an: ");
    int anzahl = scanner.nextInt();
    filialen = new double[anzahl][5];

    for (int i = 0; i < filialen.length; i++) {
      for (int j = 0; j < filialen[i].length - 1; j++) {
        System.out.print(
          "Umsatz fuer Filiale " + (i + 1) + " (Quartal " + (j + 1) + "): "
        );
        filialen[i][j] = scanner.nextDouble();
      }
    }
  }

  public void verarbeitung() {
    for (int i = 0; i < filialen.length; i++) {
      for (int j = 0; j < filialen[i].length - 1; j++) {
        filialen[i][4] += filialen[i][j];
      }
    }

    for (int i = 0; i < filialen.length; i++) {
      for (int j = 0; j < filialen[i].length; j++) {
        summeEinesQuartal[j] += filialen[i][j];
      }
    }
  }

  public void ausgabe() {
        System.out.println("\n\t\t\t\t\tQuartalsumsätze\n");
        System.out.println("\t\t1. Quartal\t2. Quartal\t3. Quartal\t4. Quartal\tSumme");
        System.out.println("------------------------------------------------------------------------------------------");
        for (int i = 0; i < filialen.length; i++) {
            System.out.println("Filiale " + (i + 1) + "\t" + filialen[i][0] + " TEUR\t" + filialen[i][1] + " TEUR\t"
                    + filialen[i][2] + " TEUR\t"
                    + filialen[i][3] + " TEUR\t" + filialen[i][4] + " TEUR");
        }
        System.out.println("------------------------------------------------------------------------------------------");
        System.out.println("Summe" + "\t\t" + summeEinesQuartal[0] + " TEUR\t" + summeEinesQuartal[1] + " TEUR\t"
                + summeEinesQuartal[2]
                + " TEUR\t" + summeEinesQuartal[3] + " TEUR\t" + summeEinesQuartal[4] + " TEUR\t");
        System.out.println("==========================================================================================");
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
