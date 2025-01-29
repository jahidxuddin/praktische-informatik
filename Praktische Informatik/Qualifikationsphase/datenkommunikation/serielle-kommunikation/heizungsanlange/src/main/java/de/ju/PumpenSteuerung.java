package de.ju;

import serialio.Serial;

import java.io.IOException;

public class PumpenSteuerung {
    private Serial serial;
    private final byte[] statusPumpen = new byte[8];

    public PumpenSteuerung() {
        System.out.println("Systeminfo: Die Pumpensteuerung ist gestartet!");
        try {
            this.serial = new Serial("COM1", 9600, 8, 1, 0);
        } catch (IOException e) {
            System.out.println("Systemfehler: Schnittstelle konnte nicht geöffnet werden!");
        }
    }

    public boolean start() {
        try {
            return this.serial.open();
        } catch (IOException e) {
            System.out.println("Systemfehler: Schnittstelle konnte nicht geöffnet werden!");
            return false;
        }
    }

    public void abfragePumpenStatus() {
        try {
            // Warte auf Startbyte
            while (this.serial.read() != 0x4F);
            System.out.println("Systeminfo: Das Startbyte wurde empfangen!");

            // Lese & speichere Pumpenstatus-Bytes
            for (int i = 0; i < this.statusPumpen.length; i++) {
                this.statusPumpen[i] = (byte) this.serial.read();
            }
            System.out.println("\nSysteminfo: Alle 8 Pumpenstatus-Bytes wurden empfangen!");

            System.out.println("Ausgabe der 8 empfangenen Pumpenstatus-Bytes:");
            String bin = "Binär:\t\t";
            String hex = "Hexadezimal:\t\t";
            for (byte b : this.statusPumpen) {
                bin += String.format("%8s", Integer.toBinaryString(b & 0xFF)).replace(' ', '0') + " ";
                hex += String.format("%2s", Integer.toHexString(b & 0xFF)).replace(' ', '0') + " ";
            }
            System.out.println(bin);
            System.out.println(hex);
        } catch (IOException e) {
            System.out.println("Systemfehler: Der aktuelle Pumpenstatus konnte nicht erfasst werden!");
        }
    }

    public void steuerePumpen() {
        byte steuerungSchalteEinAus = 0x00;

        int anzahlAktivierterPumpen = 0;
        for (int i = 0; i < this.statusPumpen.length; i++) {
            int betriebszeit = this.statusPumpen[i] >> 4;
            if (betriebszeit < 4 && anzahlAktivierterPumpen < 4) {
                steuerungSchalteEinAus |= (byte) (1 << i);
                anzahlAktivierterPumpen++;
            }
        }

        for (int i = 0; i < this.statusPumpen.length; i++) {
            if (anzahlAktivierterPumpen == 4) {
                break;
            }

            int betriebszeit = this.statusPumpen[i] >> 4;
            if (betriebszeit == 0 && (steuerungSchalteEinAus & (1 << i)) == 0) {
                steuerungSchalteEinAus |= (byte) (1 << i);
                anzahlAktivierterPumpen++;
            }
        }

        String bin = String.format("%8s", Integer.toBinaryString(steuerungSchalteEinAus & 0xFF)).replace(' ', '0') + " ";
        String hex = String.format("%2s", Integer.toHexString(steuerungSchalteEinAus & 0xFF)).replace(' ', '0') + " ";
        System.out.println("\nAktueller Status der Pumpen: " + bin + " (binär)\t" + hex + " (hexadezimal)");

        try {
            this.serial.write(steuerungSchalteEinAus);
            System.out.println("Das Steuerungsbyte wurde an die Pumpenanlage gesendet!");
        } catch (IOException e) {
            System.out.println("Systemfehler: Das Steuerungsbyte konnte nicht versendet werden!");
        }
    }

    public static void main(String[] args) {
        PumpenSteuerung pumpenSteuerung = new PumpenSteuerung();

        if (!pumpenSteuerung.start()) {
            return;
        }

        while (true) {
            pumpenSteuerung.abfragePumpenStatus();
            pumpenSteuerung.steuerePumpen();
        }
    }
}
