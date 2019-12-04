package com.example.pokedexapi.View;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.pokedexapi.Model.Pokemon;
import com.example.pokedexapi.Model.PokemonSpecies;
import com.example.pokedexapi.R;
import com.example.pokedexapi.Repository.PokeRepo;
import com.example.pokedexapi.Service.PokemonService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements SearchListener{

    private static final String TAG_DISPLAY = "DISPLAY";
    private static final String TAG_SEARCH = "SEARCH";
    private static final String TAG_FAVORITE = "FAVORITE";
    private static final String TAG = "Main activity";

    PokemonService pokeService;
    PokeRepo pokeRepo;
    String mName;

    private static final String BUNDLE_KEY_NAME = "POKEMON NAME";

    private static final String url = "https://pokeapi.co/api/v2/";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE); //sets so there's no title bar
        setContentView(R.layout.activity_main);

        if(savedInstanceState == null){ //if just opening app...
            SearchBar searchBar = SearchBar.newInstance(); //create instance of search bar
            PokemonDisplayFragment pokemonDisplayFragment = PokemonDisplayFragment.newInstance(); //create instance of display fragment


            FragmentManager fm = getSupportFragmentManager(); //add fragments to screen
            FragmentTransaction ft = fm.beginTransaction();
            ft.add(R.id.searchBar_container, searchBar, TAG_SEARCH);
            ft.add(R.id.pokemon_display_container, pokemonDisplayFragment, TAG_DISPLAY);
            ft.hide(pokemonDisplayFragment); //since there's no info to display of the pokemon, hides detail fragment from view
            ft.commit();


            pokeRepo = new PokeRepo(); //instantiate repository
            pokeService = pokeRepo.pokeService; //connect to service
        } else { //if changing orientation or returning from main screen
            pokeRepo = new PokeRepo();
            pokeService = pokeRepo.pokeService;
            String name = savedInstanceState.getString(BUNDLE_KEY_NAME); //remember last search
            if(name != null){
                searchForPokemon(name); //search that pokemon again
            }
        }
    }

    public void searchForPokemon(String name){
        mName = name; //hold for saveinstancestate
        getPokemonData(name); //search by name
    }

    public void searchFromFavorites(String name){ //calls from favorite fragment
        mName = name; //hold for saveinstancestate

        FragmentManager fm = getSupportFragmentManager();
        FavoriteFragment favoriteFragment = (FavoriteFragment) fm.findFragmentByTag(TAG_FAVORITE);

        if(favoriteFragment != null){ //if calling from favorite fragment...
            FragmentTransaction ft = fm.beginTransaction();
            ft.remove(favoriteFragment); //get rid of favorite fragment from screen
            ft.addToBackStack(TAG_SEARCH); //allow back button (search bar never left)
            ft.addToBackStack(TAG_DISPLAY); //same as search bar
            ft.commit();
        }
        getPokemonData(name); //search by name from item clicked on favorite fragment
    }

    @Override
    public void onFavoriteClick(){ //if user clicks the "Favorites" button next to search bar...

        FragmentManager fm = getSupportFragmentManager(); //change view to favorites fragment
        FragmentTransaction ft = fm.beginTransaction();
        FavoriteFragment favoriteFragment = FavoriteFragment.newInstance();
        ft.replace(android.R.id.content, favoriteFragment, TAG_FAVORITE);
        ft.addToBackStack(TAG_FAVORITE);
        ft.commit();
    }


    public void getPokemonData(final String name){

        hideKeyboard(); //if keyboard is visible, hide

        pokeService.getPokemon(name).enqueue(new Callback<Pokemon>() { //return data of **Pokemon** from service
            @Override
            public void onResponse(Call<Pokemon> call, Response<Pokemon> response) {
                Pokemon pokeResponse = response.body(); //hold response in Pokemon object
                Log.d(TAG, "Pokemon response: " + pokeResponse);

                if(pokeResponse != null){ //if it returns a pokemon...
                    FragmentManager fm = getSupportFragmentManager();
                    PokemonDisplayFragment pokemonDisplayFragment = (PokemonDisplayFragment) fm.findFragmentByTag(TAG_DISPLAY);
                    fm.beginTransaction().show(pokemonDisplayFragment).commit(); //make hidden display fragment show

                    pokemonDisplayFragment.setAttributes(pokeResponse); //set details on fragment display

                } else { //catches misspelled pokemon (no autocomplete on this version of app)
                    Log.d(TAG, "Search for " + name + " did not return results");
                    Toast.makeText(getApplicationContext(), "Pokemon not found. Check spelling.", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<Pokemon> call, Throwable t) { //if unable to communicate with database
                Log.e(TAG, "Error fetching Pokemon", t);
                Toast.makeText(MainActivity.this, "Unable to fetch Pokemon", Toast.LENGTH_LONG).show();
            }
        });

        pokeService.getPokemonSpecies(name).enqueue(new Callback<PokemonSpecies>() { //return data of **Pokemon Species** from service
            @Override
            public void onResponse(Call<PokemonSpecies> call, Response<PokemonSpecies> response) { //same as above except doesn't "unhide" display fragment
                PokemonSpecies speciesResponse = response.body();
                Log.d(TAG, "Pokemon response: " + speciesResponse);

                if(speciesResponse != null){
                    FragmentManager fm = getSupportFragmentManager();
                    PokemonDisplayFragment pokemonDisplayFragment = (PokemonDisplayFragment) fm.findFragmentByTag(TAG_DISPLAY);

                    pokemonDisplayFragment.setAttributes(speciesResponse);

                } else {
                    Log.d(TAG, "Search for " + name + " did not return results");
                }
            }

            @Override
            public void onFailure(Call<PokemonSpecies> call, Throwable t) {
                Log.e(TAG, "Error fetching Pokemon", t);
                Toast.makeText(MainActivity.this, "Unable to fetch Pokemon", Toast.LENGTH_LONG).show();
            }
        });
    }

    private void hideKeyboard() {
        View mainView = findViewById(android.R.id.content);
        InputMethodManager manager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        manager.hideSoftInputFromWindow(mainView.getWindowToken(), 0);
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outBundle){
        super.onSaveInstanceState(outBundle);
        outBundle.putString(BUNDLE_KEY_NAME, mName); //saves just the name for a repeat search
    }
}
