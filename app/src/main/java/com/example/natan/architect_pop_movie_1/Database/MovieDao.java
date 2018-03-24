package com.example.natan.architect_pop_movie_1.Database;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.example.natan.architect_pop_movie_1.model.Result;

import java.util.List;

/**
 * Created by natan on 3/20/2018.
 */

@Dao
public interface MovieDao {


    @Insert
    void insert(Result result);

    @Delete
    void delete(Result result);

    @Query("SELECT * from movie_table")
    LiveData<List<Result>> getAllFav();


}
