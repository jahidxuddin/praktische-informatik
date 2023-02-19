import java.util.Scanner;

/*
    Quiz über Planeten
*/

public class PlanetenQuiz {
    static Scanner scanner = new Scanner(System.in);

    final String[] planetenLösungen = { "Merkur", "Uranus", "Venus", "Neptun", "Erde", "Saturn", "Mars", "Jupiter"};
    String[] eingebenePlaneten;
    String ergebnis = ""; 

    public void titel() {
        System.out.println("*********************************************");
        System.out.println("                 Planeten-Quiz               ");
        System.out.println("*********************************************\n");
    }

    public void eingabe() {
        System.out.print("Wie viele Planeten kennen Sie? ");
        int bekanntePlaneten = scanner.nextInt();
        if (bekanntePlaneten > 8) {
            System.out.println("Hinweis: Es gibt nur 8 Planeten!!\n");
            eingabe();
            return;
        }
        eingebenePlaneten = new String[bekanntePlaneten];
        System.out.println("\nEingabe:");
        for (int i = 0; i < eingebenePlaneten.length; i++) {
            System.out.print((i + 1) + ". Planet: ");
            String eingabe = scanner.next();
            boolean wurdeBereitsEingegeben = false;
            for (int j = 0; j < eingebenePlaneten.length; j++) {
                if (eingabe.equalsIgnoreCase(eingebenePlaneten[j])) {
                    System.out.println("Hinweis: Dieser Planet wurde schon eingegeben!!\n");
                    wurdeBereitsEingegeben = true;
                    break;
                }
            }
            if (wurdeBereitsEingegeben) {
                i -= 1;
                continue;
            }
            eingebenePlaneten[i] = eingabe;
        }
    }

    public void verarbeitung() {
        ergebnis = "\nVon den " + eingebenePlaneten.length + " eingebenen Planeten sind ";
    
        int richtigEingegeben = 0;
        for (int i = 0; i < planetenLösungen.length; i++) {
            for (int j = 0; j < eingebenePlaneten.length; j++) {
                if (planetenLösungen[i].equalsIgnoreCase(eingebenePlaneten[j])) {
                    richtigEingegeben += 1;
                }
            }
        }
        ergebnis += richtigEingegeben + " richtig!";
    }

    public void ausgabe() {
        System.out.println("\nAusgabe: ");
        System.out.println("Folgende " + eingebenePlaneten.length + " Planeten wurden eingegeben:");
        for (int i = 0; i < eingebenePlaneten.length; i++) {
            System.out.println((i + 1) + " Planet: " + eingebenePlaneten[i]);
        }
        System.out.println(ergebnis);
    }

    public static void main(String[] args) {
        PlanetenQuiz planetenQuiz = new PlanetenQuiz();
        planetenQuiz.titel();
        char loop = 'J';
        while (loop == 'J' || loop == 'j') {
            planetenQuiz.eingabe();
            planetenQuiz.verarbeitung();
            planetenQuiz.ausgabe();
            System.out.print("\nMöchten Sie das Programm wiederholen? (J/N) ");
            loop = scanner.next().charAt(0);
        }
        scanner.close();
    }
}
