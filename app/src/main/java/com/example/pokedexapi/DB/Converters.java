package com.example.pokedexapi.DB;


import androidx.room.TypeConverter;

import java.util.Date;

public class Converters { //converts the date to a long and a long to a date
    @TypeConverter
    public static Date dateFromTimestamp(long time){
        return new Date(time);
    }

    @TypeConverter
    public static long dateToTimestamp(Date date){
        return date == null ? 0 : date.getTime();
    }
}
