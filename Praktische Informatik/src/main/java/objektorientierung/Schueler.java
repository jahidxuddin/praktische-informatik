package main.java.objektorientierung;

public class Schueler {
    private String name;
    private Kurs[] kurse;
    private Tutor tutor;

    public Schueler(String name, Kurs[] kurse, Tutor tutor) {
        this.name = name;
        this.kurse = kurse;
        this.tutor = tutor;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Kurs[] getKurse() {
        return kurse;
    }

    public void setKurse(Kurs[] kurse) {
        this.kurse = kurse;
    }

    public Tutor getTutor() {
        return tutor;
    }

    public void setTutor(Tutor tutor) {
        this.tutor = tutor;
    }
}
