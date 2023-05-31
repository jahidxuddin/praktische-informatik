public class ArrayÜberprüfung {
    public static boolean überprüfeDiagonaleRechtsLinks(char[][] arr) {
        boolean gleicheDiagonaleRechtsLinks = false;
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = arr[i].length - 1 - i; j > 0; j--) {
                char aktuellesFeld = arr[i][j];
                char nächstesFeld = arr[i + 1][j - 1];
                if (aktuellesFeld == nächstesFeld) {
                    gleicheDiagonaleRechtsLinks = true;
                } else {
                    gleicheDiagonaleRechtsLinks = false;
                    break;
                }
            }
            if (!gleicheDiagonaleRechtsLinks) {
                break;
            }
        }
        return gleicheDiagonaleRechtsLinks;
    }

    public static boolean überprüfeDiagonaleLinksRechts(char[][] arr) {
        boolean gleicheDiagonaleLinksRechts = false;
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = i; j < arr[i].length - 1; j++) {
                char aktuellesFeld = arr[i][j];
                char nächstesFeld = arr[i + 1][j + 1];
                if (aktuellesFeld == nächstesFeld) {
                    gleicheDiagonaleLinksRechts = true;
                } else {
                    gleicheDiagonaleLinksRechts = false;
                    break;
                }
            }
            if (!gleicheDiagonaleLinksRechts) {
                break;
            }
        }
        return gleicheDiagonaleLinksRechts;
    }

    public static boolean überprüfeSpalten(char[][] arr) {
        boolean gleicheSpalte = false;
        for (int i = 0; i < arr[0].length; i++) {
            for (int j = 0; j < arr.length - 1; j++) {
                char aktuellesFeld = arr[j][i];
                char nächstesFeld = arr[j + 1][i];
                if (aktuellesFeld == nächstesFeld) {
                    gleicheSpalte = true;
                } else {
                    gleicheSpalte = false;
                    break;
                }
            }
            if (gleicheSpalte) {
                break;
            }
        }
        return gleicheSpalte;
    }

    public static boolean überprüfeZeilen(char[][] arr) {
        boolean gleicheZeile = false;
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length - 1; j++) {
                char aktuellesFeld = arr[i][j];
                char nächstesFeld = arr[i][j + 1];
                if (aktuellesFeld == nächstesFeld) {
                    gleicheZeile = true;
                } else {
                    gleicheZeile = false;
                    break;
                }
            }
            if (gleicheZeile) {
                break;
            }
        }
        return gleicheZeile;
    }

    public static void main(String[] args) {
        char[][] arr = {
                { 'X', '*', 'X' },
                { '*', '*', '*' },
                { 'X', '*', 'X' }
        };
        System.out.println(überprüfeDiagonaleRechtsLinks(arr));
    }
}
