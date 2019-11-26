package com.example.pokedexapi.Model;

public class Flavor_Text_Entries {

    public String flavor_text;
    public Language language;
    public Version version;

    public String getFlavor_text() {
        return flavor_text;
    }

    public void setFlavor_text(String flavor_text) {
        this.flavor_text = flavor_text;
    }

    public Language getLanguage() {
        return language;
    }

    public void setLanguage(Language language) {
        this.language = language;
    }

    public Version getVersion() {
        return version;
    }

    public void setVersion(Version version) {
        this.version = version;
    }

    @Override
    public String toString() {
        return "Flavor_Text_Entries{" +
                "flavor_text='" + flavor_text + '\'' +
                ", language=" + language +
                ", version=" + version +
                '}';
    }
}
