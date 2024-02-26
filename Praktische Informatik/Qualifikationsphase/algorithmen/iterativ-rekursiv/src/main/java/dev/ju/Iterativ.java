package dev.ju;

public class Iterativ {
    public static long berechneFakultaet(int zahl) {
        long fakultaet = 1;

        if (zahl < 0) {
            for (int i = -1; i >= zahl; i--) {
                fakultaet *= i;
            }
            return fakultaet;
        }

        for (int i = 2; i <= zahl; i++) {
            fakultaet *= i;
        }

        return fakultaet;
    }

    public static int ggt(int zahl1, int zahl2) {
        while (zahl2 != 0) {
            int restwert = zahl1 % zahl2;
            zahl1 = zahl2;
            zahl2 = restwert;
        }

        return zahl1;
    }

    public static void main(String[] args) {
        System.out.println(ggt(9, 24));
    }
}
