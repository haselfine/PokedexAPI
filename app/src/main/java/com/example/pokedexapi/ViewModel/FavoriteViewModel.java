package com.example.pokedexapi.ViewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.pokedexapi.DB.FavRepository;
import com.example.pokedexapi.DB.Favorite;

import java.util.List;

public class FavoriteViewModel extends AndroidViewModel {
    private FavRepository mFavRepository;

    private LiveData<List<Favorite>> favorites;

    public FavoriteViewModel (@NonNull Application application){
        super(application);
        mFavRepository = new FavRepository(application);
        favorites = mFavRepository.getAllFavs();
    }

    public LiveData<List<Favorite>> getFavorites(){
        return favorites;
    } //can return list of favorites

    public void insert(Favorite favorite){
        mFavRepository.insert(favorite);
    } //add a new favorite

    public void delete(Favorite favorite){mFavRepository.delete(favorite);} //delete a favorite from database
}
