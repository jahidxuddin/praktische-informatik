package de.ju;

import serialio.Serial;

public class PumpenAnlage {
    private Serial myConnection;
    private final byte[] statusPumpen = new byte[8];
    private boolean gestartet;

    public PumpenAnlage(){
        System.out.println("\n\n\tSysteminfo: Die Pumpenanlage ist gestartet!");
        try {
            this.myConnection = new Serial("COM2", 9600, 8, 1, 0);
        } catch(Exception e) {
            System.out.println("\tSystemfehler: Schnittstelle konnte nicht geöffnet werden!\n");
        }
    }

    public boolean start() {
        try {
            this.gestartet = false;
            if (this.myConnection.open()) {
                System.out.println("\n\tSysteminfo: Die Schnittstelle ist geöffnet!");
                this.gestartet = true;
            }
        } catch(Exception e) {
            System.out.println("\tSystemfehler: Schnittstelle konnte nicht geöffnet werden!\n");
        }
        return this.gestartet;
    }

    public void sendeSteuerinformationen(){
        try {
            byte start = 0x4F;
            this.myConnection.write(start);                              // StartByte senden
            for (byte b : this.statusPumpen) {
                this.myConnection.write(b);               // Bytes statusPumpen senden
            }
        } catch (Exception e) {
            System.out.println("\tSystemfehler: Pumpenstatus konnte nicht gesendet werden!");
        }
    }

    public void getSteuerinformationen(){

        try {

            while(this.myConnection.dataAvailable() <= 0);                   // auf Statusinformationen warten

            byte steuerungSchalteEinAus = (byte) this.myConnection.read();           // Statusinformationen auslesen
            System.out.println("\tSysteminfo: Byte steuerungSchalteEinAus wurde empfangen!");

            for (int i=0; i<this.statusPumpen.length; i++) {

                if ((steuerungSchalteEinAus & (int) Math.pow(2,i)) == Math.pow(2,i)) {  // Pumpe einschalten
                    this.einschalten(i);
                }
                else {
                    this.ausschalten(i);
                }
            }
        }
        catch (Exception e) {
            System.out.println("\tSystemfehler: Steuerinformationen konnten nicht gelesen werden!");
        }

    }

    public void einschalten(int pumpennr){                                           // Pumpe einschalten

        // System.out.println((pumpennr) + ". Pumpe einschalten");

        byte zaehler;
        zaehler = (byte) (this.statusPumpen[pumpennr] >> 4);
        if (zaehler == 0) {                                                         // inaktive Pumpe einschalten
            zaehler = 1;
            zaehler = (byte) (zaehler << 4);
            this.statusPumpen[pumpennr] &= 0x0F;
            this.statusPumpen[pumpennr] |= zaehler;
        }
    }

    public void ausschalten(int pumpennr){                                        // aktive Pumpe ausschalten
        this.statusPumpen[pumpennr] &= 0x0F;

    }

    public void erhoeheZaehlerstaende() {                                         // Zaehlerstände erhöhen
        for (int i = 0 ; i < this.statusPumpen.length ; i++ ) {
            if ((this.statusPumpen[i] & 0xF0) > 0x0F) {                           // Abfrage, ob Pumpe aktiv ist
                // System.out.println((i) + ". Pumpe -> Zaehlerstand erhöhen");
                int zaehler = (this.statusPumpen[i] >> 4) + 1;                    // alten Zaehlerstand sichern
                zaehler = zaehler << 4;                                           // und erhöhen
                this.statusPumpen[i]  &= 0x0F;                                    // alten Zaehlerstand löschen
                this.statusPumpen[i]  |= (byte) zaehler;                                 // und neuen übernehmen
            }
        }
    }

    public void ausgabePumpenstatus(){                                             // Pumpen-Informationen ausgeben
        for (int i=0;i < this.statusPumpen.length; i++ ) {
            System.out.print("\tBetriebsdauer der " + (i+1) + ". Pumpe: " + (this.statusPumpen[i]>>4) + " ");
            if (this.statusPumpen[i]>>4 > 0) {
                System.out.println("\tläuft!");
            }
            else {
                System.out.println("\tsteht!");
            }
        }
        System.out.println();
    }

    public static void main(String[] args) {
        PumpenAnlage pa = new PumpenAnlage();

        if (!pa.start()) {                                                            // Kommunikation öffnen
            System.out.println("\tSysteminfo: Das Programmm wird abgebrochen");
            return;
        }

        int time = 0;
        pa.ausgabePumpenstatus();                                                // Ausgangszustand anzeigen
        while(true) {
            pa.sendeSteuerinformationen();                                       // Bytes senden
            pa.getSteuerinformationen();                                        // Steuerbyte auswerten

            if (time > 1) {                                                    // Laufzeittimer erhöhen
                pa.erhoeheZaehlerstaende();
                time = 0;
            }
            pa.ausgabePumpenstatus();                                                          // neuen Zustand anzeigen

            try {
                Thread.sleep(5000);
            } catch (Exception e) {
                System.out.println("\tSystemfehler: " + e.getMessage());
            }
            time++;
        }
    }
}
