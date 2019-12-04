package com.example.pokedexapi.Repository;


import com.example.pokedexapi.Service.PokemonService;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PokeRepo { //retrofit converts the url into gson/json, moves to service for use

    public PokemonService pokeService;

    private static final String url = "https://pokeapi.co/api/v2/";

    public PokeRepo(){

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        pokeService = retrofit.create(PokemonService.class);
    }

}
