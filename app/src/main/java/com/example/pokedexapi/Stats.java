package com.example.pokedexapi;

public class Stats {

    int effort;
    int baseStat;

    public int getEffort() {
        return effort;
    }

    public void setEffort(int effort) {
        this.effort = effort;
    }

    public int getBaseStat() {
        return baseStat;
    }

    public void setBaseStat(int baseStat) {
        this.baseStat = baseStat;
    }

    @Override
    public String toString() {
        return "Stats{" +
                "effort=" + effort +
                ", baseStat=" + baseStat +
                '}';
    }
}
