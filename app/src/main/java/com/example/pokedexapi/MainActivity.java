package com.example.pokedexapi;

import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements SearchBar.SearchBarListener {

    private static final String TAG_DISPLAY = "DISPLAY";
    private static final String TAG_SEARCH = "SEARCH";
    private static final String TAG = "Main activity";

    PokemonService pokeService;
    PokeRepo pokeRepo;

    private static final String url = "https://pokeapi.co/api/v2/";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);

        SearchBar searchBar = SearchBar.newInstance();
        PokemonDisplayFragment pokemonDisplayFragment = PokemonDisplayFragment.newInstance();



        FragmentManager fm = getSupportFragmentManager();
        fm.beginTransaction()
                .add(R.id.searchBar_container, searchBar, TAG_SEARCH)
                .add(R.id.pokemon_display_container, pokemonDisplayFragment, TAG_DISPLAY)
                .commit();

        pokeRepo = new PokeRepo();
        pokeService = pokeRepo.pokeService;
    }

    @Override
    public void searchForPokemon(String name){
        getPokemonData(name);
    }

    public void getPokemonData(final String name){
        pokeService.getPokemon(name).enqueue(new Callback<Pokemon>() {
            @Override
            public void onResponse(Call<Pokemon> call, Response<Pokemon> response) {
                Pokemon pokeResponse = response.body();
                Log.d(TAG, "Pokemon response: " + pokeResponse);

                if(pokeResponse != null){
                    FragmentManager fm = getSupportFragmentManager();
                    PokemonDisplayFragment pokemonDisplayFragment = (PokemonDisplayFragment) fm.findFragmentByTag(TAG_DISPLAY);

                    pokemonDisplayFragment.setAttributes(pokeResponse);

                } else {
                    Log.d(TAG, "Search for " + name + " did not return results");
                }
            }

            @Override
            public void onFailure(Call<Pokemon> call, Throwable t) {
                Log.e(TAG, "Error fetching Pokemon", t);
                Toast.makeText(MainActivity.this, "Unable to fetch Pokemon", Toast.LENGTH_LONG).show();
            }
        });
    }

}
