package com.example.pokedexapi;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;


/**
 * A simple {@link Fragment} subclass.
 */
public class PokemonDisplayFragment extends Fragment {

    TextView nameTV;
    TextView typeTV;
    TextView descriptionTV;
    TextView heightTV;
    TextView weightTV;
    ImageView spriteIV;

    PokeRepo pokeRepo;

    private static final String TAG = "Display Frag";

    public static PokemonDisplayFragment newInstance() {
        PokemonDisplayFragment fragment = new PokemonDisplayFragment();
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_pokemon_display, container, false);

        pokeRepo = new PokeRepo();

        nameTV = view.findViewById(R.id.name_TextView);
        typeTV = view.findViewById(R.id.type_TextView);
        descriptionTV = view.findViewById(R.id.characteristic_TextView);
        heightTV = view.findViewById(R.id.height_TextView);
        weightTV = view.findViewById(R.id.weight_TextView);
        spriteIV = view.findViewById(R.id.sprite_imageView);

        return view;
    }


    public void setAttributes(Pokemon pokeResponse) {
        nameTV.setText(pokeResponse.name);
        typeTV.setText(pokeResponse.types[0].toString());
    }
}
