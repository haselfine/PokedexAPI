package com.example.pokedexapi.View;


import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pokedexapi.DB.Favorite;
import com.example.pokedexapi.R;
import com.example.pokedexapi.ViewModel.FavoriteViewModel;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class FavoriteFragment extends Fragment implements FavoriteListener { //displays list of saved favorites for easier searches

    private static final String TAG = "Favorite Fragment"; //tag for log data
    private List<Favorite> mFavorites;

    private FavoriteListAdapter mFavoriteListAdapter;

    private FavoriteViewModel mFavoriteViewModel;

    private SearchListener mSearchListener;

    public FavoriteFragment() {
        // Required empty public constructor
    }

    public static FavoriteFragment newInstance(){
        FavoriteFragment fragment = new FavoriteFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        mFavoriteViewModel = ViewModelProviders.of(getActivity()).get(FavoriteViewModel.class); //use viewmodel to interact with data in database


        mFavoriteViewModel.getFavorites().observe(this, new Observer<List<Favorite>>() {
            @Override
            public void onChanged(List<Favorite> favorites) { //observe changes
                Log.d(TAG, "Favorites changed: " + favorites);
                FavoriteFragment.this.mFavorites = favorites;
                FavoriteFragment.this.mFavoriteListAdapter.setFavorites(favorites); //set updated list data to recycler view
                FavoriteFragment.this.mFavoriteListAdapter.notifyDataSetChanged(); //tell adapter there's a new dataset
            }
        });

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_favorite, container, false);

        RecyclerView recyclerView = view.findViewById(R.id.recyclerView);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this.getContext());
        recyclerView.setLayoutManager(layoutManager);
        mFavoriteListAdapter = new FavoriteListAdapter(this.getContext(), this);
        mFavoriteListAdapter.setFavorites(mFavorites);
        recyclerView.setAdapter(mFavoriteListAdapter);

        return view;
    }

    @Override
    public void onAttach(Context context){
        super.onAttach(context);

        Log.d(TAG, "onAttach");

        if(context instanceof SearchListener){
            mSearchListener = (SearchListener) context; //listener attach
        } else {
            throw new RuntimeException(context.toString() + " must implement SearchListener.");
        }
    }

    @Override
    public void onStart(){
        super.onStart();
        mFavoriteViewModel = ViewModelProviders.of(this).get(FavoriteViewModel.class);
    }

    @Override
    public void onListClick(int position){
        Favorite favorite = mFavorites.get(position); //find which element was clicked
        String name = favorite.getName().toLowerCase(); //convert name to lowercase for search
        mSearchListener.searchFromFavorites(name); //send search to main activity/change to display fragment
    }

    @Override
    public void onListLongClick(final int position){
        final Favorite favorite = mFavorites.get(position); //find which element was clicked

        AlertDialog confirmDeleteDialog = new AlertDialog.Builder(getActivity()) //confirm delete
                .setMessage(getString(R.string.delete_favorite_message, favorite.getName()))
                .setTitle("Delete Favorite?")
                .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        mFavoriteViewModel.delete(favorite); //if yes, delete from database
                        mFavoriteListAdapter.notifyItemRemoved(position); //remove from recyclerview
                    }
                })
                .setNegativeButton(android.R.string.cancel, null)
                .create();
        confirmDeleteDialog.show();

    }
}
