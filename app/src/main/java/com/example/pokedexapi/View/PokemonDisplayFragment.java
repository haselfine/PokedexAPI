package com.example.pokedexapi.View;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.example.pokedexapi.DB.Favorite;
import com.example.pokedexapi.Model.Pokemon;
import com.example.pokedexapi.Model.PokemonSpecies;
import com.example.pokedexapi.R;
import com.example.pokedexapi.Repository.PokeRepo;
import com.example.pokedexapi.ViewModel.FavoriteViewModel;
import com.squareup.picasso.Picasso;


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
    ProgressBar hpPB;
    ProgressBar attPB;
    ProgressBar defPB;
    ProgressBar spAttPB;
    ProgressBar spDefPB;
    ProgressBar speedPB;
    TextView hpTV;
    TextView attTV;
    TextView defTV;
    TextView spAttTV;
    TextView spDefTV;
    TextView speedTV;
    ImageButton heartBtn;
    boolean hearted;
    String mColor;

    PokeRepo pokeRepo;

    FavoriteViewModel mFavoriteViewModel;
    FavoriteListAdapter mFavoriteListAdapter;

    private static final String TAG = "Display Frag";

    public static PokemonDisplayFragment newInstance() {
        PokemonDisplayFragment fragment = new PokemonDisplayFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mFavoriteViewModel = ViewModelProviders.of(getActivity()).get(FavoriteViewModel.class); //connect to view model
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
        hpPB = view.findViewById(R.id.hp_ProgBar);
        attPB = view.findViewById(R.id.att_ProgBar);
        defPB = view.findViewById(R.id.def_ProgBar);
        spAttPB = view.findViewById(R.id.spAtt_ProgBar);
        spDefPB = view.findViewById(R.id.spDef_ProgBar);
        speedPB = view.findViewById(R.id.speed_ProgBar);
        hpTV = view.findViewById(R.id.hp_textView);
        attTV = view.findViewById(R.id.att_textView);
        defTV = view.findViewById(R.id.def_textView);
        spAttTV = view.findViewById(R.id.spAtt_textView);
        spDefTV = view.findViewById(R.id.spDef_textView);
        speedTV = view.findViewById(R.id.speed_textView);
        heartBtn = view.findViewById(R.id.heart_button);
        heartBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setHearted();
            }
        });

        return view;
    }

    private void setHearted() { //when user clicks heart button...
        if(!hearted){ //if it isn't yet hearted...
            hearted = true; //now it is hearted
            heartBtn.setImageResource(R.drawable.liked); //changes to red icon
            Toast.makeText(getContext(), "Added to favorites", Toast.LENGTH_SHORT).show(); //displays toast for user

            String name = nameTV.getText().toString(); //retrieves necessary info - name, type, and color
            String type = typeTV.getText().toString().substring(6);
            String color = mColor;

            Favorite favorite = new Favorite(name, type, color); //sets those to favorite object
            mFavoriteViewModel.insert(favorite); //inserts into favorite database

        } else { //if it is hearted...
            hearted = false; //now it's not
            heartBtn.setImageResource(R.drawable.notliked); //sets to gray heart icon
        }
    }


    public void setAttributes(Pokemon pokeResponse) { //this is for the **pokemon** response
        hearted = false; //assumes no pokemon is in favorites
        heartBtn.setImageResource(R.drawable.notliked);
        String name = "Name: " + pokeResponse.name; //set name
        name = name.substring(6,7).toUpperCase() + name.substring(7); //uppercase the pokemon's name
        String types; //same as name but with types
        String type0 = pokeResponse.types[0].type.name;
        type0 = type0.substring(0,1).toUpperCase() + type0.substring(1);
        if(pokeResponse.types.length > 1){ //since there can be more than one type, sets to "types" with a slash between the two
            String type1 = pokeResponse.types[1].type.name;
            type1 = type1.substring(0,1).toUpperCase() + type1.substring(1);
            types = "Types:\n" + type0 + "/" +
                    type1;
        } else { //otherwise it's just one type
            types = "Type: " + type0;
        }

        double height = (double) pokeResponse.height / 10; //convert response to height in meters
        double weight = (double) pokeResponse.weight / 10; //convert response to weight in kilograms

        String heightString = "Height: " + height +" m";
        String weightString = "Weight: " + weight + " kg";

        int HP = pokeResponse.stats[5].base_stat;
        String hpString = "HP:\t\t" + HP; //convert response to formatted string
        int attack = pokeResponse.stats[4].base_stat;
        String attString = "Attack:\t\t" + attack; //all stats follow the same pattern as HP
        int defense = pokeResponse.stats[3].base_stat;
        String defString = "Defense:\t\t" + defense;
        int specialAttack = pokeResponse.stats[2].base_stat;
        String spAttString = "Sp. Atk:\t\t" + specialAttack;
        int specialDefense = pokeResponse.stats[1].base_stat;
        String spDefString = "Sp. Def:\t\t" + specialDefense;
        int speed = pokeResponse.stats[0].base_stat;
        String speedString = "Speed:\t\t" + speed;

        nameTV.setText(name); //set display objects to formatted responses
        typeTV.setText(types);
        heightTV.setText(heightString);
        weightTV.setText(weightString);
        Picasso.get().load(pokeResponse.sprites.front_default).into(spriteIV);
        hpPB.setProgress(HP);
        hpTV.setText(hpString);
        attPB.setProgress(attack);
        attTV.setText(attString);
        defPB.setProgress(defense);
        defTV.setText(defString);
        spAttPB.setProgress(specialAttack);
        spAttTV.setText(spAttString);
        spDefPB.setProgress(specialDefense);
        spDefTV.setText(spDefString);
        speedPB.setProgress(speed);
        speedTV.setText(speedString);

    }

    public void setAttributes(PokemonSpecies speciesResponse) { //this is specifically for the **pokemon species** response
        int i = 0;
        int holder = 0;
        for(i = 0; i < speciesResponse.flavor_text_entries.length; i++){ //cycles through all descriptions
            if(speciesResponse.flavor_text_entries[i].language.name.equals("en")){ //first description that is in english
                holder = i; //holds that number in the array
                break; //stop loop
            }
        }

        String description = speciesResponse.flavor_text_entries[holder].flavor_text; //takes description from first english entry
        description = description.replace("\n"," "); //remove newlines
        if(description.length() > 190){ //if over 190 characters
            descriptionTV.setTextSize(14); //change font size to fit description
        } else {
            descriptionTV.setTextSize(16); //otherwise default size
        }

        descriptionTV.setText(description);

        mColor = speciesResponse.color.name; //hold color (not used in this version of program)
    }
}
