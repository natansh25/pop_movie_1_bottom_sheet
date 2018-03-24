package com.example.natan.architect_pop_movie_1;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.BottomSheetBehavior;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.natan.architect_pop_movie_1.adapter.MovieAdapter;
import com.example.natan.architect_pop_movie_1.api.ApiClient;
import com.example.natan.architect_pop_movie_1.api.ApiInterface;
import com.example.natan.architect_pop_movie_1.model.Movie;
import com.example.natan.architect_pop_movie_1.model.Result;
import com.facebook.stetho.Stetho;
import com.squareup.picasso.Picasso;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private MovieAdapter mMovieAdapter;

    //-------------Testing bottom sheets----------------

    private TextView txt_Title;
    private TextView txt_Plot;
    private TextView txt_Rating;
    private TextView txt_Release;
    private ImageView img_Poster;

    BottomSheetBehavior sheetBehavior;

    ConstraintLayout mConstraintLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRecyclerView = findViewById(R.id.recyclerView);
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(MainActivity.this, 2);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        Stetho.initializeWithDefaults(this);
        loadHighest();

        //------------------------Testing bottom sheets------------

        txt_Title = findViewById(R.id.title);
        img_Poster = findViewById(R.id.image_poster);
        txt_Plot = findViewById(R.id.plot);
        txt_Rating = findViewById(R.id.rating);
        txt_Release = findViewById(R.id.release);
        mConstraintLayout=findViewById(R.id.bottom_sheet);
        sheetBehavior = BottomSheetBehavior.from(mConstraintLayout);


        /*sheetBehavior.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            @Override
            public void onStateChanged(@NonNull View bottomSheet, int newState) {

            }

            @Override
            public void onSlide(@NonNull View bottomSheet, float slideOffset) {

            }
        });*/


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_top) {
            loadTop();
        } else {
            loadHighest();
        }
        return super.onOptionsItemSelected(item);
    }

    private void loadHighest() {

        ApiInterface apiService =
                ApiClient.getClient().create(ApiInterface.class);

        Call<Movie> call = apiService.getPopularMovies(ApiClient.api_key);
        call.enqueue(new Callback<Movie>() {
            @Override
            public void onResponse(Call<Movie> call, final Response<Movie> response) {
                int statusCode = response.code();
                List<Result> results = response.body().getResults();
                mMovieAdapter = new MovieAdapter(results, new MovieAdapter.RecyclerViewClickListener() {
                    @Override
                    public void onClick(Result result) {
                        /*Intent intent = new Intent(MainActivity.this, DetailActivity.class);
                        intent.putExtra("data", result);
                        startActivity(intent);*/
                        Toast.makeText(MainActivity.this, String.valueOf(result.getTitle()), Toast.LENGTH_SHORT).show();

                        //-----------------Testing bottom sheet---------------------

                        txt_Title.setText(result.getTitle());
                        txt_Plot.setText(result.getOverview());
                        txt_Rating.setText(result.getVoteAverage() + "/10");
                        txt_Release.setText(result.getReleaseDate());
                        Picasso.with(img_Poster.getContext()).load("https://image.tmdb.org/t/p/w500" + result.getPosterPath()).into(img_Poster);
                        sheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);

                    }
                });
                mRecyclerView.setAdapter(mMovieAdapter);
                mMovieAdapter.notifyDataSetChanged();


            }

            @Override
            public void onFailure(Call<Movie> call, Throwable t) {

            }
        });


    }

    private void loadTop() {

        ApiInterface apiService =
                ApiClient.getClient().create(ApiInterface.class);

        Call<Movie> call = apiService.getTopRatedMovies(ApiClient.api_key);
        call.enqueue(new Callback<Movie>() {
            @Override
            public void onResponse(Call<Movie> call, Response<Movie> response) {
                int statusCode = response.code();
                List<Result> results = response.body().getResults();
                mMovieAdapter = new MovieAdapter(results, new MovieAdapter.RecyclerViewClickListener() {
                    @Override
                    public void onClick(Result result) {
                        Intent intent = new Intent(MainActivity.this, DetailActivity.class);
                        intent.putExtra("data", result);
                        startActivity(intent);
                        Toast.makeText(MainActivity.this, String.valueOf(result.getTitle()), Toast.LENGTH_SHORT).show();

                    }
                });
                mRecyclerView.setAdapter(mMovieAdapter);
                mMovieAdapter.notifyDataSetChanged();


            }

            @Override
            public void onFailure(Call<Movie> call, Throwable t) {

            }
        });
    }

}
