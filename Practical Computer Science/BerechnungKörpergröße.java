import java.text.DecimalFormat;
import java.util.Scanner;

/*
    Berechnung der Körpergröße
*/

public class BerechnungKörpergröße {

    double oberschenkellänge;
    int alter;
    String geschlecht;

    double körpergröße;

    DecimalFormat formatter = new DecimalFormat("#.##");
 
    public void titel() {
        System.out.println("********************************************");
        System.out.println("\t   Berechnung Körpergröße");
        System.out.println("********************************************\n");
    }

    public void eingabe() { // Abfrage der benötigten Daten
        Scanner scanner = new Scanner(System.in);

        System.out.print("Wie lang sind Ihre Oberschenkel (in m)? ");
        oberschenkellänge = scanner.nextDouble();

        System.out.print("Wie alt sind Sie? ");
        alter = scanner.nextInt();
        
        System.out.print("Sind Sie männlich (1) oder weiblich (2)? ");
        geschlecht = scanner.next();

        scanner.close();
    }

    public void berechnen() { // Berechnung der Körpergröße
        if (geschlecht.equals("1")) {
            geschlecht = "männlich";
            körpergröße = 2.238 * oberschenkellänge + 0.69089;
        } 
        
        if (geschlecht.equals("2")) {
            geschlecht = "weiblich";
            körpergröße = 2 * oberschenkellänge + 0.61417;
        }

        if (alter > 30) {
            körpergröße = körpergröße - 0.0006 * (alter - 30);
        }
    }

    public void ausgabe() { // Ausgabe der Ergebnisse
        System.out.println("\nAuswertung:");
        System.out.println("Sie sind " + alter + " Jahre alt, " + geschlecht + " und haben");
        System.out.println("eine Oberschenkellänge von " + oberschenkellänge + " m.\n");
        System.out.println("Wahrscheinlich sind Sie " + formatter.format(körpergröße) + " m groß.");
    }

    public static void main(String[] args) {
        BerechnungKörpergröße berechnungKörpergröße = new BerechnungKörpergröße();
        berechnungKörpergröße.titel();
        berechnungKörpergröße.eingabe();
        berechnungKörpergröße.berechnen();
        berechnungKörpergröße.ausgabe();    
    }
}