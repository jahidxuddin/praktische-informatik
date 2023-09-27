package dev.ju.bank;

/**
 *
 * Diese Klasse dient zur Darstellung eines Kunden
 * @version 1.0 from 22.09.2023
 * @author Jahid Uddin
 */

public class Kunde {
    private int kundenNr;
    private String kundenName;
    private Konto konto;

    public Kunde(int kundenNr, String kundenName) {
        this.kundenNr = kundenNr;
        this.kundenName = kundenName;
    }

    public int getKundenNr() {
        return kundenNr;
    }

    public void setKundenNr(int kundenNr) {
        this.kundenNr = kundenNr;
    }

    public String getKundenName() {
        return kundenName;
    }

    public void setKundenName(String kundenName) {
        this.kundenName = kundenName;
    }

    public void neuesKontoAnlegen(Konto konto) {
        this.konto = konto;
    }

    public String kontoUebersicht() {
        return this.kundenName + "(Kundennummer: " + this.kundenNr + ") hat das " + this.konto.getKontoTyp() + " " + this.konto.getKontoNr() + ".";
    }
}
