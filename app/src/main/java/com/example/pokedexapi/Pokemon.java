package com.example.pokedexapi;

public class Pokemon {

    int id;
    String name;
    Type[] types;
    int height;
    int weight;
    Ability[] abilities;
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

    public Type[] getTypes() {
        return types;
    }

    public void setTypes(Type[] types){
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

    public Ability[] getAbilities() {
        return abilities;
    }

    public void setAbilities(Ability[] abilities){
        this.abilities = abilities;
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
    public String toString()
    {
        return "Pokemon [types = "+types+", weight = "+weight+", sprites = "+sprites+", abilities = "+abilities+", stats = "+stats+", name = "+name+", id = "+id+", height = "+height+"]";
    }

}




