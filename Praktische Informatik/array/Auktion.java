import java.util.Arrays;
import java.util.Scanner;

/*
  Auktion
*/

public class Auktion {
  static Scanner scanner = new Scanner(System.in);

  String[] bietende = new String[3];
  double[] gebote = new double[3];

  String objekt;

  String niedrigstBiedender = "";
  double niedrigstesGebot;

  String höchstBiedender = "";
  double höchstesGebot;

  public void titel() {
    System.out.println("*********************************************");
    System.out.println("                 Auktion                   ");
    System.out.println("*********************************************\n");
  }

  public void eingabe() {
    System.out.print("Was für ein Objekt soll versteigert werden: ");
    objekt = scanner.next();
    Arrays.fill(bietende, "-");
    for (int i = 0; i < bietende.length; i++) {
      System.out.print("\nName des " + (i + 1) + ". Interessenten: ");
      String name = scanner.next();
      bietende[i] = name;
      System.out.print("Wie viel EUR bietet " + name + " für den " + objekt + ": ");
      double gebot = scanner.nextInt();
      gebote[i] = gebot;
      if (i == 2) {
        System.out.println("\nHinweis: Es können keine Gebote mehr angenommen werden.");
        break;
      }
      System.out.print("Möchten Sie noch einen weiteren Interessenten eingeben (J/N): ");
      String weitererInteressent = scanner.next();
      if (!weitererInteressent.equalsIgnoreCase("j")) {
        break;
      }
    }
  }

  public double min() {
    double min = gebote[0];
    for (int i = 0; i < gebote.length; i++) {
      if (min > gebote[i]) {
        min = gebote[i];
      }
    }
    return min;
  }

  public double max() {
    double max = gebote[0];
    for (int i = 0; i < gebote.length; i++) {
      if (max < gebote[i]) {
        max = gebote[i];
      }
    }
    return max;
  }

  public void verarbeitung() {
    for (int i = 0; i < gebote.length; i++) {
      if (gebote[i] == min()) {
        niedrigstBiedender = bietende[i];
        niedrigstesGebot = gebote[i];
      }
      if (gebote[i] == max()) {
        höchstBiedender = bietende[i];
        höchstesGebot = gebote[i];
      }
    }
  }

  public void ausgabe() {
    System.out.println("\nFolgende Gebote wurden getätigt:");
    for (int i = 0; i < bietende.length; i++) {
      System.out.println((i + 1) + ". Gebot: " + bietende[i] + ", " + gebote[i] + " EUR");
    }
    System.out.println("\n" + höchstBiedender + " hat mit " + höchstesGebot + " EUR das höchste Gebot getätigt.");
    System.out.println(niedrigstBiedender + " hat mit " + niedrigstesGebot + " EUR das niedrigste Gebot getätigt.");
    System.out.println("\nHerzlichen Glückwunsch " + höchstBiedender + " zum Kauf des/der " + objekt + "!");
  }

  public static void main(String[] args) {
    Auktion auktion = new Auktion();
    auktion.titel();
    char loop = 'J';
    while (loop == 'J' || loop == 'j') {
      auktion.eingabe();
      auktion.verarbeitung();
      auktion.ausgabe();
      System.out.print("\nMöchten Sie die Eingabe wiederholen? (J/N) ");
      loop = scanner.next().charAt(0);
    }
    scanner.close();
  }
}
