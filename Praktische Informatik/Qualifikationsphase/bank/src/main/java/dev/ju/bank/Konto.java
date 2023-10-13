package dev.ju.bank;

/**
 *
 * Diese Klasse dient zur Darstellung eines Kontos
 * @version 2.0 from 11.10.2023
 * @author Jahid Uddin
 */

public class Konto {
    private String kontoNr;
    private String kontoTyp;
    private double kontoStand;

    public Konto(String kontoNr) {
        this.kontoNr = kontoNr;
        this.kontoStand = 0;
    }

    public Konto(String kontoNr, String kontoTyp) {
        this.kontoNr = kontoNr;
        this.kontoTyp = kontoTyp;
        this.kontoStand = 0;
    }

    public String getKontoNr() {
        return kontoNr;
    }

    public void setKontoNr(String kontoNr) {
        this.kontoNr = kontoNr;
    }

    public String getKontoTyp() {
        return kontoTyp;
    }

    public void setKontoTyp(String kontoTyp) {
        this.kontoTyp = kontoTyp;
    }

    public double getKontoStand() {
        return kontoStand;
    }

    public void setKontoStand(double kontoStand) {
        this.kontoStand = kontoStand;
    }

    public boolean auszahlen(double betrag) {
        if (kontoStand - betrag < 0) {
            return false;
        }

        kontoStand -= betrag;

        return true;
    }

    public boolean einzahlen(double betrag) {
        if (betrag < 0) {
            return false;
        }

        this.kontoStand += betrag;

        return true;
    }
}
