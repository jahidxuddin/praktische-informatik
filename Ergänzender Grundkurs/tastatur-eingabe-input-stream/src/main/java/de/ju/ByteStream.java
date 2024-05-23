package de.ju;

import java.io.IOException;
import java.io.InputStream;

public class ByteStream {
    public ByteStream() {
        ausgabe();
    }

    public char leseZeichen() {
        try {
            InputStream inputStream = System.in;
            return (char) inputStream.read();
        } catch (IOException e) {
            return ' ';
        }
    }

    public String leseWort() {
        try(InputStream inputStream = System.in) {
            String wort = "";
            do {
                wort += (char) inputStream.read();
            } while(inputStream.available() > 0);
            return wort;
        } catch (IOException e) {
            return "";
        }
    }

    public void ausgabe() {
        // INFO: leseZeichen() und leseWort() können nicht gleichzeitig ausgeführt werden!
        // char zeichen = leseZeichen();
        // System.out.println(zeichen);

        String wort = leseWort();
        System.out.println(wort);
    }

    public static void main(String[] args) {
        new ByteStream();
    }
}
