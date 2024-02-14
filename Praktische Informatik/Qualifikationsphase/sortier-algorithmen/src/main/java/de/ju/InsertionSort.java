package de.ju;

public class InsertionSort {
    private static int anzahlSchleifendurchlaeufe;
    private static int anzahlTauschvorgaenge;

    public static int getAnzahlSchleifenDurchlaeufe() {
        return anzahlSchleifendurchlaeufe;
    }

    public static int getAnzahlTauschvorgaenge() {
        return anzahlTauschvorgaenge;
    }

    public static int[] insertionSort(int[] array) {
        for (int i = 1; i < array.length; i++) {
            int temp = array[i];
            int j = i - 1;
            while(j > 0 && array[j] > temp){
                array[j + 1] = array[j];
                j--;
            }
            array[j + 1] = temp;
        }

        return array;
    }
}
