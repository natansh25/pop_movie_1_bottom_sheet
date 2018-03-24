package com.example.natan.architect_pop_movie_1.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.natan.architect_pop_movie_1.R;
import com.example.natan.architect_pop_movie_1.model.Result;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by natan on 3/17/2018.
 */

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MyViewHolder> {

    private List<Result> mResults;

    private RecyclerViewClickListener mListener;

    public MovieAdapter(List<Result> results, RecyclerViewClickListener clickListener) {
        mResults = results;
        mListener = clickListener;
    }

    public interface RecyclerViewClickListener {

        //if we want to on click the item index value
        //void onClick(View view, int position);

        //if we want the whole object to retrive the items
        void onClick(Result result);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.custom_layout, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        Result result = mResults.get(position);
        Context context = holder.img.getContext();
        holder.txt_name.setText(result.getOriginalTitle());
        Picasso.with(context).load("https://image.tmdb.org/t/p/w500" + result.getPosterPath()).into(holder.img);


    }

    @Override
    public int getItemCount() {
        return mResults.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private ImageView img;
        private TextView txt_name;

        public MyViewHolder(View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.imageView);
            txt_name = itemView.findViewById(R.id.txt_movie_name);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {

            int adapterPosition = getAdapterPosition();
            Result result = mResults.get(adapterPosition);
            mListener.onClick(result);

        }
    }

}
