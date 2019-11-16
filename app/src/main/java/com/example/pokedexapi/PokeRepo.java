package com.example.pokedexapi;


import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PokeRepo {

    PokemonService pokeService;

    private static final String url = "https://pokeapi.co/api/v2/";

    public PokeRepo(){

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        pokeService = retrofit.create(PokemonService.class);
    }

}
