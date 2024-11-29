package de.ju;

public class ThreadTester {
    public ThreadTester() {
        int sleeptime = 100;
        int anzahlDurchlaeufe = 5;

        System.out.println("\n\n\tStarte Ausgabe:\n");
        Zahlenausgabe z1 = new Zahlenausgabe(1, anzahlDurchlaeufe, sleeptime, "\t");
        Zahlenausgabe z2 = new Zahlenausgabe(2, anzahlDurchlaeufe, sleeptime, "\t\t\t");
        Zahlenausgabe z3 = new Zahlenausgabe(3, anzahlDurchlaeufe, sleeptime, "\t\t\t\t\t");
        Zahlenausgabe z4 = new Zahlenausgabe(4, anzahlDurchlaeufe, sleeptime, "\t\t\t\t\t\t\t");

        Thread t1 = new Thread(z1);
        Thread t2 = new Thread(z2);
        Thread t3 = new Thread(z3);
        Thread t4 = new Thread(z4);
        t1.start();
        t2.start();
        t3.start();
        t4.start();

        System.out.println("\n\tProgramm beendet!");
    }

    public static void main(String[] args) {
        new ThreadTester();
    }
}
