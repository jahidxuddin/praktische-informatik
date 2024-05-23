package de.ju;

import java.io.IOException;
import java.io.InputStreamReader;

public class CharacterStream {
    public CharacterStream() {
        ausgabe();
    }

    public char leseZeichen() {
        InputStreamReader inputStreamReader = new InputStreamReader(System.in);
        try {
           return (char) inputStreamReader.read();
        } catch (IOException e) {
            return ' ';
        }
    }

    public String leseWort() {
        try {
            InputStreamReader inputStreamReader = new InputStreamReader(System.in);

            String wort = "";
            do {
                wort += (char) inputStreamReader.read();
            } while (inputStreamReader.ready());

            inputStreamReader.close();

            return wort;
        } catch (IOException e) {
            return "";
        }
    }

    public void ausgabe() {
        char zeichen = leseZeichen();
        System.out.println(zeichen);

        String wort = leseWort();
        System.out.println(wort);
    }

    public static void main(String[] args) {
        new CharacterStream();
    }
}
