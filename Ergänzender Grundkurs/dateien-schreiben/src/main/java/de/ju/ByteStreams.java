package de.ju;

import java.io.*;
import java.util.*;

public class ByteStreams {
    private final File datei;
    private String text;

    public ByteStreams() {
        this.datei = new File("test.txt");
        this.text = "";
        menu();
    }

    public void schreibeTextInDatei(String text) {
        try (FileOutputStream fos = new FileOutputStream(datei)) {
            byte[] bytes = text.getBytes();
            fos.write(bytes);
        } catch (IOException e) {
            System.out.println("Meldung: Text konnte nicht in die Datei geschrieben werden!");
        }
    }

    public void schreibeTextInDatei(String text, boolean append) {
        try (FileOutputStream fos = new FileOutputStream(datei, append)) {
            byte[] bytes = text.getBytes();
            fos.write(bytes);
        } catch (IOException e) {
            System.out.println("Meldung: Text konnte nicht in die Datei geschrieben werden!");
        }
    }

    public String dateiAuslesen() {
        StringBuilder fileContent = new StringBuilder();
        try (FileInputStream fis = new FileInputStream(datei)) {
            int data;
            while ((data = fis.read()) != -1) {
                fileContent.append((char) data);
            }
        } catch (IOException e) {
            System.out.println("Meldung: Datei konnte nicht ausgelesen werden!");
        }
        return fileContent.toString();
    }

    public void menu() {
        Scanner sc = new Scanner(System.in);
        Scanner scInner = new Scanner(System.in);

        boolean stop = false;
        do {
            System.out.println("\n\n\n\t**************************************************");
            System.out.println("\t                        Menü");
            System.out.println("\t**************************************************");
            System.out.println("\t<1> Text eingeben");
            System.out.println("\t<2> Text auf dem Bildschirm ausgeben");
            System.out.println("\t<3> Text in die Datei \"" + datei.getName() + "\" schreiben");
            System.out.println("\t    (Vorhandene Texte werden dadurch gelöscht");
            System.out.println("\t<4> Text in die Datei \"" + datei.getName() + "\" am Ende einfügen");
            System.out.println("\t<5> Inhalt der Datei \"" + datei.getName() + "\" auslesen");
            System.out.print("\tEingabe Auswahl: ");
            int auswahl = sc.nextInt();

            switch (auswahl) {
                case 1:
                    System.out.print("\tBitte geben Sie einen Text ein: ");
                    text = scInner.nextLine();
                    System.out.println("\n");
                    break;
                case 2:
                    System.out.println("\tBildschirmausgabe " + this.text);
                    System.out.println("\n");
                    break;
                case 3:
                    this.schreibeTextInDatei(text);
                    System.out.println("\tMeldung: Der Text \"" + text + "\" wurde in die Datei \"" + datei.getName() + "\" geschrieben!");
                    System.out.println("\n");
                    break;
                case 4:
                    this.schreibeTextInDatei(text, true);
                    System.out.println("\tMeldung: Der Text \"" + text + "\" wurde am Ende in die Datei \"" + datei.getName() + "\" eingefügt!");
                    System.out.println("\n");
                    break;
                case 5:
                    this.dateiAuslesen();
                    System.out.println("\tText \"" + this.dateiAuslesen() + "\" in der Datei \"" + datei.getName() + "\": ");
                    System.out.println("\n");
                    break;
                default:
                    break;
            }
        } while (!stop);
    }

    public static void main(String[] ignoredArgs) {
        new ByteStreams();
    }
}
