package com.example.pokedexapi;

public class Stats {

    public int effort;
    public int base_stat;
    public Stat stat;

    public int getEffort() {
        return effort;
    }

    public void setEffort(int effort) {
        this.effort = effort;
    }

    public int getBase_stat() {
        return base_stat;
    }

    public void setBase_stat(int base_stat) {
        this.base_stat = base_stat;
    }

    public Stat getStat() {
        return stat;
    }

    public void setStat(Stat stat) {
        this.stat = stat;
    }

    @Override
    public String toString() {
        return "Stats{" +
                "effort=" + effort +
                ", base_stat=" + base_stat +
                ", stat=" + stat +
                '}';
    }
}
