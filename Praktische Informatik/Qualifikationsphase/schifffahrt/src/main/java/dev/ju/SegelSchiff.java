package dev.ju;

public class SegelSchiff extends Schiff {
    private double segelflaeche;

    public SegelSchiff(String name, double tonnage, double segelflaeche) {
        super(name, tonnage);
        this.segelflaeche = segelflaeche;
    }
}
