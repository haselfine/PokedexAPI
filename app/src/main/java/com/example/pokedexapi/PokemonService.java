package com.example.pokedexapi;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface PokemonService {

    @GET("pokemon/{name}")
    Call<Pokemon> getPokemon(@Path("name") String name);

    @GET("ability/{name}")
    Call<Ability> getAbility(@Path("name") String name);
}
