package de.ju;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BufferedStream {
    public BufferedStream() {
        ausgabe();
    }

    public String leseZeile() {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        try {
            return bufferedReader.readLine();
        } catch (IOException e) {
            return "";
        }
    }

    public String leseMehrereZeilen() {
        try(BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in))) {
            String zeilen = "";

            String eingabe;
            while ((eingabe = bufferedReader.readLine()) != null && !eingabe.equals("over")) {
                zeilen += eingabe + "\n";
            }

            return zeilen;
        } catch (IOException e) {
            return "";
        }
    }

    public void ausgabe() {
        String zeile = leseZeile();
        System.out.println(zeile);

        String mehrereZeilen = leseMehrereZeilen();
        System.out.println(mehrereZeilen);
    }

    public static void main(String[] args) {
        new BufferedStream();
    }
}
