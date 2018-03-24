package com.example.natan.architect_pop_movie_1.Database;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import com.example.natan.architect_pop_movie_1.model.Movie;
import com.example.natan.architect_pop_movie_1.model.Result;

import java.util.List;

/**
 * Created by natan on 3/20/2018.
 */

public class MovieRespository {

    // refrence to dao

    private MovieDao mMovieDao;

    private LiveData<List<Result>> mAllFav;

    public MovieRespository(Application application) {

        MovieRoomDatabase db = MovieRoomDatabase.getDatabase(application);
        mMovieDao = db.movieDao();
        mAllFav = mMovieDao.getAllFav();
    }


    public LiveData<List<Result>> getAllFav() {
        return mAllFav;
    }

    public void insert(Result result) {
        new insertAsyncTask(mMovieDao).execute(result);
    }


    public class insertAsyncTask extends AsyncTask<Result, Void, Void> {

        private MovieDao mAsyncTaskDao;

        public insertAsyncTask(MovieDao asyncTaskDao) {
            mAsyncTaskDao = asyncTaskDao;
        }

        @Override
        protected Void doInBackground(Result... results) {
            mAsyncTaskDao.insert(results[0]);
            return null;
        }
    }


}
