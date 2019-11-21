package com.example.pokedexapi;

public class Growth_Rate {

    String name;
    String url;

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
        return "Growth_Rate{" +
                "name='" + name + '\'' +
                ", url='" + url + '\'' +
                '}';
    }
}
