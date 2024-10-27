package de.ju;

import serialio.Serial;

import java.io.IOException;
import java.util.Scanner;

public class Sender {
    private final Serial conn;
    private final int ACK;
    private final int ETX;

    public Sender() {
        this.ACK = 0x06;
        this.ETX = 0x03;

        try {
            this.conn = new Serial("COM1", 9600, 8, 1, 0);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void oeffnePorts() {
        this.conn.setDTR(true);
        this.conn.setRTS(true);
    }

    private void warteAufDSR() {
        System.out.println("\nSysteminfo: Warte auf Betriebsbereitschaft des Empfängers (DSR)!");
        while (!this.conn.isDSR()) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        System.out.println("Systeminfo: Empfänger ist betriebsbereit!");
    }

    private void warteAufCTS() {
        System.out.println("\nSysteminfo: Warte auf Betriebsbereitschaft des Empfängers (CTS)!");
        while (!this.conn.isCTS()) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        System.out.println("Systeminfo: Empfänger ist betriebsbereit!");
    }

    private void sendeACK() throws IOException {
        System.out.println("\nSysteminfo: Sende ACK!");
        this.conn.write(this.ACK);
        System.out.println("Systeminfo: ACK wurde gesendet!");
    }

    public void senden() {
        try {
            if (!this.conn.open()) {
                return;
            }

            this.oeffnePorts();
            this.warteAufDSR();
            this.warteAufCTS();
            this.sendeACK();

            Scanner scanner = new Scanner(System.in);
            System.out.println("\nNachricht für den Empfänger eingeben\nund mit dem Wort over absenden:");
            String nachricht;
            while (!(nachricht = scanner.nextLine() + "\n").equals("over\n")) {
                this.conn.write(nachricht);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void verbindungBeenden() {
        try {
            System.out.println("\nSysteminfo: Sende ETX!");
            this.conn.write(this.ETX);
            System.out.println("Systeminfo: ETX wurde gesendet!");

            this.conn.close();
            System.out.println("\nSysteminfo: Übertragung wurde beendet!");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        System.out.println("Systeminfo: Ich bin der Sender!");
        Sender sender = new Sender();
        System.out.println("\nSysteminfo: Port wurde geöffnet!");

        sender.senden();
        sender.verbindungBeenden();
    }
}
