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

        boolean hatMaximaleKontonlaengeErreicht = this.kunde.neuesKontoAnlegen(new Konto(kontoNr, kontoTyp));

        if (hatMaximaleKontonlaengeErreicht) {
            System.out.println("\n\nMaximale Konten Anzahl erreicht. Weitere Konten können nicht angelegt werden.");
        }
    }

    public void kontenUebersicht() {
        System.out.println("\n\nKontenübersicht");
        System.out.println(kunde.getKundenName() + " hat folgende Konten:");
        System.out.println(kunde.kontenUebersicht());
    }

    public void auswahlmenue() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("\n\nAuswahlmenü");
        System.out.println("Neues Konto eröffnen:   ---> 1");
        System.out.println("Kontenübersicht:        ---> 2");
        System.out.println("Programmbeenden:        ---> 0");
        System.out.print("Auswahleingabe:              ");

        switch (scanner.nextInt()) {
            case 1: {
                eingabeNeuesKontoAnlegen();
                break;
            }
            case 2: {
                kontenUebersicht();
                break;
            }
            case 0: {
                return;
            }
        }

        auswahlmenue();
    }

    public static void main(String[] args) {
        Steuerung steuerung = new Steuerung();
        steuerung.eingabeNeueKundenDaten();
        steuerung.auswahlmenue();
    }
}
