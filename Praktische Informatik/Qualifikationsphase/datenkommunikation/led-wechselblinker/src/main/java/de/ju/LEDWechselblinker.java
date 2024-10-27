package de.ju;

import serialio.Serial;

import java.io.IOException;
import java.util.Scanner;

public class LEDWechselblinker {
    private final Serial serial;
    private boolean istAmLaufen;

    public LEDWechselblinker(Serial serial) {
        this.serial = serial;
        this.istAmLaufen = false;
    }

    public boolean istAmLaufen() {
        return this.istAmLaufen;
    }

    public void setIstAmLaufen(boolean istAmLaufen) {
        this.istAmLaufen = istAmLaufen;
    }

    public void blinken(int anzahl) {
        for (int i = 0; i < anzahl; i++) {
            try {
                this.serial.setRTS(true);
                Thread.sleep(100);
                this.serial.setRTS(false);
                Thread.sleep(100);
                this.serial.setDTR(true);
                Thread.sleep(100);
                this.serial.setDTR(false);
                Thread.sleep(100);
            } catch (InterruptedException ignored) {
            }
        }
    }

    public void beenden() {
        try {
            this.serial.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        SerialDetails details = new SerialDetails("COM5", 9600, 8, 1, 0);
        SerialWrapper.createSerialConnection(details, new SerialConnection() {
            @Override
            public void onSuccess(Serial serial) {
                Scanner scanner = new Scanner(System.in);
                LEDWechselblinker ledWechselblinker = new LEDWechselblinker(serial);

                do {
                    System.out.print("Möchten Sie es blinken lassen? (j/n): ");
                    ledWechselblinker.setIstAmLaufen(scanner.nextLine().equalsIgnoreCase("j"));
                    ledWechselblinker.blinken(10);
                } while (ledWechselblinker.istAmLaufen());

                ledWechselblinker.beenden();
                scanner.close();
            }

            @Override
            public void onFailure(String error) {
                System.out.println(error);
                System.exit(0);
            }
        });
    }
}
