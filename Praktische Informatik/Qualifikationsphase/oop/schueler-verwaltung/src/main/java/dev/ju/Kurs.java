package dev.ju;

public class Kurs {
    private String bezeichnung;
    private int stunden;

    public Kurs(String bezeichnung, int stunden) {
        this.bezeichnung = bezeichnung;
        this.stunden = stunden;
    }

    public String getBezeichnung() {
        return bezeichnung;
    }

    public void setBezeichnung(String bezeichnung) {
        this.bezeichnung = bezeichnung;
    }

    public int getStunden() {
        return stunden;
    }

    public void setStunden(int stunden) {
        this.stunden = stunden;
    }

    @Override
    public String toString() {
        return this.bezeichnung + " (" + this.stunden + " Stunden)";
    }
}
