package dev.ju;

/**
  *
  * L�sung Aufgabe 1: Mitarbeiter - Angestellter
  * @author Michael Schlosser
  */

public class Anwendung {
    public static void main(String[] args) {
        Mitarbeiter mitarbeiter1 = new Mitarbeiter();

        Angestellter angestellter1 = new Angestellter();
        Angestellter angestellter2 = new Angestellter();

        Arbeiter arbeiter1 = new Arbeiter();
        Arbeiter arbeiter2 = new Arbeiter();
        Arbeiter arbeiter3 = new Arbeiter();

        AkkordArbeiter akkordArbeiter1 = new AkkordArbeiter();

        System.out.println("\n\n\tMitarbeiter 1:");
        mitarbeiter1.kennung();
        System.out.println("\t-----------------------------------------------" );

        System.out.println("\tAngestellter 1:");
        angestellter1.kennung();
        angestellter1.arbeitsplatz();
        System.out.println("\t-----------------------------------------------" );

        System.out.println("\tAngestellter 2:");
        angestellter2.kennung("Hans-Dampf KG");
        angestellter2.setEinkommen(3000);
        angestellter2.abrechnung();
        System.out.println("\t-----------------------------------------------");

        System.out.println("\tArbeiter 1:");
        arbeiter1.kennung("Hans-Dampf KG");
        arbeiter1.setEinkommen();
        arbeiter1.abrechnung();
        System.out.println("\t-----------------------------------------------");

        System.out.println("\tArbeiter 2:");
        arbeiter2.kennung("Hans-Dampf KG");
        arbeiter2.setEinkommen(80);
        arbeiter2.abrechnung();
        System.out.println("\t-----------------------------------------------");

        System.out.println("\tArbeiter 3:");
        arbeiter3.kennung("Hans-Dampf KG");
        arbeiter3.setEinkommen(120, 15);
        arbeiter3.abrechnung();
        System.out.println("\t-----------------------------------------------");

        System.out.println("\tAkkordarbeiter 1:");
        akkordArbeiter1.kennung("Hans-Dampf KG");
        akkordArbeiter1.setEinkommen(500, 0.16, 10);
        akkordArbeiter1.abrechnung();
        System.out.println("\t-----------------------------------------------");
    }
}
