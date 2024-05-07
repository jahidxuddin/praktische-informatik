import java.io.File;
import java.io.IOException;
import java.util.Scanner;

void ordnerAnlegen(String ordnerPfad) {
    File ordner = new File(ordnerPfad);
    if (ordner.exists()) {
        System.out.println("Systeminfo: Der Ordner existiert schon!");
        return;
    }

    boolean istErstellt = ordner.mkdir();
    if (istErstellt) {
        System.out.println("Systeminfo: Der Ordner wurde angelegt!");
    } else {
        System.out.println("Systeminfo: Der Ordner wurde nicht angelegt!");
    }
}

void dateiAnlegen(String dateiName, String ordner) {
    File datei = new File(ordner + File.separator + dateiName);
    if (datei.exists()) {
        System.out.printf("Systeminfo: Die Datei %s existiert schon!", datei.getName());
        return;
    }

    try {
        boolean istErstellt = datei.createNewFile();

        if (istErstellt) {
            System.out.printf("Systeminfo: Die Datei %s wurde angelegt!", datei.getName());
        } else {
            System.out.printf("Systeminfo: Die Datei %s wurde nicht angelegt!", datei.getName());
        }
    } catch (IOException e) {
        System.out.println("Systeminfo: Die Datei wurde nicht angelegt!");
    }
}

void listeOrdner(String pfad) {
    File ordner = new File(pfad);
    String[] dateien = ordner.list();
    if (dateien != null) {
        System.out.println("\nAlle Dateien im Ordner: " + pfad);
        for (String name : dateien) {
            System.out.println("\n" + name);
        }
    }
}

void main() {
    Scanner scanner = new Scanner(System.in);

    System.out.print("Ordnername eingeben: ");
    String ordnerPfad = scanner.nextLine();
    ordnerAnlegen(ordnerPfad);

    System.out.print("Dateiname eingeben: ");
    String dateiName = scanner.nextLine();
    dateiAnlegen(dateiName, ordnerPfad);

    listeOrdner(ordnerPfad);
}
