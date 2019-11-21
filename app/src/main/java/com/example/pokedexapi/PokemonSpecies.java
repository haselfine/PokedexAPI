package com.example.pokedexapi;

import java.util.Arrays;

public class PokemonSpecies {

    Flavor_Text_Entries[] flavor_text_entries;
    Growth_Rate growth_rate;

    public Flavor_Text_Entries[] getFlavor_text_entries() {
        return flavor_text_entries;
    }

    public void setFlavor_text_entries(Flavor_Text_Entries[] flavor_text_entries) {
        this.flavor_text_entries = flavor_text_entries;
    }

    public Growth_Rate getGrowth_rate() {
        return growth_rate;
    }

    public void setGrowth_rate(Growth_Rate growth_rate) {
        this.growth_rate = growth_rate;
    }

    @Override
    public String toString() {
        return "PokemonSpecies{" +
                "flavor_text_entries=" + Arrays.toString(flavor_text_entries) +
                ", growth_rate=" + growth_rate +
                '}';
    }
}
