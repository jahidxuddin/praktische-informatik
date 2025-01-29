import java.util.Date;

public class Buchung {
    private Date buTag;
    private String text;
    private double betrag;

    public Buchung(String text, double betrag) {
        this.text = text;
        this.betrag = betrag;
        this.buTag = new Date();
    }

    public Date getBuTag() {
        return buTag;
    }

    public void setBuTag(Date buTag) {
        this.buTag = buTag;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public double getBetrag() {
        return betrag;
    }

    public void setBetrag(double betrag) {
        this.betrag = betrag;
    }

    @Override
    public String toString() {
        return this.text + ", Betrag: " + this.betrag;
    }
}
