package dev.ju;

public class Kunde {
    private int kundenNr;
    private static int kundenstammNr = 1000;
    private String name;
    private Schiff[] schiffe;

    public Kunde() {
        this.schiffe = new Schiff[42];
        this.kundenNr = kundenstammNr++;
    }

    public Kunde(String name) {
        this.schiffe = new Schiff[42];
        this.name = name;
        this.kundenNr = kundenstammNr++;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getKundenNr() {
        return kundenNr;
    }

    public void setKundenNr(int kundenNr) {
        this.kundenNr = kundenNr;
    }

    public Schiff[] getSchiffe() {
        return schiffe;
    }

    public void setSchiffe(Schiff[] schiffe) {
        this.schiffe = schiffe;
    }

    public boolean neuesSchiffAnlegen(String name, double tonnage) {
        for (int i = 0; i < schiffe.length; i++) {
            if (schiffe[i] == null) {
                schiffe[i] = new Schiff(name, tonnage);
                return true;
            }
        }

        return false;
    }

    public boolean neuesMotorSchiffAnlegen(String name, double tonnage, double motorleistung) {
        for (int i = 0; i < schiffe.length; i++) {
            if (schiffe[i] == null) {
                schiffe[i] = new Motorschiff(name, tonnage, motorleistung);
                return true;
            }
        }

        return false;
    }

    public boolean neuesSegelSchiffAnlegen(String name, double tonnage, double segelflaeche) {
        for (int i = 0; i < schiffe.length; i++) {
            if (schiffe[i] == null) {
                schiffe[i] = new Segelschiff(name, tonnage, segelflaeche);
                return true;
            }
        }

        return false;
    }
}
