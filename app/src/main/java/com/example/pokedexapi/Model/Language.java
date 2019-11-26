package com.example.pokedexapi.Model;

public class Language {

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
        return "Language{" +
                "name='" + name + '\'' +
                ", url='" + url + '\'' +
                '}';
    }
}
