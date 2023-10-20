package dev.ju.bank;

/**
 *
 * Diese Klasse dient zur Darstellung eines Kunden
 * @version 3.0 from 11.10.2023
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
                return true;
            }
        }

        return false;
    }

    public double abfrageKontoStand(String kontoNr) {
        for (Konto konto : konten) {
            if (konto != null && konto.getKontoNr().equals(kontoNr)) {
                return konto.getKontoStand();
            }
        }

        return Double.NaN;
    }

    public boolean auszahlen(String kontoNr, double betrag) {
        for (Konto konto : konten) {
            if (konto != null && konto.getKontoNr().equals(kontoNr)) {
                return konto.auszahlen(betrag);
            }
        }

        return false;
    }

    public boolean einzahlen(String kontoNr, double betrag) {
        for (Konto konto : konten) {
            if (konto != null && konto.getKontoNr().equals(kontoNr)) {
                konto.einzahlen(betrag);
                return true;
            }
        }

        return false;
    }

    public boolean loescheKonto(String kontoNr) {
        for (int i = 0; i < konten.length; i++) {
            if (konten[i] != null && konten[i].getKontoNr().equals(kontoNr)) {
                konten[i] = null;
                return true;
            }
        }

        return false;
    }

    public String kontenUebersicht() {
        StringBuilder kontenUebersicht = new StringBuilder();

        for (int i = 0; i < konten.length; i++) {
            Konto konto = konten[i];

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
