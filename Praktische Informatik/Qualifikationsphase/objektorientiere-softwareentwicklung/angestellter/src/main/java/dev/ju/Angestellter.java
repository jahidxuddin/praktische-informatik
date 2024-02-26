package dev.ju;

/**
  * L�sung Aufgabe 1: Mitarbeiter - Angestellter
  * @author Michael Schlosser
  */

public class Angestellter extends Mitarbeiter {
    // double einkommen;
    // Erweiterung der Klasse Mitarbeiter
    public void arbeitsplatz() {
        System.out.println("\tIch arbeite im Buero!" );
    }

    public void setEinkommen(double einkommen){
        super.einkommen = einkommen;
    }

    public void kennung() {
        System.out.println("\tIch bin ein Angestellter der " + super.firma + ".");
    }

    public void kennung(String firma) {
        super.firma = firma;
        System.out.println("\tIch bin ein Angestellter der " + super.firma + ".");
    }
}
