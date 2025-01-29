import socketio.ServerSocket;
import socketio.Socket;

import java.io.IOException;

public class EBServer {
    private ServerSocket server;
    private int port;
    private EBVerwaltung eb;

    public EBServer(int port, EBVerwaltung eb) throws IOException {
        this.port = port;
        this.eb = eb;
        this.server = new ServerSocket(port);
    }

    public void starteServer() throws IOException {
        Socket client = this.server.accept();
        client.write("+OK E-Banking\n");

        String nachricht = client.readLine();
        String[] anmeldeDaten = nachricht.split(";");

        Konto konto = eb.anmelden(anmeldeDaten[1], Integer.parseInt(anmeldeDaten[2]));
        if (konto == null) {
            client.write("-ERR: Login fehlgeschlagen\n");
            return;
        }

        client.write("+OK Willkommen\n");

        String befehl = "";
        while (!befehl.equals("quit")) {
            befehl = client.readLine();

            String[] ueberweisungsDaten = befehl.split(";");
            String iban = ueberweisungsDaten[1];
            String buchungstext = ueberweisungsDaten[2];
            double betrag = Double.parseDouble(ueberweisungsDaten[3]);

            if (konto.getKontostand() - betrag < 0) {
                client.write("-ERR Überweisung nicht erfolgt\n");
                befehl = "quit";
            }

            Konto empfaenger = eb.sucheKonto(iban);
            if (empfaenger == null) {
                client.write("-ERR Ungültige IBAN\n");
                befehl = "quit";
            }

            if (!konto.ueberweisen(empfaenger, buchungstext, betrag)) {
                client.write("-ERR Überweisung nicht erfolgt\n");
                befehl = "quit";
            }

            client.write("+OK Überweisung erfolgt\n");
            client.write("Aktueller Kontostand: " + konto.getKontostand() + " EURO\n");
        }
    }
}
