package com.example.natan.architect_pop_movie_1.viewModel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.example.natan.architect_pop_movie_1.Database.MovieRespository;
import com.example.natan.architect_pop_movie_1.model.Result;

import java.util.List;
import java.util.ResourceBundle;

/**
 * Created by natan on 3/20/2018.
 */

public class MovieViewModel extends AndroidViewModel {

    private MovieRespository mMovieRespository;

    private LiveData<List<Result>> mAllFav;


    public MovieViewModel(@NonNull Application application) {
        super(application);
        mMovieRespository = new MovieRespository(application);
        mAllFav = mMovieRespository.getAllFav();
    }


    public LiveData<List<Result>> getAllFav() {
        return mAllFav;
    }


    public void insert(Result result) {
        mMovieRespository.insert(result);
    }

}
