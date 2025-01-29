package dev.ju;

import java.util.ArrayList;

public class Klasse {
    private String name;
    private Lehrer meinLehrer;
    private ArrayList<Schueler> meineSchueler;

    public Klasse(Lehrer lehrer) {
        this.meinLehrer = lehrer;
        this.meineSchueler = new ArrayList<>();
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Lehrer getMeinTutor() {
        return meinLehrer;
    }

    public void setMeinTutor(Lehrer meinLehrer) {
        this.meinLehrer = meinLehrer;
    }

    public void setMeineSchueler(ArrayList<Schueler> meineSchueler) {
        this.meineSchueler = meineSchueler;
    }

    public ArrayList<Schueler> getMeineSchueler() {
        return this.meineSchueler;
    }

    public void neuerSchuelerHinzufuegen(String name) {
        this.meineSchueler.add(new Schueler(name));
    }

    public String ausgabeKlassenListe() {
        String klassenliste = "";
        for (Schueler schueler : this.meineSchueler) {
            klassenliste += (this.meineSchueler.indexOf(schueler) + 1) + ". " + schueler.getName() + "\n";
        }

        return klassenliste;
    }

    public String ausgabeSchuelerNamen() {
        String schuelerNamen = "";
        for (Schueler schueler : this.meineSchueler) {
            schuelerNamen += (this.meineSchueler.indexOf(schueler) + 1) + ". " + schueler + "\n";
        }

        return schuelerNamen;
    }

    public String ausgabeSchuelerMitKursen() {
        String schuelerMitKursen = "";

        for (Schueler schueler : this.meineSchueler) {
            schuelerMitKursen += (this.meineSchueler.indexOf(schueler) + 1) + ". " + schueler + "\n";
            schuelerMitKursen += schueler.ausgabeKurse();
            schuelerMitKursen += "\n";
        }

        return schuelerMitKursen;
    }

    public void schuelerNameAendern(String alterName, String neuerName) {
        for (Schueler schueler : this.meineSchueler) {
            if (schueler.getName().equalsIgnoreCase(alterName)) {
                schueler.setName(neuerName);
                break;
            }
        }
    }
}
