import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/*
    Berechnung des Mittelwertes
*/

public class Mittelwertberechnung {

    static Scanner scanner = new Scanner(System.in);
    DecimalFormat formatter = new DecimalFormat("#.##");

    int zahl = 0;    
    List<Double> zahlen = new ArrayList<>();
    double mittelwert;

    public void titel() {
        System.out.println("********************************************");
        System.out.println("\t     Mittelwertberechnung");
        System.out.println("********************************************");
    }

    public void eingabe() { // Abfrage der benötigten Daten    
        boolean loop = true;
        while (loop) {
            zahl++;

            System.out.print("Bitte geben Sie die " + zahl + ". Zahl ein: ");
            double eingabe = scanner.nextDouble();
            zahlen.add(eingabe);

            System.out.print("Möchten Sie eine weitere Zahl eingeben (j, n)? ");
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
    }

    public void berechnen() { // Berechnung des Mittelwertes
        double zwischenergebnis = 0;
        int i = 0;
        while (i < zahlen.size()) {
            zwischenergebnis = zwischenergebnis + zahlen.get(i);
            i++;
        }
        mittelwert = zwischenergebnis / zahl;
    }

    public void ausgabe() { // Ausgabe des Mittelwertes
        System.out.println("\nMittelwert von den Zahlen:");
        int i = 0;
        while (i < zahlen.size()) {
            System.out.println(i + 1 + ". Zahl: " + zahlen.get(i));
            i++;
        }
        System.out.println("\nbeträgt: " + formatter.format(mittelwert) + ".");
        System.out.print("\nMöchten Sie eine neue Berechnung durchführen (J/N)? ");
        zahl = 0;
        zahlen.clear();
    }

    public static void main(String[] args) {
        Mittelwertberechnung mittelwertberechnung = new Mittelwertberechnung();
        mittelwertberechnung.titel();
        boolean loop = true;
        while (loop) {
            mittelwertberechnung.eingabe(); 
            mittelwertberechnung.berechnen();
            mittelwertberechnung.ausgabe();
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