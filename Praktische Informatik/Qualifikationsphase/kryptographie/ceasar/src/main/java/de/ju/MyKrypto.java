package de.ju;

public class MyKrypto {
    public String caesarVerschluesseln(String text, int key) {
        char[] charArr = text.toCharArray();
        for (int i = 0; i < charArr.length; i++) {
            charArr[i] += (char) key;
        }
        return new String(charArr);
    }

    public void caesarDecryptBrute(String geheim) {
        char[] originalArr = geheim.toCharArray();

        for (int i = 1; i <= 26; i++) {
            char[] charArr = originalArr.clone();

            for (int j = 0; j < charArr.length; j++) {
                if (Character.isLetter(charArr[j])) {
                    charArr[j] = shiftChar(charArr[j], -i);
                }
            }
            System.out.println("TEXT " + i + ": " + new String(charArr));
        }
    }

    private char shiftChar(char c, int shift) {
        if (Character.isUpperCase(c)) {
            return (char) ('A' + (c - 'A' + shift + 26) % 26);
        } else if (Character.isLowerCase(c)) {
            return (char) ('a' + (c - 'a' + shift + 26) % 26);
        }
        return c;
    }

    public static void main(String[] args) {
        MyKrypto myKrypto = new MyKrypto();

        String verschluesselterText = myKrypto.caesarVerschluesseln("ich mag java", 24);
        myKrypto.caesarDecryptBrute(verschluesselterText);
    }
}
