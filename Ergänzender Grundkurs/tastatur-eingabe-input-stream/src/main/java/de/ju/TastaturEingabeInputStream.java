package de.ju;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class TastaturEingabeInputStream {
    public TastaturEingabeInputStream() {
        ausgabe();
    }

    public char leseZeichen() {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

            return (char) reader.read();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public String leseWort() {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

            return reader.readLine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void ausgabe() {
        System.out.println(leseZeichen());
        System.out.println(leseWort());
    }

    public static void main(String[] args) {
        new TastaturEingabeInputStream();
    }
}
