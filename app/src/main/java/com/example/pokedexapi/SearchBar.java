package com.example.pokedexapi;


import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 */
public class SearchBar extends Fragment implements View.OnClickListener {

    EditText searchET;
    Button searchBtn;
    String mPokemonName;

    private static final String TAG = "SEARCH BAR FRAG";

    interface SearchBarListener{
        void searchForPokemon(String name);
    }

    public static SearchBar newInstance() {
        SearchBar searchBar = new SearchBar();
        return searchBar;
    }

    private SearchBarListener mSearchListener;


    @Override
    public void onAttach(@NonNull Context context){
        super.onAttach(context);

        Log.d(TAG, "onAttach");

        if(context instanceof SearchBarListener){
            mSearchListener = (SearchBarListener) context;
        } else {
            throw new RuntimeException(context.toString() + " must implement SearchBarListener");
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

        return view;
    }
    @Override
    public void onClick(View v) {
        mPokemonName = searchET.getText().toString();
        if(mPokemonName.isEmpty()){
            Toast.makeText(getContext(), "Enter a Pokemon name", Toast.LENGTH_LONG).show();
            return;
        }
        mPokemonName = mPokemonName.toLowerCase();
        mSearchListener.searchForPokemon(mPokemonName);
    }

}
