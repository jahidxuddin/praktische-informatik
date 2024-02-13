package dev.ju;

public class Rekursiv {
    public static long berechneFakultaet(int zahl) {
        if (zahl <= 1) {
            return 1;
        }

        return zahl * berechneFakultaet(zahl - 1);
    }

    public static int ggt(int zahl1, int zahl2) {
        return zahl2 != 0 ? ggt(zahl2, zahl1 % zahl2) : zahl1;
    }

    public static void ausgabeZahlen(int n) {
        if (n != 0) {
            ausgabeZahlen(n - 1);
            System.out.println(n + ",");
        }
    }

    public static void main(String[] args) {
        System.out.println(ggt(99876, 24));
        ausgabeZahlen(10);
    }
}
