package de.ju;

import socketio.ServerSocket;
import socketio.Socket;

import java.io.IOException;
import java.util.Calendar;

public class TimeServer {
    private final int port = 1234;
    private final ServerSocket server;
    private final Socket client;

    public TimeServer() {
        try {
            this.server = new ServerSocket(this.port);
            System.out.println("Time-Server ist gestartet und wartet auf Client!");
            this.client = this.server.accept();
            System.out.println("\nClient wurde vom Server akzeptiert!");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void kommunizieren() {
        System.out.println("\n\nUhrzeiten werden an den Client gesendet!");
        try {
            while (!beenden()) {
                this.client.write( Calendar.getInstance().getTime() + "\n");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException ignored) {
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean beenden() {
        try {
            if (this.client.dataAvailable() > 0) {
                if (this.client.readLine().equals("Ende\n")) {
                    System.out.println("Server wird beendet!");
                    this.client.close();
                    System.out.println("Socket ist geschlossen!");
                    this.server.close();
                    System.out.println("Serversocket ist geschlossen!");
                    System.out.println("\n\n\n");
                    return true;
                }
            } else {
                return false;
            }
        } catch (IOException e) {
            return false;
        }

        return false;
    }

    public static void main(String[] args) {
        TimeServer ts1 = new TimeServer();
        ts1.kommunizieren();
        ts1.beenden();
    }
}
