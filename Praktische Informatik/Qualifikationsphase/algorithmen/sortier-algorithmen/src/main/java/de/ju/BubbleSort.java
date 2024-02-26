package de.ju;

import java.util.Arrays;

/**
 *
 * BubbleSort: Sortieralgorithmus durch Vertauschen
 */
public class BubbleSort {
    private static int anzahlSchleifendurchlaeufe;
    private static int anzahlTauschvorgaenge;

    public static int getAnzahlSchleifenDurchlaeufe() {
        return anzahlSchleifendurchlaeufe;
    }

    public static int getAnzahlTauschvorgaenge() {
        return anzahlTauschvorgaenge;
    }

    public static int[] bubbleSort(int[] array) {
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array.length - i - 1; j++) {
                if (array[j] > array[j + 1]) {
                    int temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                    anzahlTauschvorgaenge++;
                }
                anzahlSchleifendurchlaeufe++;
            }
            anzahlSchleifendurchlaeufe++;
        }

        return array;
    }
}
