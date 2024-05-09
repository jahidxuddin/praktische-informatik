import java.io.IOException;
import java.io.RandomAccessFile;

void main() {
    try (RandomAccessFile raf = new RandomAccessFile("test.txt", "w")) {
        raf.writeUTF("AAAAA\nBBBBB\nCCCCC");
    } catch (IOException e) {
        System.out.println("Datei konnte nicht gelesen/beschrieben werden.");
    }
}
