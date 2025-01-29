package dev.ju.bank;

import java.util.Scanner;

public class Steuerung {
    private Kunde kunde;

    public void eingabeNeueKundenDaten() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("\nName des Kontoinhabers: ");
        String kundenName = scanner.nextLine();
        System.out.print("Eingabe Kundennr: ");
        int kundenNr = scanner.nextInt();

        this.kunde = new Kunde(kundenNr, kundenName);
    }

    public void eingabeNeuesKontoAnlegen() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("\n\nKontoeröffnung");

        System.out.print("Eingabe Kontonummer: ");
        String kontoNr = scanner.next();
        System.out.print("Eingabe Kontotyp: ");
        String kontoTyp = scanner.next();

        boolean kontoanlegungErfolgreich = this.kunde.neuesKontoAnlegen(new Konto(kontoNr, kontoTyp));

        if (kontoanlegungErfolgreich) {
            System.out.println("\n\nKonto wurde angelegt.");
        } else {
            System.out.println("\n\nMaximale Konten Anzahl erreicht. Weitere Konten können nicht angelegt werden.");
        }
    }

    public void kontenUebersicht() {
        System.out.println("\n\nKontenübersicht");
        System.out.println(kunde.getKundenName() + " hat folgende Konten:");
        System.out.println(kunde.kontenUebersicht());
    }

    public void abfrageKontostand() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("\n\nKontostand");

        System.out.print("Eingabe Kontonummer: ");
        String kontoNr = scanner.next();

        double aktuellerKontostand = kunde.abfrageKontoStand(kontoNr);

        if (Double.isNaN(aktuellerKontostand)) {
            System.out.println("\n\nDas Konto mit der Kontonummer " + kontoNr + " konnte nicht gefunden werden.");
        } else {
            System.out.println("\n\nDer Kontostand, des Kontos mit der Kontonummer " + kontoNr + ", beträgt: " + aktuellerKontostand);
        }
    }

    public void einzahlen() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("\n\nEinzahlung");

        System.out.print("Eingabe Kontonummer: ");
        String kontoNr = scanner.nextLine();

        System.out.print("Eingabe Betrag: ");
        double betrag = scanner.nextDouble();

        boolean einzahlungErfolgreich = kunde.einzahlen(kontoNr, betrag);

        if (einzahlungErfolgreich) {
            System.out.println("\n\nDie Einzahlung von " + betrag + " auf das Konto mit der Kontonummer " + kontoNr + " war erfolgreich.");
        } else {
            System.out.println("\n\nDas Konto mit der Kontonummer " + kontoNr + " konnte entweder nicht gefunden werden oder es wurde eine ungültige Betragseingabe getätigt.");
        }
    }

    public void auszahlen() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("\n\nAuszahlung");

        System.out.print("Eingabe Kontonummer: ");
        String kontoNr = scanner.next();

        System.out.print("Eingabe Betrag: ");
        double betrag = scanner.nextDouble();

        boolean auszahlungErfolgreich = kunde.auszahlen(kontoNr, betrag);

        if (auszahlungErfolgreich) {
            System.out.println("\n\nDie Auszahlung von " + betrag + " aus dem Konto mit der Kontonummer " + kontoNr + " war erfolgreich.");
        } else {
            System.out.println("\n\nDas Konto mit der Kontonummer " + kontoNr + " konnte nicht gefunden werden oder der geforderte Betrag könnte ihr Konto überziehen.");
        }
    }

    public void loescheKonto() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("\n\nKonto löschen");

        System.out.print("Eingabe Kontonummer: ");
        String kontoNr = scanner.next();

        boolean kontoLoeschungErfolgreich = kunde.loescheKonto(kontoNr);

        if (kontoLoeschungErfolgreich) {
            System.out.println("\n\nDas Konto mit der Kontonummer " + kontoNr + " wurde erfolgreich gelöscht.");
        } else {
            System.out.println("\n\nDas Konto mit der Kontonummer " + kontoNr + " konnte nicht gefunden werden.");
        }
    }

    public void auswahlmenue() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("\n\nAuswahlmenü");
        System.out.println("Neues Konto eröffnen:       ---> 1");
        System.out.println("Kontenübersicht:            ---> 2");
        System.out.println("Abfrage Kontostand:         ---> 3");
        System.out.println("Einzahlung:                 ---> 4");
        System.out.println("Auszahlung:                 ---> 5");
        System.out.println("Konto löschen:              ---> 6");
        System.out.println("Programmbeenden:            ---> 0");
        System.out.print("Auswahleingabe:                  ");

        switch (scanner.nextInt()) {
            case 1:
                eingabeNeuesKontoAnlegen();
                break;
            case 2:
                kontenUebersicht();
                break;
            case 3:
                abfrageKontostand();
                break;
            case 4:
                einzahlen();
                break;
            case 5:
                auszahlen();
                break;
            case 6:
                loescheKonto();
                break;
            case 0: return;
            default: System.out.println("\n\nUngültige Eingabe.");
        }

        auswahlmenue();
    }

    public static void main(String[] args) {
        Steuerung steuerung = new Steuerung();
        steuerung.eingabeNeueKundenDaten();
        steuerung.auswahlmenue();
    }
}
