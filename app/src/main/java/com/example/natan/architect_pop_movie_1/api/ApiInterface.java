package com.example.natan.architect_pop_movie_1.api;

import com.example.natan.architect_pop_movie_1.model.Movie;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by natan on 3/17/2018.
 */

public interface ApiInterface {


    @GET("movie/top_rated")
    Call<Movie> getTopRatedMovies(@Query("api_key") String apiKey);

    @GET("movie/popular")
    Call<Movie> getPopularMovies(@Query("api_key") String apiKey);


}
