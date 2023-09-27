package dev.ju.bank;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Steuerung {
    private final List<Kunde> kunden = new ArrayList<>();

    public Kunde eingabeNeueKundenDaten() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("\nName des Kontoinhabers: ");
        String kundenName = scanner.nextLine();
        System.out.print("Eingabe Kundennr: ");
        int kundenNr = scanner.nextInt();

        return new Kunde(kundenNr, kundenName);
    }

    public void eingabeNeuesKontoAnlegen(Kunde kunde) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Eingabe KontoNr: ");
        String kontoNr = scanner.next();
        System.out.print("Eingabe Kontotyp: ");
        String kontoTyp = scanner.next();

        kunde.neuesKontoAnlegen(new Konto(kontoNr, kontoTyp));
    }

    public void eingabe() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            Kunde kunde = eingabeNeueKundenDaten();
            kunden.add(kunde);
            eingabeNeuesKontoAnlegen(kunde);

            System.out.print("\nMöchten Sie einen weiteren Kunden anlegen? (j,n) ");
            char eingabe = scanner.next().charAt(0);
            if (eingabe == 'n' || eingabe == 'N') {
                break;
            }
        }

        scanner.close();
    }

    public void ausgabe() {
        System.out.println("\nKontenübersicht:");
        kunden.forEach((kunde) -> System.out.println(kunde.kontoUebersicht()));
    }

    public static void main(String[] args) {
        Steuerung steuerung = new Steuerung();
        steuerung.eingabe();
        steuerung.ausgabe();
    }
}
