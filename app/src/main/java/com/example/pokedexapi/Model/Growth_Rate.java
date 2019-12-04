package com.example.pokedexapi.Model;

public class Growth_Rate { //this is also not used in the program at the moment

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
        return "Growth_Rate{" +
                "name='" + name + '\'' +
                ", url='" + url + '\'' +
                '}';
    }
}
