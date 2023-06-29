package main.java.fallunterscheidungen;

import java.util.Scanner;

/*
   Berechnung der kleinsten von drei Zahlen
*/

public class KleinsteVonDreiZahlen {
   double zahl1;
   double zahl2;
   double zahl3;

   double kleinsteZahl;

   public void titel() {
      System.out.println("****************************************");
      System.out.println("\tKleinste von drei Zahlen");
      System.out.println("****************************************\n");
   }

   public void eingabe() { // Abfrage der benötigten Daten
      Scanner scanner = new Scanner(System.in);

      System.out.print("Bitte geben Sie die Zahl 1 ein: ");
      zahl1 = scanner.nextDouble();

      System.out.print("Bitte geben Sie die Zahl 2 ein: ");
      zahl2 = scanner.nextDouble();

      System.out.print("Bitte geben Sie die Zahl 3 ein: ");
      zahl3 = scanner.nextDouble();

      scanner.close();
   }

   public void berechnen() { // Berechnung der kleinsten von drei Zahlen
      if (zahl1 < zahl2) {
         if (zahl1 < zahl3) {
            kleinsteZahl = zahl1;
         }
      }

      if (zahl2 < zahl1) {
         if (zahl2 < zahl3) {
            kleinsteZahl = zahl2;
         }
      }

      if (zahl3 < zahl2) {
         if (zahl3 < zahl1) {
            kleinsteZahl = zahl3;
         }
      }
   }

   public void ausgabe() { // Ausgabe der Ergebnisse
      System.out.println("\nAuswertung");
      System.out.println("Die kleinste Zahl von den Zahlen: ");
      System.out.println("Zahl 1: " + zahl1);
      System.out.println("Zahl 2: " + zahl2);
      System.out.println("Zahl 3: " + zahl3);
      System.out.println("ist die Zahl: " + kleinsteZahl + ".");
   }

   public static void main(String[] args) {
      KleinsteVonDreiZahlen kleinsteVonDreiZahlen = new KleinsteVonDreiZahlen();
      kleinsteVonDreiZahlen.titel();
      kleinsteVonDreiZahlen.eingabe();
      kleinsteVonDreiZahlen.berechnen();
      kleinsteVonDreiZahlen.ausgabe();
   }
}
