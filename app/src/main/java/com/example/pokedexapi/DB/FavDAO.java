package com.example.pokedexapi.DB;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface FavDAO {

    @Query("SELECT * FROM favorite ORDER BY UPPER(name) DESC")
    LiveData<List<Favorite>> getAllFavs();

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Favorite... favorites); //add new favorite to db

    @Update
    void update(Favorite favorite); //This is not used

    @Update
    void update(Favorite... favorites); //allows update to all rows

    @Delete
    void delete(Favorite favorite); //delete favorite from db

    @Query("DELETE FROM favorite WHERE id = :id") //this is not used
    void delete(int id);
}
