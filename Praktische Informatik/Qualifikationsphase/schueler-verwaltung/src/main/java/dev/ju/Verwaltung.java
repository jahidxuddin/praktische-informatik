package dev.ju;

public class Verwaltung {
    private Klasse meineKlasse;

    public Verwaltung() {
        Lehrer meinLehrer = new Lehrer("Schlosser", "SCLO");
        this.meineKlasse = new Klasse(meinLehrer);
    }

    public void eingabeKlassenliste() {
        this.meineKlasse.setName("BG12-DV");

        this.meineKlasse.neuerSchuelerHinzufuegen("Max Muster");
        this.meineKlasse.neuerSchuelerHinzufuegen("Friedrich-List");
        this.meineKlasse.neuerSchuelerHinzufuegen("Tim Tester");
        this.meineKlasse.neuerSchuelerHinzufuegen("Birgit Beispiel");
    }

    public void ausgabeKlassenliste() {
        System.out.println("********************************");
        System.out.println("\t\tKlassenliste");
        System.out.println("********************************");
        System.out.println("Klassenbezeichnung: " + this.meineKlasse.getName());
        System.out.println("Tutor: " + this.meineKlasse.getMeinTutor().getName() + " (" + this.meineKlasse.getMeinTutor().getNamensKuerzel() + ")");
        System.out.println("\nAlle Schüler der Klasse BG12-DV: ");
        System.out.println(this.meineKlasse.ausgabeKlassenListe());
    }

    public void eingabeSchuelerUndIhreKurse() {
        meineKlasse.setName("BG12-DV");
        meineKlasse.getMeinTutor().setNamensKuerzel("sclo");
        meineKlasse.getMeinTutor().setName("Schlosser");

        meineKlasse.neuerSchuelerHinzufuegen("Max Muster");
        meineKlasse.neuerSchuelerHinzufuegen("Friedrich List");
        meineKlasse.neuerSchuelerHinzufuegen("Tim Tester");
        meineKlasse.neuerSchuelerHinzufuegen("Birgit Beispiel");

        for (Schueler schueler : meineKlasse.getMeineSchueler()) {
            schueler.neuenKursHinzufuegen("LK PI: Objektorientierte Softwareentwickelung", 6);
        }

        meineKlasse.getMeineSchueler().get(0).neuenKursHinzufuegen("GK IT: Betriebssysteme", 3);
        meineKlasse.getMeineSchueler().get(0).neuenKursHinzufuegen("GK Englisch: The Challenge of Individualism", 3);
        meineKlasse.getMeineSchueler().get(1).neuenKursHinzufuegen("GK Sport: Fussball", 2);
        meineKlasse.getMeineSchueler().get(1).neuenKursHinzufuegen("GK Englisch: The Challenge of Individualism", 3);

        meineKlasse.getMeineSchueler().get(2).neuenKursHinzufuegen("GK IT: Betriebssysteme", 3);
    }

    public void aendereSchuelerUndKursName() {
        this.meineKlasse.schuelerNameAendern("Max Muster", "Hans Zimmermann");

        for (Schueler schueler : this.meineKlasse.getMeineSchueler()) {
            schueler.kursNameAendern("GK IT: Betriebssysteme", "GK IT: Netzwerktechnik");
        }
    }

    public void ausgabeSchuelerUndIhreKurse() {
        System.out.println("******************************************************************");
        System.out.println("\t\t\t\t\tAlle Schüler und ihre Kurse");
        System.out.println("******************************************************************");
        System.out.println(this.meineKlasse.ausgabeSchuelerMitKursen());
    }

    public static void main(String[] args) {
        Verwaltung vw = new Verwaltung();
        vw.eingabeKlassenliste();
        vw.ausgabeKlassenliste();
        System.out.println("\n\n");
        vw.eingabeSchuelerUndIhreKurse();
        vw.aendereSchuelerUndKursName();
        vw.ausgabeSchuelerUndIhreKurse();
    }
}
