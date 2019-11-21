package com.example.pokedexapi;

import java.util.Arrays;

public class Pokemon {

    int id;
    String name;
    Types[] types;
    int height;
    int weight;
    Sprites sprites;
    Stats[] stats;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Types[] getTypes() {
        return types;
    }

    public void setTypes(Types[] types){
        this.types = types;
    }


    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public Sprites getSprites() {
        return sprites;
    }

    public void setSprites(Sprites sprites){
        this.sprites = sprites;
    }

    public Stats[] getStats() {
        return stats;
    }

    public void setStats(Stats[] stats){
        this.stats = stats;
    }

    @Override
    public String toString() {
        return "Pokemon{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", types=" + Arrays.toString(types) +
                ", height=" + height +
                ", weight=" + weight +
                ", sprites=" + sprites +
                ", stats=" + Arrays.toString(stats) +
                '}';
    }
}




