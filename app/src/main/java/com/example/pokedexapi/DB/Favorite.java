package com.example.pokedexapi.DB;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.util.Date;

@Entity
public class Favorite implements Comparable<Favorite>{

    int id; //this is not used since I switched to having the name be the primary key
    @NonNull
    @PrimaryKey(autoGenerate = false)
    String name; //name is primary key as each pokemon has one name, and I don't want to allow duplicates
    Date date; //date to show when the user added this pokemon to their favorites
    String type; //each pokemon has a type which is useful for displaying
    String color; //this is here because I wanted to make the recycler view items the same color as the pokemon, but I had issues implementing that (colors jumped around)

    @Ignore
    public Favorite(String name, String type, String color) {
        this.name = name;
        this.date = new Date();
        this.type = type;
        this.color = color;
    }

    public Favorite(){}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @Override
    public int compareTo(Favorite favorite) {
        return this.date.compareTo(favorite.date);
    }

    @Override
    public String toString() {
        return "Favorite{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", date=" + date +
                ", type='" + type + '\'' +
                ", color='" + color + '\'' +
                '}';
    }
}
