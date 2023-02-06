import java.util.HashMap;
import java.util.Scanner;

/*
    Potenzierung von Zahlen
*/

public class Schulnoten {
    
    static Scanner scanner = new Scanner(System.in);

    int anzahlSchüler;
    int[] notenliste; 

    HashMap<Integer, Integer> notenspiegel = new HashMap<>();

    String notenlisteAusgabe, notenspiegelAusgabe, notendurchschnittAusgabe;

    public void titel() {
        System.out.println("*********************************************");
        System.out.println("             Noteneingabe                    ");
        System.out.println("*********************************************\n");
    }

    public void eingabe() { // Abfrage der benötigten Daten  
        System.out.print("Wie viele Schüler haben an der Klassenarbeit teilgenommen? ");
        anzahlSchüler = scanner.nextInt();
        notenliste = new int[anzahlSchüler];
        for (int i = 0; i < anzahlSchüler; i++) {
            System.out.print("Notenpunkte des " + (i + 1) + ". Schülers eingeben: ");
            notenliste[i] = scanner.nextInt();
        }
    }

    public void notenspiegel() { // Berechnung des Notenspiegels
        for (int i = 0; i <= 15; i++) {
            notenspiegel.put(i, 0);
        }

        notenspiegelAusgabe = "\nAusgabe Notenspiegel:";

        for (int i = 0; i <= 15; i++) {
            if (i < notenliste.length) {
                notenspiegel.put(notenliste[i], notenspiegel.get(notenliste[i]) + 1);
            } else {
                break;
            }
        }

        for (int i = 15; i >= 0; i--) {
            if (i < 10) {
                notenspiegelAusgabe += "\n " + i + " Punkte: " + notenspiegel.get(i) + " Schüler";
            } else {
                notenspiegelAusgabe += "\n" + i + " Punkte: " + notenspiegel.get(i) + " Schüler";
            }
        }
    }

    public void notendurchschnitt() { // Berechnung des Notendurchschnitts
        notendurchschnittAusgabe = "\nDer Notendurchschnitt der Klasse beträgt: ";
    }

    public void ausgabe() { // Ausgabe der Ergebnisse
        notenlisteAusgabe = "\nAusgabe Notenliste Schüler:\n";
        for (int i = 0; i < notenliste.length; i++) {
            notenlisteAusgabe += "\n" + (i + 1) + ". Schüler: " + notenliste[i] + " Punkte";
        }
        System.out.println(notenlisteAusgabe);
        System.out.println(notenspiegelAusgabe);
    }

    public static void main(String[] args) {
        Schulnoten schulnoten = new Schulnoten();
        schulnoten.titel();
        char loop = 'J';
        while (loop == 'J' || loop == 'j') {
            schulnoten.eingabe();
            schulnoten.notenspiegel();
            schulnoten.notendurchschnitt();
            schulnoten.ausgabe();
            System.out.print("\nMöchten Sie eine neue Berechnung durchführen? (J/N) ");
            loop = scanner.next().charAt(0);
        }
        scanner.close();
    } 
}