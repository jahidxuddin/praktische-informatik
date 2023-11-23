package dev.ju;

public class Segelschiff extends Schiff {
    private double segelflaeche;

    public Segelschiff(String name, double tonnage, double segelflaeche) {
        super(name, tonnage);
        this.segelflaeche = segelflaeche;
    }

    public double getSegelflaeche() {
        return segelflaeche;
    }
}
