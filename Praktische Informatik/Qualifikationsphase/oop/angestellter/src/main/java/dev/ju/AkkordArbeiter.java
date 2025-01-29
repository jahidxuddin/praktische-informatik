package dev.ju;

public class AkkordArbeiter extends Arbeiter {
    public void kennung(String arbeitsplatz) {
        super.firma = arbeitsplatz;
        System.out.println("\tIch bin ein Akkordarbeiter der " + firma + "." );
    }

    public void setEinkommen(int produzierteMenge, double minutenFaktor, double vorgabezeit) {
        super.einkommen = produzierteMenge * minutenFaktor * vorgabezeit;
    }
}
