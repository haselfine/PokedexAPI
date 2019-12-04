package com.example.pokedexapi.View;


import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.pokedexapi.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class SearchBar extends Fragment implements View.OnClickListener {

    EditText searchET;
    Button searchBtn;
    Button favoritesBtn;
    String mPokemonName;

    private static final String TAG = "SEARCH BAR FRAG";

    public static SearchBar newInstance() {
        SearchBar searchBar = new SearchBar();
        return searchBar;
    }

    private SearchListener mSearchListener;


    @Override
    public void onAttach(@NonNull Context context){
        super.onAttach(context);

        Log.d(TAG, "onAttach");

        if(context instanceof SearchListener){
            mSearchListener = (SearchListener) context; //attach listener
        } else {
            throw new RuntimeException(context.toString() + " must implement SearchListener");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_search_bar, container, false);

        searchET = view.findViewById(R.id.search_EditText);
        searchBtn = view.findViewById(R.id.search_Button);
        searchBtn.setOnClickListener(this);
        favoritesBtn = view.findViewById(R.id.favorites_button);
        favoritesBtn.setOnClickListener(this);

        return view;
    }
    @Override
    public void onClick(View v) { //tells buttons apart by their id
        int button = v.getId();
        switch (button){
            case R.id.search_Button: //if search button...
                mPokemonName = searchET.getText().toString();
                if(mPokemonName.isEmpty()){ //check for empty text
                    Toast.makeText(getContext(), "Enter a Pokemon name", Toast.LENGTH_LONG).show(); //display hint to enter text
                    return;
                }
                mPokemonName = mPokemonName.toLowerCase(); //convert to lowercase
                mSearchListener.searchForPokemon(mPokemonName); //goes to search function in main activity
                break;
            case R.id.favorites_button: //if favorites button...
                mSearchListener.onFavoriteClick();  //send to response in main activity (bring up favorites fragment)
                break;
        }

    }

}
