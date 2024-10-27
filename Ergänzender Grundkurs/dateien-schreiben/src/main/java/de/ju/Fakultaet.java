import java.io.IOException;
import java.io.RandomAccessFile;
import java.math.BigInteger;

void datenSpeichern() {
    try (RandomAccessFile raf = new RandomAccessFile("fakultätenBigInt.txt", "rw")) {
        for (int i = 1; i < 22; i++) {
            BigInteger fakultaet = BigInteger.ONE;
            for (int j = 2; j < i + 1; j++) {
                fakultaet = fakultaet.multiply(BigInteger.valueOf(j));
            }
            raf.writeBytes("\t\tFakultaet von " + i + "! = " + fakultaet + "\n");
        }
    } catch (IOException e) {
        throw new RuntimeException(e);
    }
}

void datenLesen() {
    try (RandomAccessFile raf = new RandomAccessFile("fakultätenBigInt.txt", "rw")) {
        raf.seek(0);
        String text;
        while ((text = raf.readLine()) != null) {
            System.out.println(text);
        }
    } catch (IOException e) {
        throw new RuntimeException(e);
    }
}

void main() {
    datenSpeichern();
    datenLesen();
}
