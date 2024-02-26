package dev.ju;

public class Lehrer extends Person {
    private String name;
    private String namensKuerzel;

    public Lehrer(String name, String namensKuerzel) {
        super(name);
        this.name = name;
        this.namensKuerzel = namensKuerzel;
    }

    public String getNamensKuerzel() {
        return namensKuerzel;
    }

    public void setNamensKuerzel(String namensKuerzel) {
        this.namensKuerzel = namensKuerzel;
    }

    @Override
    public String toString() {
        return this.name + " (" + this.getNamensKuerzel() + ")";
    }
}
