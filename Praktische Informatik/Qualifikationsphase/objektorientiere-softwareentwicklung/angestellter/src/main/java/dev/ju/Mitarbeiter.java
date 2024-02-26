package dev.ju;

/**
  *
  * L�sung Aufgabe 1: Mitarbeiter - Angestellter
  * @author Michael Schlosser
  */

public class Mitarbeiter {
    protected String firma;
    protected String nachname;
    protected double einkommen;

    public Mitarbeiter() {
        this.firma = "Friedrich-List OHG";
        this.einkommen = 0;
    }

    public void kennung() {
        System.out.println("\tIch bin ein Mitarbeiter der " + firma + "." );
    }

    public void abrechnung(){
        System.out.println("\tDas Einkommen betraegt : " + einkommen + " Euro." );
    }
}
