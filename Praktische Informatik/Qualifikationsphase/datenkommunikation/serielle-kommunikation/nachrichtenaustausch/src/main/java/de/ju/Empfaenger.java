package de.ju;

import serialio.Serial;

import java.io.IOException;

public class Empfaenger {
    private final Serial conn;
    private final int ACK;
    private final int ETX;

    public Empfaenger() {
        this.ACK = 0x06;
        this.ETX = 0x03;

        try {
            this.conn = new Serial("COM2", 9600, 8, 1, 0);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void oeffnePorts() {
        System.out.println("\nSysteminfo: Sende Betriebsbereitschaft (DTR)!");
        this.conn.setDTR(true);
        System.out.println("Systeminfo: Betriebsbereitschaft wurde gesendet!");

        System.out.println("\nSysteminfo: Sende Empfangsbereitschaft (DTR)!");
        this.conn.setRTS(true);
        System.out.println("Systeminfo: Empfangsbereitschaft wurde gesendet!");
    }

    public void empfangen() {
        try {
            if (!this.conn.open()) {
                return;
            }

            this.oeffnePorts();
            while (this.conn.open()) {
                System.out.println("\nSysteminfo: Warte auf ACK!");
                while (true) {
                    if (this.conn.read() == this.ACK) {
                        break;
                    }
                }
                System.out.println("Systeminfo: ACK empfangen!");

                System.out.println("\nSysteminfo: Nachricht vom Sender live ausgeben:\n");
                String aktuelleNachricht;
                while (!(aktuelleNachricht = this.conn.readLine()).equals("over")) {
                    System.out.println("Nachricht: " + aktuelleNachricht);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void verbindungBeenden() {
        try {
            System.out.println("\nSysteminfo: Warte auf ETX!");
            this.conn.write(this.ETX);
            System.out.println("Systeminfo: ETX wurde empfangen!");

            this.conn.close();
            System.out.println("\nSysteminfo: Übertragung wurde beendet!");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        Empfaenger empfaenger = new Empfaenger();
        System.out.println("\nIch bin der Empfänger!");

        empfaenger.empfangen();
        empfaenger.verbindungBeenden();
    }
}
