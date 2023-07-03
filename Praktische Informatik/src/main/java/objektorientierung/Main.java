package main.java.objektorientierung;

public class Main {
    public static void main(String[] args) {
        // Erstelle Kurse
        Kurs praktischeInformatik = new Kurs("Praktische Informatik");
        Kurs mathematik = new Kurs("Mathematik");

        // Füge Kurse hinzu
        Kurs[] kurse = new Kurs[] { praktischeInformatik, mathematik };

        // Erstelle Tutor
        Tutor tutor = new Tutor("Frau Ketzer");

        // Erstelle Schüler
        Schueler schueler = new Schueler("Jahid Uddin", kurse, tutor);

        // Ausgabe Schülerdaten
        System.out.println("Schüler: " + schueler.getName());
        System.out.println("Tutor: " + schueler.getTutor().getName());
        System.out.println("Kurse:");
        for (Kurs kurs : schueler.getKurse()) {
            System.out.println("\t- " + kurs.getFach());
        }
    }
}
