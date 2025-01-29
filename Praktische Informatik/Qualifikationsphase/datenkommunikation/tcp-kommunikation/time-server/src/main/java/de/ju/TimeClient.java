package de.ju;

import socketio.Socket;

import java.io.IOException;

public class TimeClient {
    private final String HOST = "localhost";
    private final int PORT = 1234;
    private final Socket client;

    public TimeClient() {
        try {
            this.client = new Socket(this.HOST, this.PORT);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void verbinden() {
        this.client.connect();
        System.out.println("\nVerbindung ist aufgebaut!\n");
    }

    public void kommunizieren() {
        try {
            for (int i = 0; i < 10; i++) {
                System.out.println(client.readLine());
            }
            beenden();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void beenden() {
        try {
            this.client.write("Ende\n");
            System.out.println("\nClient wird beendet!");
            this.client.close();
            System.out.println("\nSocket ist geschlossen!");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        TimeClient tc = new TimeClient();
        System.out.println("Time-Client ist gestartet und versucht Verbindung zum Server aufzubauen!");
        tc.verbinden();
        tc.kommunizieren();
    }
}
