package com.example.pokedexapi.Model;

public class Color { //I pulled this so I could make the recycler views the same color as the pokemon, but I couldn't figure out how to make them hold

    public String name;
    public String url;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "Color{" +
                "name='" + name + '\'' +
                ", url='" + url + '\'' +
                '}';
    }
}
