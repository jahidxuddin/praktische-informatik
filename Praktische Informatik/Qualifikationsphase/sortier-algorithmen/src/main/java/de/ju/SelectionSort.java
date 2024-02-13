package de.ju;

import java.util.Arrays;

public class SelectionSort {
    private int anzahlSchleifendurchlaeufe;
    private int anzahlTauschvorgaenge;

    public int getAnzahlSchleifenDurchlaeufe() {
        return anzahlSchleifendurchlaeufe;
    }

    public int getAnzahlTauschvorgaenge() {
        return anzahlTauschvorgaenge;
    }

    public int[] selectionSort(int[] array) {
        for (int i = 0; i < array.length; i++) {
            for (int j = i + 1; j < array.length; j++) {
                if (array[i] > array[j]) {
                    int temp = array[i];
                    array[i] = array[j];
                    array[j] = temp;
                    anzahlTauschvorgaenge++;
                }
                anzahlSchleifendurchlaeufe++;
            }
            anzahlSchleifendurchlaeufe++;
        }

        return array;
    }

    public static void main(String[] args) {
        SelectionSort selectionSort = new SelectionSort();

        int[] sortedArray = selectionSort.selectionSort(new int[]{ 5, 10, 2, 13, 100, 99, 50, 23, 48 });

        Arrays.stream(sortedArray).forEach(System.out::println);

        System.out.println();
        System.out.println();

        System.out.println("Tauschvorgänge: " + selectionSort.getAnzahlTauschvorgaenge());
        System.out.println("Schleifendurchläufe: " + selectionSort.getAnzahlSchleifenDurchlaeufe());
    }
}
