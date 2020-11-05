package com.example.projectprototype;

public class Exercise {
    private String name;
    private int sets;
    private int units;
    public Exercise(){};

    public Exercise(String ename, int esets, int eunits) {
        name = ename;
        sets = esets;
        units = eunits;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSets() {
        return sets;
    }

    public void setSets(int sets) {
        this.sets = sets;
    }

    public int getUnits() {
        return units;
    }

    public void setUnits(int units) {
        this.units = units;
    }
}
