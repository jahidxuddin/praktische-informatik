package main.java.parameteruebergabeAnEineMethode;

import java.util.Scanner;

public class Gewichtsbestimmung {
    Scanner scanner = new Scanner(System.in);
    double[] gewichte;

    public void titel() {
        System.out.println("*********************************************");
        System.out.println("            Gewichtsbestimmung               ");
        System.out.println("*********************************************\n");
    }

    public void eingabe() {
        System.out.print("Geben Sie Ihre Koerpergroesse (in cm) ein: ");
        int koerpergroesse = scanner.nextInt();
        System.out.print("Geben Sie Ihr Geschlecht (m/w) ein: ");
        char geschlecht = scanner.next().charAt(0);
        gewichte = bestimmeGewicht(koerpergroesse, geschlecht);
    }

    public double[] bestimmeGewicht(int koerpergroesse, char geschlecht) {
        double normalgewicht = koerpergroesse - 100;
        double idealgewicht = normalgewicht - ((normalgewicht / 100) * (geschlecht == 'm' ? 10 : 15));
        double uebergewicht = normalgewicht + ((normalgewicht / 100) * 12);

        return new double[] { normalgewicht, idealgewicht, uebergewicht };
    }

    public void ausgabe() {
        System.out.println("\nAuswertung:");
        System.out.println("Normalgewicht: " + gewichte[0] + " kg");
        System.out.println("Idealgewicht: " + gewichte[1] + " kg");
        System.out.println("Uebergewicht: " + gewichte[2] + " kg");
    }

    public static void main(String[] args) {
        Gewichtsbestimmung gewichtsbestimmung = new Gewichtsbestimmung();
        gewichtsbestimmung.titel();
        gewichtsbestimmung.eingabe();
        gewichtsbestimmung.ausgabe();
    }
}
