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
public class FavoriteFragment extends Fragment implements FavoriteListener {

    private static final String TAG = "Favorite Fragment";
    private List<Favorite> mFavorites;

    private FavoriteListAdapter mFavoriteListAdapter;

    private FavoriteViewModel mFavoriteViewModel;

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

        mFavoriteViewModel = ViewModelProviders.of(getActivity()).get(FavoriteViewModel.class);


        mFavoriteViewModel.getFavorites().observe(this, new Observer<List<Favorite>>() {
            @Override
            public void onChanged(List<Favorite> favorites) {
                Log.d(TAG, "Favorites changed: " + favorites);
                FavoriteFragment.this.mFavorites = favorites;
                FavoriteFragment.this.mFavoriteListAdapter.setFavorites(favorites);
                FavoriteFragment.this.mFavoriteListAdapter.notifyDataSetChanged();
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
    }

    @Override
    public void onStart(){
        super.onStart();
        mFavoriteViewModel = ViewModelProviders.of(this).get(FavoriteViewModel.class);
    }

    @Override
    public void onListClick(int position){
        Favorite favorite = mFavorites.get(position);
        //TODO send to main to begin fragment transaction with displayfragment for search
    }

    @Override
    public void onListLongClick(final int position){
        final Favorite favorite = mFavorites.get(position);

        AlertDialog confirmDeleteDialog = new AlertDialog.Builder(getActivity())
                .setMessage(getString(R.string.delete_favorite_message, favorite.getName()))
                .setTitle("Delete Favorite?")
                .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        mFavoriteViewModel.delete(favorite);
                        mFavoriteListAdapter.notifyItemRemoved(position);
                    }
                })
                .setNegativeButton(android.R.string.cancel, null)
                .create();
        confirmDeleteDialog.show();

    }
}
