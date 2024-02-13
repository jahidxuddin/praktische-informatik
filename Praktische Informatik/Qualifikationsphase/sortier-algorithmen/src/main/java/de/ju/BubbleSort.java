package de.ju;

import java.util.Arrays;

/**
 *
 * BubbleSort: Sortieralgorithmus durch Vertauschen
 */
public class BubbleSort {
    private int anzahlSchleifendurchlaeufe;
    private int anzahlTauschvorgaenge;

    public int getAnzahlSchleifenDurchlaeufe() {
        return anzahlSchleifendurchlaeufe;
    }

    public int getAnzahlTauschvorgaenge() {
        return anzahlTauschvorgaenge;
    }

    public int[] bubbleSort(int[] array) {
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

    public static void main(String[] args) {
        BubbleSort bubbleSort = new BubbleSort();

        int[] sortedArray = bubbleSort.bubbleSort(new int[]{ 5, 10, 2, 13, 100, 99, 50, 23, 48 });

        Arrays.stream(sortedArray).forEach(System.out::println);

        System.out.println();
        System.out.println();

        System.out.println("Tauschvorgänge: " + bubbleSort.getAnzahlTauschvorgaenge());
        System.out.println("Schleifendurchläufe: " + bubbleSort.getAnzahlSchleifenDurchlaeufe());
    }
}
