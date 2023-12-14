package dev.ju;

import java.util.ArrayList;

/**
  *
  * Klasse - Schueler mit ArrayList
  */
public class Klasse {
    private String name;
    private Lehrer meinTutor;
    private ArrayList<Schueler> meineSchueler;

    public Klasse(String name, Lehrer tutor) {
        this.name = name;
        this.meinTutor = tutor;
        this.meineSchueler = new ArrayList<>();
    }

    public String getName(){
      return this.name;
    }

    public void setName(String name){
      this.name = name;
    }

    public Lehrer getMeinTutor() {
        return meinTutor;
    }

    public void setMeinTutor(Lehrer meinTutor) {
        this.meinTutor = meinTutor;
    }

    public ArrayList<Schueler> getMeineSchueler(){
        return this.meineSchueler;
    }

    public void setMeineSchueler(ArrayList<Schueler> meineSchueler){
      this.meineSchueler = meineSchueler;
    }

    public void neuerSchuelerHinzufuegen(String name){
        this.meineSchueler.add(new Schueler(name));
    }

    public void ausgabeKlassenListe(){
        System.out.println("\nAlle Schüler der Klasse " + this.name + ":");
        for (Schueler schueler : this.meineSchueler) {
            int i = this.meineSchueler.indexOf(schueler);
            System.out.println((i + 1) + ". " + schueler.getName());
        }
    }
}
