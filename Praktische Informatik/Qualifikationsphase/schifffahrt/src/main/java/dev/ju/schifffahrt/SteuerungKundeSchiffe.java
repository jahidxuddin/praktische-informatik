package dev.ju.schifffahrt;

import java.util.Scanner;

public class SteuerungKundeSchiffe {
    private final Kunde[] kunden;

    public SteuerungKundeSchiffe() {
        this.kunden = new Kunde[42];
    }

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

        System.out.print("Eingabe Schiffstyp (1: Motorschiff, 2: Segelschiff): ");
        char schiffstyp = scanner.next().charAt(0);

        System.out.print("Eingabe Schiffsname: ");
        scanner.nextLine();
        String schiffsname = scanner.nextLine();

        System.out.print("Eingabe Tonnage: ");
        double tonnage = scanner.nextDouble();

        boolean schiffanlegungErfolgreich;

        switch (schiffstyp) {
            case '1': {
                System.out.print("Motorleistung (in PS): ");
                double motorleistung = scanner.nextDouble();

                schiffanlegungErfolgreich = aktuellerKunde.neuesMotorSchiffAnlegen(schiffsname, tonnage, motorleistung);

                break;
            }
            case '2': {
                System.out.print("Segelflaeche (in QM): ");
                double segelflaeche = scanner.nextDouble();

                schiffanlegungErfolgreich = aktuellerKunde.neuesSegelSchiffAnlegen(schiffsname, tonnage, segelflaeche);

                break;
            }
            default: {
                schiffanlegungErfolgreich = false;
            }
        }

        if (!schiffanlegungErfolgreich) {
            System.out.println("\nSchiff konnte nichte gefunden werden.");
            return;
        }

        System.out.println("\nSchiff wurde erfolgreich angelegt.");
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

                System.out.println((j + 1) + ". Schiff: " + aktuellesSchiff.getName());
                System.out.println("\tSchiffstyp: " + aktuellesSchiff.getClass().getSimpleName());
                System.out.println("\tTonnage: " + aktuellesSchiff.getTonnage());

                // Schiff schiff = new Motorschiff();
                if (aktuellesSchiff instanceof Motorschiff) {
                    System.out.println("\tMotorleistung: " + ((Motorschiff) aktuellesSchiff).getMotorleistung());
                }

                // Schiff schiff = new Segelschiff();
                if (aktuellesSchiff instanceof Segelschiff) {
                    System.out.println("\tSegelflaeche: " + ((Segelschiff) aktuellesSchiff).getSegelflaeche());
                }
            }
        }
    }

    public void menue() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("\nAuswahlmenü:");
        System.out.println("Neuen Kunden anlegen:\t\t\t\t\t\t\t---> 1");
        System.out.println("Neues Schiff anlegen:\t\t\t\t\t\t\t---> 2");
        System.out.println("Alle Kunden mit Kundennr ausgeben:\t\t\t\t---> 3");
        System.out.println("Alle Kunden mit Ihren Schiffen ausgeben:\t\t---> 4");
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
