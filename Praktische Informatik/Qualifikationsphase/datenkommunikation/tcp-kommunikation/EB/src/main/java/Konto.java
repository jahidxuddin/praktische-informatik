import java.util.ArrayList;
import java.util.List;

public class Konto {
    private String iban;
    private int pin;
    private double kontostand;
    private ArrayList<Buchung> buchungen;

    public Konto(String iban) {
        this.iban = iban;
        // this.pin = EBVerwaltung.generierePin();
        this.kontostand = 0;
        this.buchungen = new ArrayList<>();
    }

    private void hinzufuegenBuchung(Buchung b) {
        this.buchungen.add(b);
    }

    public List<Buchung> sucheBuchungen(String begriff) {
        List<Buchung> buchungenMitBegriff = new ArrayList<>();
        for (Buchung b : this.buchungen) {
            if (b.getText().contains(begriff)) {
                buchungenMitBegriff.add(b);
            }
        }
        return buchungenMitBegriff;
    }

    public boolean ueberweisen(Konto empfaenger, String text, double betrag) {
        return true;
    }

    public int getPin() {
        return pin;
    }

    public void setPin(int pin) {
        this.pin = pin;
    }

    public String getIban() {
        return iban;
    }

    public void setIban(String iban) {
        this.iban = iban;
    }

    public double getKontostand() {
        return kontostand;
    }

    public void setKontostand(double kontostand) {
        this.kontostand = kontostand;
    }

    public ArrayList<Buchung> getBuchungen() {
        return buchungen;
    }

    public void setBuchungen(ArrayList<Buchung> buchungen) {
        this.buchungen = buchungen;
    }
}
