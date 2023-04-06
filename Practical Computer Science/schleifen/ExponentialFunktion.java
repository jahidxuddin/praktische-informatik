package schleifen;

import java.util.Scanner;

/*
    Potenzierung von Zahlen
*/

public class ExponentialFunktion {
  static Scanner scanner = new Scanner(System.in);

  double basis, exponent, ergebnis;

  public void titel() {
    System.out.println("******************************************************************");
    System.out.println("                        Exponential-Funktion                      ");
    System.out.println("******************************************************************");
  }

  public void eingabe() { // Abfrage der ben�tigten Daten
    System.out.print("Geben Sie eine Zahl ein, die Sie potenzieren m�chten: ");
    basis = scanner.nextDouble();
    System.out.print("Bitte geben Sie den Exponenten ein: ");
    exponent = scanner.nextDouble();
  }

  public void verarbeitung() { // Verarbeitung der Daten
    ergebnis = basis;
    for (int i = 1; i < exponent; i++) {
      ergebnis *= basis;
    } // end of for
  }

  public void ausgabe() { // Ausgabe der Ergebnisse
    System.out.println("\n" + basis + " hoch " + exponent + " = " + ergebnis);
  }

  public static void main(String[] args) {
    ExponentialFunktion exponentialFunktion = new ExponentialFunktion();
    exponentialFunktion.titel();
    char loop = 'J';
    while (loop == 'J' || loop == 'j') {
      exponentialFunktion.eingabe();
      exponentialFunktion.verarbeitung();
      exponentialFunktion.ausgabe();
      System.out.print("\nM�chten Sie eine neue Berechnung durchf�hren? (J/N) ");
      loop = scanner.next().charAt(0);
    }
    scanner.close();
  }
}