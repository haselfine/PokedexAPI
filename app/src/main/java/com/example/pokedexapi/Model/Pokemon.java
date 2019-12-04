package com.example.pokedexapi.Model;

import java.util.Arrays;

public class Pokemon { //main class/object, holds 90% of the information I'm looking for

    public Stats[] stats;
    public int id;
    public String name;
    public Types[] types;
    public int height;
    public int weight;
    public Sprites sprites;


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




