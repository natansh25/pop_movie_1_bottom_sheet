package com.example.natan.architect_pop_movie_1.api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by natan on 3/17/2018.
 */

public class ApiClient {

    final static String MOVIE_DB_URL = "http://api.themoviedb.org/3/";
    final static String API_KEY = "api_key";
    public static String api_key = "053130b8fdf68ca19c58155b4bd37bdd";
    private static Retrofit retrofit = null;

    public static Retrofit getClient() {
        if (retrofit==null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(MOVIE_DB_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }


}
