package de.ju;

import java.util.Arrays;

public class QuickSort {
    private static int anzahlSchleifendurchlaeufe;
    private static int anzahlTauschvorgaenge;

    public static int getAnzahlSchleifenDurchlaeufe() {
        return anzahlSchleifendurchlaeufe;
    }

    public static int getAnzahlTauschvorgaenge() {
        return anzahlTauschvorgaenge;
    }

    public static int[] quickSort(int[] array, int linkePosition, int rechtePosition) {
        int i = linkePosition;
        int j = rechtePosition;
        int wertMitte = array[(linkePosition + rechtePosition) / 2];

        do {
            while (array[i] < wertMitte) {
                i++;
            }

            while (array[j] > wertMitte) {
                j--;
            }

            if (i <= j) {
                int temp = array[i];
                array[i] = array[j];
                array[j] = temp;
                i++;
                j--;
            }
        } while (i <= j);

        if (linkePosition < j) {
            return quickSort(array, linkePosition, j);
        }

        if (i < rechtePosition) {
            return quickSort(array, i, rechtePosition);
        }

        return array;
    }

    public static void main(String[] args) {
        int[] array = new int[] { 100, 32, 545, 666, 22, 0, 134, 566 };
        Arrays.stream(quickSort(array, 0, array.length - 1)).forEach(System.out::println);
    }
}
