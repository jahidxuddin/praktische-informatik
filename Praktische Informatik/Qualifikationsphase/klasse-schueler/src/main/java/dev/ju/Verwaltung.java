package dev.ju;

/**
  *
  * Klasse - Schueler mit ArrayList
  */
public class Verwaltung {
    private final Klasse meineKlasse;

    public Verwaltung(){
        this.meineKlasse = new Klasse("BG12-DV", new Lehrer(
                "Schlosser",
                "SCLO"
        ));
    }

    public void eingabe(){
        this.meineKlasse.neuerSchuelerHinzufuegen("Max Muster");
        this.meineKlasse.neuerSchuelerHinzufuegen("Friedrich-List");
        this.meineKlasse.neuerSchuelerHinzufuegen("Tim Tester");
        this.meineKlasse.neuerSchuelerHinzufuegen("Birgit Beispiel");
    }

    public void ausgabe(){
        System.out.println("************************************");
        System.out.println("\t\t\tKlassenliste");
        System.out.println("************************************");
        System.out.println("Klassenbezeichnung: " + this.meineKlasse.getName());
        System.out.println("Tutor: " +  this.meineKlasse.getMeinTutor().getName() + " (" + this.meineKlasse.getMeinTutor().getKuerzel() + ")");
        this.meineKlasse.ausgabeKlassenListe();
    }

    public static void main(String [] args)   {
        Verwaltung vw = new Verwaltung();
        vw.eingabe();
        vw.ausgabe();
    }
}
