package dev.ju.bank;

/**
 *
 * Diese Klasse dient zur Darstellung eines Kunden
 * @version 2.0 from 29.09.2023
 * @author Jahid Uddin
 */

public class Kunde {
    private int kundenNr;
    private String kundenName;
    private final Konto[] konten;

    public Kunde(int kundenNr, String kundenName) {
        this.kundenNr = kundenNr;
        this.kundenName = kundenName;
        this.konten = new Konto[3];
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

    public boolean neuesKontoAnlegen(Konto konto) {
        for (int i = 0; i < konten.length; i++) {
            if (konten[i] == null) {
                konten[i] = konto;
                return false;
            }
        }

        return true;
    }

    public String kontenUebersicht() {
        if (this.konten[0] == null) {
            return "---";
        }

        StringBuilder kontenUebersicht = new StringBuilder();

        for (int i = 0; i < this.konten.length; i++) {
            Konto konto = this.konten[i];

            if (konto == null) {
                break;
            }

            if (i > 0) {
                kontenUebersicht.append("\n");
            }

            kontenUebersicht
                    .append((i + 1))
                    .append(". Konto: ")
                    .append(konto.getKontoTyp())
                    .append(" ")
                    .append(konto.getKontoNr());
        }

        return kontenUebersicht.toString();
    }
}
