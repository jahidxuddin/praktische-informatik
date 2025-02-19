package de.ju;

public class MyKrypto {
    public String verschluesseleXOR(String klarText, String key) {
        byte[] byteText = klarText.getBytes();
        byte[] byteKey = key.getBytes();
        byte[] result = xorByteArrays(byteText, byteKey);
        String text = "";
        for (byte b : result) {
            text += (char) b;
        }
        return text;
    }

    private byte[] xorByteArrays(byte[] a, byte[] b) {
        if (a.length != b.length) {
            throw new IllegalArgumentException("Byte arrays must have the same length");
        }

        byte[] result = new byte[a.length];
        for (int i = 0; i < result.length; i++) {
            result[i] = (byte) (a[i] ^ b[i]);
        }
        return result;
    }

    public static void main(String[] args) {
        MyKrypto myKrypto = new MyKrypto();
        System.out.println(myKrypto.verschluesseleXOR("TEXT", "PENT"));
    }
}
