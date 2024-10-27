package de.ju;

import serialio.Serial;

public class Empfaenger {
    private final Serial connection;
    private final int ACK = 0x06;
    private final int ETX = 0x07;

    public Empfaenger(Serial connection) {
        this.connection = connection;
    }

    public void empfangen() {
        while (connection.dataAvailable()) {
           
        }
    }

    public void verbindungBeenden() {

    }

    public static void main(String[] args) {
        SerialDetails details = new SerialDetails("COM5", 9600, 8, 1, 0);
        SerialWrapper.createSerialConnection(details, new SerialConnection() {
            @Override
            public void onSuccess(Serial serial) {
                Empfaenger empfaenger = new Empfaenger(null);
            }

            @Override
            public void onFailure(String error) {
                System.out.println(error);
                System.exit(0);
            }
        });
    }
}
