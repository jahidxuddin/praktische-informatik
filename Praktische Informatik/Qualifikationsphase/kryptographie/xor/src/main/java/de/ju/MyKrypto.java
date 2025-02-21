package de.ju;

import java.util.Base64;

public class MyKrypto {
    public String encryptXOR(String text, String key) {
        byte[] byteText = Base64.getDecoder().decode(text);
        byte[] byteKey = key.getBytes();

        byte[] result = new byte[byteText.length];
        for (int i = 0; i < result.length; i++) {
            result[i] = (byte) (byteText[i] ^ byteKey[i % byteKey.length]);
        }

        return Base64.getEncoder().encodeToString(result);
    }

    public static void main(String[] args) {
        MyKrypto myKrypto = new MyKrypto();
        System.out.println(myKrypto.encryptXOR(myKrypto.encryptXOR("TEXT", "PENT"), "PENT"));
    }
}
