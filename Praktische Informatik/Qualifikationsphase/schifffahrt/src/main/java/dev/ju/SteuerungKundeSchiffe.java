package dev.ju;

import java.util.Scanner;

public class SteuerungKundeSchiffe {
    private final Kunde[] kunden = new Kunde[42];

    public Kunde findeKunde(int kundenNr) {
        for (Kunde kunde : kunden) {
            if (kunde != null && kunde.getKundenNr() == kundenNr) {
                return kunde;
            }
        }

        return null;
    }

    public void eingabeNeueKundenAnlegen() {
        if (kunden[kunden.length - 1] != null) {
            System.out.println("\nMaximale Kundenanzahl erreicht!");
            return;
        }

        Scanner scanner = new Scanner(System.in);

        for (int i = 0; i < kunden.length; i++) {
            Kunde aktuellerKunde = kunden[i];

            if (aktuellerKunde != null) {
                continue;
            }

            System.out.print("\nName des " + (i + 1) + ". Kunden eingeben: ");
            String name = scanner.nextLine();

            kunden[i] = new Kunde(name);
            break;
        }

        System.out.print("\nMöchten Sie einen weiteren Kunden anlegen? (j,n): ");
        char eingabe = scanner.next().charAt(0);
        if (eingabe == 'j') {
            eingabeNeueKundenAnlegen();
        }
    }

    public void eingabeNeuesSchiffAnlegen() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("\nEingabe Kundennr: ");
        int kundenNr = scanner.nextInt();

        Kunde aktuellerKunde = findeKunde(kundenNr);

        if (aktuellerKunde == null) {
            System.out.println("\nKunde konnte nicht gefunden werden.");
            return;
        }

        Schiff[] aktuelleSchiffe = aktuellerKunde.getSchiffe();

        if (aktuelleSchiffe[aktuelleSchiffe.length - 1] != null) {
            System.out.println("\nMaximale Anzahl an Schiffe erreicht.");
        }

        scanner.nextLine();

        System.out.println("Eingabe Schiffsname: ");
        String schiffsname = scanner.nextLine();

        System.out.print("Eingabe Tonnage: ");
        double tonnage = scanner.nextDouble();

        for (int i = 0; i < aktuelleSchiffe.length; i++) {
            if (aktuelleSchiffe[i] == null) {
                aktuelleSchiffe[i] = new Schiff(schiffsname, tonnage);
                return;
            }
        }
    }

    public void ausgabeKunden() {
        System.out.println("\nAuflistung Kunden:");
        for (int i = 0; i < kunden.length; i++) {
            if (kunden[i] == null) {
                continue;
            }

            System.out.println((i + 1) + ". " + kunden[i].getName() + "(Kundenr: " + kunden[i].getKundenNr() + ")");
        }
    }

    public void ausgabeKundenMitIhrenSchiffen() {
        for (Kunde aktuellerKunde : kunden) {
            if (aktuellerKunde == null) {
                continue;
            }

            System.out.println("\nAlle Schiffe von " + aktuellerKunde.getName() + "(" + aktuellerKunde.getKundenNr() + "):");

            for (int j = 0; j < aktuellerKunde.getSchiffe().length; j++) {
                Schiff aktuellesSchiff = aktuellerKunde.getSchiffe()[j];

                if (aktuellesSchiff == null) {
                    continue;
                }

                System.out.println((j + 1) + ". Schiff: " + aktuellesSchiff.getName() + " (Tonnage: " + aktuellesSchiff.getTonnage() + ")");
            }
        }
    }

    public void menue() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("\nAuswahlmenü:");
        System.out.println("Neuen Kunden anlegen:\t\t\t\t\t\t\t---> 1");
        System.out.println("Neues Schiff anlegen:\t\t\t\t\t\t\t---> 2");
        System.out.println("Alle Kunden mit Kundennr ausgeben:\t\t\t\t---> 3");
        System.out.println("Alle Kunden mit ihren Schiffen ausgeben:\t\t---> 4");
        System.out.println("Programm beenden:\t\t\t\t\t\t\t\t---> 0");
        System.out.print("Auswahleingabe: ");

        char eingabe = scanner.next().charAt(0);
        switch (eingabe) {
            case '1':
                eingabeNeueKundenAnlegen();
                break;
            case '2':
                eingabeNeuesSchiffAnlegen();
                break;
            case '3':
                ausgabeKunden();
                break;
            case '4':
                ausgabeKundenMitIhrenSchiffen();
                break;
            case '0':
                System.exit(0);
            default:
                System.out.println("\n\nUngültige Eingabe!\n\n");
                menue();
                return;
        }

        menue();
    }

    public static void main(String[] args) {
        SteuerungKundeSchiffe steuerungKundeSchiffe = new SteuerungKundeSchiffe();
        steuerungKundeSchiffe.menue();
    }
}
