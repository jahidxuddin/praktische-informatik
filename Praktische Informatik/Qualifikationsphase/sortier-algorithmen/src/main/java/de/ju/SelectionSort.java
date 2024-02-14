package de.ju;

public class SelectionSort {
    private static int anzahlSchleifendurchlaeufe;
    private static int anzahlTauschvorgaenge;

    public static int getAnzahlSchleifenDurchlaeufe() {
        return anzahlSchleifendurchlaeufe;
    }

    public static int getAnzahlTauschvorgaenge() {
        return anzahlTauschvorgaenge;
    }

    public static int[] selectionSort(int[] array) {
        for (int i = 0; i < array.length - 1; i++) {
            int minIndex = i;

            for (int j = i + 1; j < array.length; j++) {
                if (array[j] < array[minIndex]) {
                    minIndex = j;
                }
                anzahlSchleifendurchlaeufe++;
            }

            int temp = array[i];
            array[i] = array[minIndex];
            array[minIndex] = temp;
            anzahlTauschvorgaenge++;

            anzahlSchleifendurchlaeufe++;
        }

        return array;
    }
}
