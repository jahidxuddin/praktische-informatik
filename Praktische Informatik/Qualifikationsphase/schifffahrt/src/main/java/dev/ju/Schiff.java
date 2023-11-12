package dev.ju;

public class Schiff {
    private String name;
    private double tonnage;

    public Schiff(String name, double tonnage) {
        this.name = name;
        this.tonnage = tonnage;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getTonnage() {
        return tonnage;
    }

    public void setTonnage(double tonnage) {
        this.tonnage = tonnage;
    }
}
