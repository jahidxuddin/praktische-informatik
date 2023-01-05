import java.util.Scanner;

/*
    Minispiel: Erratung der Zufallszahl 
*/

public class Zufallszahlen {
    
    static Scanner scanner = new Scanner(System.in);

    final int OBERGRENZE = 10;
    final int UNTERGRENZE = 1;
    int ZUFALLSZAHL = (int) ((OBERGRENZE - UNTERGRENZE + 1) * Math.random() + UNTERGRENZE);
    int versuche = 0;

    public void titel() {
        System.out.println("*****************************************************");
        System.out.println("                Zufallszahlen erraten                ");
        System.out.println("*****************************************************");
    }

    public void eingabe() { // Abfrage der benötigten Daten  
        int benutzereingabe = 0;

        while (benutzereingabe != ZUFALLSZAHL) {
            versuche++;
            if (versuche == 5) {
                System.out.println("\nDu hast leider verloren!");
                System.exit(500);
            }
            System.out.print("\nGeben Sie eine Zahl zwischen 1 und 10 ein: ");
            benutzereingabe = scanner.nextInt();
            if (benutzereingabe > OBERGRENZE) {
                System.out.println("Falsche Eingabe! Wiederholen Sie die Eingabe!");
            } else if (benutzereingabe < UNTERGRENZE) {
                System.out.println("Falsche Eingabe! Wiederholen Sie die Eingabe!");
            } else if (benutzereingabe < ZUFALLSZAHL) {
                if (5 - versuche == 1) {
                    System.out.println("Die Zahl ist zu niedrig! Sie haben noch " + (5 - versuche) + " Versuch.");
                } else {
                    System.out.println("Die Zahl ist zu niedrig! Sie haben noch " + (5 - versuche) + " Versuche."); 
                }
            } else if (benutzereingabe > ZUFALLSZAHL) {
                if (5 - versuche == 1) {
                    System.out.println("Die Zahl ist zu hoch! Sie haben noch " + (5 - versuche) + " Versuch.");
                } else {
                    System.out.println("Die Zahl ist zu hoch! Sie haben noch " + (5 - versuche) + " Versuche.");
                }
            }
        }
        
    }

    public void ausgabe() { // Ausgabe der Ergebnisse
        if (versuche == 1) {
            System.out.println("Sie haben nach " + versuche + " Versuch gewonnen!");
        } else {
            System.out.println("Sie haben nach " + versuche + " Versuchen gewonnen!"); 
        }
        
        System.out.print("\nMöchten Sie noch eine Berechnung durchführen (j/n)? ");
        versuche = 0;
        ZUFALLSZAHL = (int) ((OBERGRENZE - UNTERGRENZE + 1) * Math.random() + UNTERGRENZE);
    }

    public static void main(String[] args) {
        Zufallszahlen zufallszahlen = new Zufallszahlen();
        zufallszahlen.titel();
        boolean loop = true;
        while (loop) {
            zufallszahlen.eingabe(); 
            zufallszahlen.ausgabe();
            switch (scanner.next()) {
                case "j":
                    loop = true;
                    break;
                case "J":
                    loop = true;
                    break;
                default:
                    loop = false;
                    break;
            }
        }
        scanner.close();
    } 

}
