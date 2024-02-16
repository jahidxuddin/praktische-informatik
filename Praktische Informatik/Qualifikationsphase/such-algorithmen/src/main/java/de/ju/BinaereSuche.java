package de.ju;

import java.util.Scanner;

public class BinaereSuche {
    private final char[] buchstaben = {'a','b','c','d','e', 'f', 'g', 'h', 'i', 'j','k','l','m','n', 'o', 'p', 'q', 'r','s','t','u','v','w', 'x', 'y', 'z'};
    private final String[] haustiere = {"Fisch", "Hamster", "Hund", "Katze", "Meerschweinchen", "Vogel"};
    private int durchlauf;
    private int auswahl;

    public void eingabe(){
        Scanner sc = new Scanner(System.in);
        int position = -99;

        System.out.print("\tMöchten Sie ein Zeichen (=1) oder einen Suchbegriff (=2) eingeben: ");
        int auswahl = sc.nextInt();

        switch (auswahl) {
            case 1 :
                System.out.print("\tBitte geben Sie Zeichen ein: ");
                char zeichen = sc.next().charAt(0);
                position = suche(zeichen, buchstaben);
                break;
            case 2 :
                System.out.print("\tBitte geben Sie einen Suchbegriff ein: ");
                String suchbegriff = sc.next();
                position = suche(suchbegriff, haustiere);
                break;
        }
        this.ausgabe(position);
        System.out.println();
    }

    public int suche(char zeichen, char[] array) {
        int erstePosition = 0;
        int letztePosition = array.length;
        int mitte = (erstePosition + letztePosition / 2);

        while (erstePosition <= letztePosition) {
            durchlauf++;
            mitte = (erstePosition + letztePosition / 2);
            if (array[mitte] == zeichen) {
                return mitte;
            } else if (array[mitte] > zeichen) {
                letztePosition = mitte - 1;
            } else {
                erstePosition = mitte + 1;
            }
        }

        System.out.println("\n\tProtokoll Durchläufe");

        System.out.println("\tNr. " + durchlauf + ", erste Position: " + erstePosition + ", letzte Position: " + letztePosition + " -> Mitte: " + mitte );

        return -99;
    }

    public int suche(String suchbegriff, String[] array) {
        int erstePosition = 0;
        int letztePosition = array.length;

        while (erstePosition <= letztePosition) {
            durchlauf++;
            int mitte = (erstePosition + letztePosition) / 2;

            if (array[mitte].equals(suchbegriff)) {
                return mitte;
            } else if (array[mitte].charAt(0) > suchbegriff.charAt(0)) {
                letztePosition = letztePosition - 1;
            } else {
                erstePosition = erstePosition + 1;
            }
        }

        return -99;
    }

    public void ausgabe(int gefundenePosition){
        String ausgabe;

        if (auswahl == 1) {
            ausgabe = "Das Zeichen";
        } else {
            ausgabe = "Der Suchbegriff";
        }

        System.out.println("\n\n\tSuchergebnis:");

        if (gefundenePosition >= 0) {
            System.out.println("\t" + ausgabe + " befindet sich an der " + gefundenePosition + ". Position im Array!");
        } else {
            System.out.println("\t" + ausgabe + " ist nicht im Array vorhanden!");
        }
        System.out.println("\tAnzahl der Suchdurchläufe: " + durchlauf);
    }

    public static void main (String[] args) {
        BinaereSuche bs = new BinaereSuche();
        System.out.println("\n\n\n\t*********************************************************************" );
        System.out.println("\t\t\t\tBinäre Suche");
        System.out.println("\t*********************************************************************" );
        bs.eingabe();
        System.out.println("\n\n\n");
    }
}
