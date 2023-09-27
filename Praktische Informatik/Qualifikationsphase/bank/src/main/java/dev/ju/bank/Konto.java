package dev.ju.bank;

/**
 *
 * Diese Klasse dient zur Darstellung eines Kontos
 * @version 1.0 from 22.09.2023
 * @author Jahid Uddin
 */

public class Konto {
    private String kontoNr;
    private String kontoTyp;

    public Konto(String kontoNr, String kontoTyp) {
        this.kontoNr = kontoNr;
        this.kontoTyp = kontoTyp;
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
}
