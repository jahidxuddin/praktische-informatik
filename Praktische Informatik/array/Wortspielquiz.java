import java.util.Scanner;

/*
  Wortspielquiz
*/

public class Wortspielquiz {
    static Scanner scanner = new Scanner(System.in);

    final String[] FRAGEN = {
            "Sie wird kürzer, je länger man zieht",
            "Dieser Ring ist nicht rund",
            "Dieser Baum hat keine Blätter",
            "Dieses Tal will jeder haben",
            "Ein Zahn, der nicht beißt",
            "Ein Stuhl ohne Beine"
    };

    final String[] LÖSUNGEN = {
            "Zigarre",
            "Boxring",
            "Purzelbaum",
            "Kapital",
            "Loewenzahn",
            "Dachstuhl"
    };

    int[] gezogeneZahlen = { 6, 6, 6, 6, 6, 6 };
    int antworten;
    int richtigeAntworten;

    String aktuelleFrage = "";

    public void titel() {
        System.out.println("*********************************************");
        System.out.println("                 Wortspielquiz               ");
        System.out.println("*********************************************\n");
    }

    public boolean auswahlFragen(int i) {
        int zufallszahl = (int) ((5 - 0 + 1) * Math.random() + 0);
        for (int j = 0; j < gezogeneZahlen.length; j++) {
            if (gezogeneZahlen[j] == zufallszahl) {
                return false;
            }
        }
        gezogeneZahlen[i] = zufallszahl;
        aktuelleFrage = "\"" + FRAGEN[zufallszahl] + "!\"";
        return true;
    }

    public String eingabeAntwort() {
        System.out.print("Eingabe Lösung: ");
        return scanner.next();
    }

    public boolean auswertung(String antwort) {
        for (int i = 0; i < LÖSUNGEN.length; i++) {
            if (LÖSUNGEN[i].equalsIgnoreCase(antwort)) {
                richtigeAntworten += 1;
                return true;
            }
        }
        return false;
    }

    public void eingabe() {
        for (int i = 0; i < FRAGEN.length; i++) {
            if (!auswahlFragen(i)) {
                i -= 1;
                continue;
            }
            antworten += 1;
            System.out.println((i + 1) + ". Frage: " + aktuelleFrage);
            if (auswertung(eingabeAntwort())) {
                System.out.println("\nAuswertung:");
                System.out.println("Herzlichen Glückwunsch, Ihre Antwort ist richtg!");
            } else {
                System.out.println("\nAuswertung:");
                System.out.println("Die Antwort war leider falsch!");
            }
            if (i == FRAGEN.length - 1) {
                break;
            }
            System.out.print("\nMöchten Sie noch einmal spielen? (J/N) ");
            char loop = scanner.next().charAt(0);
            System.out.println();
            if (loop == 'N' || loop == 'n') {
                break;
            }
        }
    }

    public void ausgabeGesamtauswertung() {
        System.out.println("\nSie haben " + richtigeAntworten + " von " + antworten + " Antworten richtig!");
    }

    public static void main(String[] args) {
        Wortspielquiz wortspielquiz = new Wortspielquiz();
        wortspielquiz.titel();
        wortspielquiz.eingabe();
        wortspielquiz.ausgabeGesamtauswertung();
        scanner.close();
    }
}
