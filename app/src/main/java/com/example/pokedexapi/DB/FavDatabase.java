package com.example.pokedexapi.DB;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

@Database(entities = {Favorite.class}, version = 1, exportSchema = false)
@TypeConverters({Converters.class})
public abstract class FavDatabase extends RoomDatabase { //stores favorites in a room database


    public static volatile FavDatabase INSTANCE;

    public abstract FavDAO favDAO();


    static FavDatabase getDatabase(final Context context){
        if (INSTANCE == null){
            synchronized (FavDatabase.class){
                if (INSTANCE == null){
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            FavDatabase.class, "favorites").build(); //table named "favorites"
                }
            }
        }
        return INSTANCE;
    }
}
