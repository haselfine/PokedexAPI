package com.example.pokedexapi.DB;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

public class FavRepository { //defines database interactions
    private FavDAO favDAO;

    public FavRepository(Application application){
        FavDatabase db = FavDatabase.getDatabase(application);
        favDAO = db.favDAO();
    }

    public void insert(Favorite favorite){
        new InsertFavAsync(favDAO).execute(favorite);
    }

    static class InsertFavAsync extends AsyncTask<Favorite, Void, Void> {
        private  FavDAO favDAO;

        InsertFavAsync(FavDAO favDAO){
            this.favDAO = favDAO;
        }

        @Override
        protected Void doInBackground(Favorite... favorites){
            favDAO.insert(favorites);
            return null;
        }
    }

    public void update(Favorite favorite){
        new UpdateWaterAsync(favDAO).execute(favorite);
    }

    static class UpdateWaterAsync extends AsyncTask<Favorite, Void, Void>{
        private FavDAO favDAO;

        UpdateWaterAsync(FavDAO favDAO){
            this.favDAO = favDAO;
        }

        @Override
        protected Void doInBackground(Favorite... favorites){
            favDAO.update(favorites);
            return null;
        }
    }

    public LiveData<List<Favorite>> getAllFavs(){
        return favDAO.getAllFavs();
    }

    public void delete(Favorite favorite){
        new DeleteFavAsyncTask(favDAO).execute(favorite);
    }

    private static class DeleteFavAsyncTask extends AsyncTask<Favorite, Void, Void>{ //in background
        FavDAO dao;

        public DeleteFavAsyncTask(FavDAO dao){
            this.dao = dao;
        }

        @Override
        protected Void doInBackground(Favorite... favorites){
            dao.delete(favorites[0]);
            return null;
        }
    }
}
