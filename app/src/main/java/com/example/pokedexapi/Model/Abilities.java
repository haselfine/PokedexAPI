package com.example.pokedexapi.Model;

public class Abilities { //for JSON deserialization, holds abilities ( this one is actually not used in program, but could in future)
    public int slot;
    public boolean is_hidden;
    public Ability ability;

    public int getSlot() {
        return slot;
    }

    public void setSlot(int slot) {
        this.slot = slot;
    }

    public boolean is_hidden() {
        return is_hidden;
    }

    public void setHidden(boolean hidden) {
        is_hidden = hidden;
    }


    @Override
    public String toString() {
        return "Abilities{" +
                "slot=" + slot +
                ", is_hidden=" + is_hidden +
                ", ability=" + ability +
                '}';
    }
}
