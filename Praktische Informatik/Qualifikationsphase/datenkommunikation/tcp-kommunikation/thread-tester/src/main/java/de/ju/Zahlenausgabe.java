package de.ju;

public class Zahlenausgabe implements Runnable {
    private final int nummer;
    private final int anzahlDurchlauefe;
    private final int sleepTime;
    private final String tabulator;

    public Zahlenausgabe(int nummer, int anzahlDurchlauefe, int sleepTime, String tabulator) {
        this.nummer = nummer;
        this.anzahlDurchlauefe = anzahlDurchlauefe;
        this.sleepTime = sleepTime;
        this.tabulator = tabulator;
    }

    @Override
    public void run() {
        System.out.println(tabulator + "Thread " + nummer + " startet ...");
        for (int i = 0; i < anzahlDurchlauefe; i++) {
            System.out.println(tabulator + "Z" + nummer + ": " + i);
            try {
                Thread.sleep(sleepTime);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
