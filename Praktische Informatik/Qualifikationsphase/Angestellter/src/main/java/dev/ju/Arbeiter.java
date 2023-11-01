package dev.ju;

public class Arbeiter extends Mitarbeiter {
    protected final double STANDARDLOHN = 18;
    protected final double DURCHSCHNITLICHE_ARBEITSZEIT_IN_STUNDEN = 140;

    public void kennung(String arbeitsplatz) {
        super.firma = arbeitsplatz;
        System.out.println("\tIch bin ein Arbeiter der " + firma + "." );
    }

    public void setEinkommen() {
        super.einkommen = STANDARDLOHN * DURCHSCHNITLICHE_ARBEITSZEIT_IN_STUNDEN;
    }

    public void setEinkommen(double geleisteteStunden) {
        super.einkommen = STANDARDLOHN * geleisteteStunden;
    }

    public void setEinkommen(double geleisteteStunden, double stundenlohn) {
        super.einkommen = geleisteteStunden * stundenlohn;
    }
}
