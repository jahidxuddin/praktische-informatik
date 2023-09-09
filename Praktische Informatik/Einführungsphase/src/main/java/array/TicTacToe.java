package main.java.array;

import java.util.Arrays;
import java.util.Scanner;

/*
  Tic Tac Toe
*/

public class TicTacToe {

  static Scanner scanner = new Scanner(System.in);

  char[][] spielfeld;
  boolean gameLoop;
  char aktuellerSpieler;

  public void titel() {
    System.out.println("*********************************************");
    System.out.println("                 TicTacToe                   ");
    System.out.println("*********************************************\n");
  }

  public void starteSpiel() {
    System.out.print("Bitte wählen sie eine Spielfeldgröße: ");
    int größe = scanner.nextInt();
    spielfeld = new char[größe][größe];
    for (char[] spalte : spielfeld) {
      Arrays.fill(spalte, '*');
    }
    gameLoop = true;
    aktuellerSpieler = 'X';
    while (gameLoop) {
      ausgabeSpielfeld();
      System.out.println(
        "\n\nSpieler " + (aktuellerSpieler == 'X' ? "1(=X):" : "2(=O):")
      );
      setzeSpielstein();
      auswertungSpielfeld();
      aktuellerSpieler = aktuellerSpieler == 'X' ? 'O' : 'X';
    }
  }

  public void setzeSpielstein() {
    System.out.print(
      "Eingabe Koordinate x (0-" + (spielfeld.length - 1) + "): "
    );
    int x = scanner.nextInt();
    System.out.print(
      "Eingabe Koordinate y (0-" + (spielfeld.length - 1) + "): "
    );
    int y = scanner.nextInt();
    char feld = spielfeld[y][x];
    if (feld == 'X' || feld == 'O') {
      System.out.println("\nFeld ist bereits belegt!\n");
      setzeSpielstein();
      return;
    }
    spielfeld[y][x] = aktuellerSpieler;
  }

  public void ausgabeSpielfeld() {
    for (int i = 0; i < spielfeld.length; i++) {
      for (int j = 0; j < spielfeld[i].length; j++) {
        System.out.print((j == 0 ? "\n" : "") + spielfeld[i][j] + " ");
      }
    }
  }

  public boolean überprüfeZeilen() {
    boolean gleicheZeile = false;
    for (int i = 0; i < spielfeld.length; i++) {
      for (int j = 0; j < spielfeld[i].length - 1; j++) {
        char aktuellesFeld = spielfeld[i][j];
        char nächstesFeld = spielfeld[i][j + 1];
        if (aktuellesFeld == nächstesFeld && aktuellesFeld != '*') {
          gleicheZeile = true;
        } else {
          gleicheZeile = false;
          break;
        }
      }
      if (gleicheZeile) {
        break;
      }
    }
    return gleicheZeile;
  }

  public boolean überprüfeSpalten() {
    boolean gleicheSpalte = false;
    for (int i = 0; i < spielfeld[0].length; i++) {
      for (int j = 0; j < spielfeld.length - 1; j++) {
        char aktuellesFeld = spielfeld[j][i];
        char nächstesFeld = spielfeld[j + 1][i];
        if (aktuellesFeld == nächstesFeld && aktuellesFeld != '*') {
          gleicheSpalte = true;
        } else {
          gleicheSpalte = false;
          break;
        }
      }
      if (gleicheSpalte) {
        break;
      }
    }
    return gleicheSpalte;
  }

  public boolean überprüfeDiagonaleLinksRechts() {
    for (int i = 0; i < spielfeld.length - 1; i++) {
      char aktuellesFeld = spielfeld[i][i];
      char nächstesFeld = spielfeld[i + 1][i + 1];
      if (aktuellesFeld != nächstesFeld || aktuellesFeld == '*') {
        return false;
      }
    }
    return true;
  }

  public boolean überprüfeDiagonaleRechtsLinks() {
    for (int i = spielfeld.length - 1; i > 0; i--) {
      char aktuellesFeld = spielfeld[spielfeld.length - i - 1][i];
      char nächstesFeld = spielfeld[spielfeld.length - i][i - 1];
      if (aktuellesFeld != nächstesFeld || aktuellesFeld == '*') {
        return false;
      }
    }
    return true;
  }

  public void auswertungSpielfeld() {
    if (
      überprüfeZeilen() ||
      überprüfeSpalten() ||
      überprüfeDiagonaleLinksRechts() ||
      überprüfeDiagonaleRechtsLinks()
    ) {
      System.out.println("\n" + aktuellerSpieler + " gewinnt!");
      gameLoop = false;
    }
  }

  public static void main(String[] args) {
    TicTacToe ticTacToe = new TicTacToe();
    ticTacToe.titel();
    char loop = 'J';
    while (loop == 'J' || loop == 'j') {
      ticTacToe.starteSpiel();
      System.out.print("\nMöchten Sie das Spiel wiederholen? (J/N) ");
      loop = scanner.next().charAt(0);
    }
    scanner.close();
  }
}
