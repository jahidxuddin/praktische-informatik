import java.util.Scanner;
import java.text.DecimalFormat;

/*
    Berechnung des Notendurchschnitts
*/

public class Notenberechnung {

    int note1;
    int note2;
    int note3;
    int note4;
    int note5;
    int note6;

    int anzahlSchüler;
    String notendurchschnitt;

    public void titel() {
        System.out.println("********************************************");
        System.out.println("\tBerechnung Notendurchschnitt");
        System.out.println("********************************************");
    }

    public void eingabe() { // Abfrage der geschriebenen Noten
        Scanner scanner = new Scanner(System.in);

        System.out.println("\nNoteneingabe:");
        
        System.out.print("Bitte geben Sie die Anzahl der Note 1 ein: ");
        note1 = scanner.nextInt();

        System.out.print("Bitte geben Sie die Anzahl der Note 2 ein: ");
        note2 = scanner.nextInt();

        System.out.print("Bitte geben Sie die Anzahl der Note 3 ein: ");
        note3 = scanner.nextInt();

        System.out.print("Bitte geben Sie die Anzahl der Note 4 ein: ");
        note4 = scanner.nextInt();

        System.out.print("Bitte geben Sie die Anzahl der Note 5 ein: ");
        note5 = scanner.nextInt();

        System.out.print("Bitte geben Sie die Anzahl der Note 6 ein: ");
        note6 = scanner.nextInt();

        scanner.close();
    }

    public void berechnen() { // Berechnung des Notendurchschnitts
        anzahlSchüler = note1 + note2 + note3 + note4 + note5 + note6; 

        DecimalFormat formatter = new DecimalFormat("#.##");  
        notendurchschnitt = formatter.format(
            (note1 * 1 + note2 * 2 + note3 * 3 + note4 * 4 + note5 * 5 + note6 * 6) / (float) anzahlSchüler
        );
    }

    public void ausgabe() { // Ausgabe der Ergebnisse 
        System.out.println("\nAuswertung:");
        System.out.println("Insgesamt haben Schüler " + anzahlSchüler + " an der Klassen-");
        System.out.println("arbeit teilgenommen.\n");

        System.out.println("Note 1: " + note1 + " Schüler");
        System.out.println("Note 2: " + note2 + " Schüler");
        System.out.println("Note 3: " + note3 + " Schüler");
        System.out.println("Note 4: " + note4 + " Schüler");
        System.out.println("Note 5: " + note5 + " Schüler");
        System.out.println("Note 6: " + note6 + " Schüler\n");
        
        System.out.println("Der Notendurchschnitt beträgt: " + notendurchschnitt + ".");
    }

    public static void main(String[] args) {
        Notenberechnung notenberechnung = new Notenberechnung();
        notenberechnung.titel();
        notenberechnung.eingabe();
        notenberechnung.berechnen();
        notenberechnung.ausgabe();
    }
}