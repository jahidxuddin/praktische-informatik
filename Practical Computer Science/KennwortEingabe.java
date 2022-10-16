import java.util.Scanner;

public class KennwortEingabe {

    public void titel() {
        System.out.println("********************************************");
        System.out.println("\t      Kennworteingabe");
        System.out.println("********************************************");
    }

    public void eingabe() { // Abfrage des Passworts    
        Scanner scanner = new Scanner(System.in);

        String benutzereingabe = "";
        int versuche = 3;

        final String PASSWORT = "FLS";

        while (!benutzereingabe.equals(PASSWORT)) {
            if (versuche == 0) {
                System.out.println("\nIhre Benutzererkennung wird gesperrt!");
                System.exit(500);
            }
            System.out.print("\nBitte geben Sie ihr Kennwort ein: ");
            benutzereingabe = scanner.next();
            versuche--;
            if (!benutzereingabe.equals("FLS")) {
                System.out.println("Das Kennwort ist leider falsch!");
                System.out.println("Sie haben noch " + versuche + " von 3 Versuchen!");
            }
        }

        scanner.close();
    }

    public void ausgabe() { // Ausgabe der Validierung
        System.out.println("\nSie haben sich erfolgreich angemeldet!");
    }

    public static void main(String[] args) {
        KennwortEingabe kennwortEingabe = new KennwortEingabe();
        kennwortEingabe.titel();
        kennwortEingabe.eingabe(); 
        kennwortEingabe.ausgabe();
    }
}