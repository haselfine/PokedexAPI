package com.example.pokedexapi.View;

public interface SearchListener {
    void searchForPokemon(String name);
    void searchFromFavorites(String name);
    void onFavoriteClick();
}
