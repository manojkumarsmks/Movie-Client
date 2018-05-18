package com.moviesuggestions.manojkumar.moviesuggestions;

import android.content.Context;
import android.graphics.Movie;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class RecycleAdapter extends RecyclerView.Adapter<RecycleAdapter.MyViewHolder> {

    private Context context;
    private List<Movies> movieList;

    public RecycleAdapter(Context context, List<Movies> movieList) {
        this.context = context;
        this.movieList = movieList;
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.card_view, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        holder.movie_title.setText(movieList.get(position).getMovie());
       // holder.movie_tumbnail.setImageResource(movieList.get(position).getThumbnail());
    }

    @Override
    public int getItemCount() {
        return movieList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView movie_title;
        ImageView movie_tumbnail;
        public MyViewHolder(View itemView) {
            super(itemView);

            movie_title = (TextView)itemView.findViewById(R.id.movie_title);
            movie_tumbnail = (ImageView)itemView.findViewById(R.id.image);

        }
    }
}
