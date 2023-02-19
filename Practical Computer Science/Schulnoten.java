import java.text.DecimalFormat;
import java.util.Scanner;

public class Schulnoten {
    static Scanner scanner = new Scanner(System.in);
    DecimalFormat formatter = new DecimalFormat("#.##");

    int[] notenspiegel = new int[15 + 1];
    int[] notenliste;

    public void titel() {
        System.out.println("*********************************************");
        System.out.println("                 Noteneingabe                ");
        System.out.println("*********************************************\n");
    }

    public void eingabe() {
        System.out.print("Wie viele Schüler haben an der Klassenarbeit teilgenommen: ");
        int schüleranzahl = scanner.nextInt();
        notenliste = new int[schüleranzahl];

        for (int i = 0; i < notenliste.length; i++) {
            System.out.print("Notenpunkte des " + (i + 1) + ". Schülers eingeben: ");
            notenliste[i] = scanner.nextInt();
        }
    }

    public void notenliste() {
        System.out.println("\nAusgabe Notenliste Schüler:\n");
        for (int i = 0; i < notenliste.length; i++) {
            System.out.println((i + 1) + ". Schüler: " + notenliste[i] + " Punkte");
        }
    }

    public void notenspiegel() {
        System.out.println("\nAusgabe Notenspiegel:");
        for (int i = 15; i > 0; i--) {
            for (int j = 0; j < notenliste.length; j++) {
                if (notenliste[j] == i) {
                    notenspiegel[i]++;
                }
            }

            System.out.println(i + " Punkte: " + notenspiegel[i] + " Schüler");
        }
    }

    public void notendurchschnitt() {
        double notendurchschnitt = 0;

        for (int i = 0; i < notenliste.length; i++) {
            notendurchschnitt += notenliste[i];
        }

        notendurchschnitt /= notenliste.length;

        System.out.println("\nDer Notendurchschnitt der Klasse beträgt: " + formatter.format(notendurchschnitt));
    }

    public void ausgabe() {
        notenliste();
        notenspiegel();
        notendurchschnitt();
    }

    public static void main(String[] args) {
        Schulnoten schulnoten = new Schulnoten();
        schulnoten.titel();
        char loop = 'J';
        while (loop == 'J' || loop == 'j') {
            schulnoten.eingabe();
            schulnoten.ausgabe();
            System.out.print("\nMöchten Sie die Noteneingabe wiederholen? (J/N) ");
            loop = scanner.next().charAt(0);
        }
        scanner.close();
    }
}
