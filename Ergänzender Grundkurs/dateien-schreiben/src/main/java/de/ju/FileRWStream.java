import java.io.*;
import java.util.Scanner;

public void schreibeTextInDatei(File datei, String text, boolean append) {
    try (FileWriter fw = new FileWriter(datei, append)) {
        fw.write(text);
    } catch (IOException _) {
        System.out.println("Meldung: Text konnte nicht in die Datei geschrieben werden!");
    }
}

public String dateiAuslesen(File datei) {
    StringBuilder dateiInhalt = new StringBuilder();
    try (FileReader fr = new FileReader(datei)) {
        while (fr.ready()) {
            dateiInhalt.append((char) fr.read());
        }
    } catch (IOException _) {
        System.out.println("Meldung: Datei konnte nicht ausgelesen werden!");
    }
    return dateiInhalt.toString();
}

public void menu(File datei, String text) {
    Scanner sc = new Scanner(System.in);
    Scanner scInner = new Scanner(System.in);

    boolean stop = false;
    do {
        System.out.println("\n\n\n\t**************************************************");
        System.out.println("\t                        Menü");
        System.out.println("\t**************************************************");
        System.out.println("\t<1> Text eingeben");
        System.out.println("\t<2> Text auf dem Bildschirm ausgeben");
        System.out.println(STR."\t<3> Text in die Datei \"\{datei.getName()}\" schreiben");
        System.out.println("\t    (Vorhandene Texte werden dadurch gelöscht");
        System.out.println(STR."\t<4> Text in die Datei \"\{datei.getName()}\" am Ende einfügen");
        System.out.println(STR."\t<5> Inhalt der Datei \"\{datei.getName()}\" auslesen");
        System.out.print("\tEingabe Auswahl: ");
        int auswahl = sc.nextInt();

        switch (auswahl) {
            case 1:
                System.out.print("\tBitte geben Sie einen Text ein: ");
                text = scInner.nextLine();
                System.out.println("\n");
                break;
            case 2:
                System.out.println(STR."\tBildschirmausgabe \{text}");
                System.out.println("\n");
                break;
            case 3:
                schreibeTextInDatei(datei, text, false);
                System.out.println(STR."\tMeldung: Der Text \"\{text}\" wurde in die Datei \"\{datei.getName()}\" geschrieben!");
                System.out.println("\n");
                break;
            case 4:
                schreibeTextInDatei(datei, text, true);
                System.out.println(STR."\tMeldung: Der Text \"\{text}\" wurde am Ende in die Datei \"\{datei.getName()}\" eingefügt!");
                System.out.println("\n");
                break;
            case 5:
                System.out.println(STR."\tText \"\{dateiAuslesen(datei)}\" in der Datei \"\{datei.getName()}\": ");
                System.out.println("\n");
                break;
            default:
                stop = true;
                break;
        }
    } while (!stop);
}

void main() {
    File datei = new File("test.txt");
    String text = "";

    menu(datei, text);
}
