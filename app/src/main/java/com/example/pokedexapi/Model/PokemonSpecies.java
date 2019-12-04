package com.example.pokedexapi.Model;

import java.util.Arrays;

public class PokemonSpecies { //this holds the other 10% of data I'm looking for

    public Flavor_Text_Entries[] flavor_text_entries; //description
    public Growth_Rate growth_rate; //not used
    public Color color; //color, would like to use in future

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

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    @Override
    public String toString() {
        return "PokemonSpecies{" +
                "flavor_text_entries=" + Arrays.toString(flavor_text_entries) +
                ", growth_rate=" + growth_rate +
                ", color=" + color +
                '}';
    }
}
