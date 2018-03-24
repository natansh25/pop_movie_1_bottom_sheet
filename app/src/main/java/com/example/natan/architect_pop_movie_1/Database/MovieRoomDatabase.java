package com.example.natan.architect_pop_movie_1.Database;

import android.arch.persistence.db.SupportSQLiteOpenHelper;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.DatabaseConfiguration;
import android.arch.persistence.room.InvalidationTracker;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.example.natan.architect_pop_movie_1.model.Result;

/**
 * Created by natan on 3/20/2018.
 */


@Database(entities = {Result.class}, version = 1)
public abstract class MovieRoomDatabase extends RoomDatabase {

    private static MovieRoomDatabase INSTANCE;

    public abstract MovieDao movieDao();

    public static MovieRoomDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (MovieRoomDatabase.class) {
                if (INSTANCE == null) {
                    // Create database here
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            MovieRoomDatabase.class, "movie_database")
                            .fallbackToDestructiveMigration()
                            .build();
                }
            }
        }
        return INSTANCE;
    }


}
