import java.io.IOException;
import java.io.RandomAccessFile;

void listeNamen() {
    System.out.println("Vor- und Nachnamen der Datei adressen.txt auflisten:");

    try(RandomAccessFile raf = new RandomAccessFile("adressen.txt", "rw")) {
        String rohDaten;
        while ((rohDaten = raf.readLine()) != null) {
            String[] adressen = rohDaten.split(",");

            System.out.println(adressen[1] + " " + adressen[0] + ",");
        }
    } catch(IOException e) {
        System.out.println(e.getMessage());
    }
}

void main() {
    listeNamen();
}
