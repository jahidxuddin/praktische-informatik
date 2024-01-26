package dev.ju;

public class Rekursiv {
    public static long berechneFakultaet(int zahl) {
        if (zahl <= 1) {
            return 1;
        }

        return zahl * berechneFakultaet(zahl - 1);
    }

    public static int ggt(int zahl1, int zahl2) {
        if (zahl2 != 0) {
            return ggt(zahl2, zahl1 % zahl2);
        }

        return zahl1;
    }

    public static void main(String[] args) {
        System.out.println(ggt(9, 24));
    }
}
