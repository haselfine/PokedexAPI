package com.example.pokedexapi.Service;

import com.example.pokedexapi.Model.Pokemon;
import com.example.pokedexapi.Model.PokemonSpecies;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface PokemonService {

    @GET("pokemon/{name}")
    Call<Pokemon> getPokemon(@Path("name") String name);

    @GET("pokemon-species/{name}")
    Call<PokemonSpecies> getPokemonSpecies(@Path("name") String name);
}
