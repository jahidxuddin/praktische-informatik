package dev.ju;

public class Lehrer {
    private String name;
    private String kuerzel;

    public Lehrer() {

    }

    public Lehrer(String name, String kuerzel) {
        this.name = name;
        this.kuerzel = kuerzel;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getKuerzel() {
        return kuerzel;
    }

    public void setKuerzel(String kuerzel) {
        this.kuerzel = kuerzel;
    }
}
