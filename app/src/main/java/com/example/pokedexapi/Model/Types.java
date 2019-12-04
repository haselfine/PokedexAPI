package com.example.pokedexapi.Model;

public class Types //holds individual type
{
    public String slot;

    public Type type;

    @Override
    public String toString() {
        return "Types{" +
                "slot='" + slot + '\'' +
                ", type=" + type +
                '}';
    }
}