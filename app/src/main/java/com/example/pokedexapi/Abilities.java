package com.example.pokedexapi;

public class Abilities {
    int slot;
    boolean is_hidden;
    Ability ability;

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
    public String toString()
    {
        return "ClassPojo [is_hidden = "+is_hidden+", slot = "+slot+", ability = "+ability+"]";
    }
}
