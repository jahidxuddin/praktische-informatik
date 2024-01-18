package dev.ju;

import java.util.ArrayList;

public class Schueler extends Person {
    private static int schuelerstammNummer = 1000;
    private int schuelerNummer;
    private String name;
    private ArrayList<Kurs> meineKurse;

    public Schueler(String name) {
        super(name);
        this.name = name;
        this.meineKurse = new ArrayList<>();
        this.schuelerNummer = schuelerstammNummer;
        schuelerstammNummer += 1;
    }

    public int getSchuelerNummer() {
        return schuelerNummer;
    }

    public void setSchuelerNummer(int schuelerNummer) {
        this.schuelerNummer = schuelerNummer;
    }

    public ArrayList<Kurs> getMeineKurse() {
        return meineKurse;
    }

    public void setMeineKurse(ArrayList<Kurs> meineKurse) {
        this.meineKurse = meineKurse;
    }

    public void neuenKursHinzufuegen(String bezeichnung, int stunden) {
        this.meineKurse.add(new Kurs(bezeichnung, stunden));
    }

    public String ausgabeKurse() {
        String kurse = "";

        for (Kurs kurs : this.meineKurse) {
            kurse += kurs + "\n";
        }

        return kurse;
    }

    public void kursNameAendern(String alterName, String neuerName) {
        for (Kurs kurs : this.meineKurse) {
            if (kurs.getBezeichnung().equalsIgnoreCase(alterName)) {
                kurs.setBezeichnung(neuerName);
                break;
            }
        }
    }

    @Override
    public String toString() {
        return this.name + " (Schülernummer: " + this.schuelerNummer + ")";
    }
}
