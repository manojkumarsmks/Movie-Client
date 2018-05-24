package com.moviesuggestions.manojkumar.moviesuggestions;

import android.content.Context;
import android.graphics.Movie;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.BounceInterpolator;
import android.view.animation.ScaleAnimation;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import static com.bumptech.glide.request.RequestOptions.centerCropTransform;
import com.bumptech.glide.Glide;

import java.util.List;

public class RecycleAdapter extends RecyclerView.Adapter<RecycleAdapter.MyViewHolder> {

    private Context context;
    List<Movies> movieList;

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
        Glide.with(context)
                .load(movieList.get(position).getThumbnail())
                .into(holder.movie_tumbnail);
        holder.like_button.setChecked(false);

       // holder.movie_tumbnail.setImageResource(movieList.get(position).getThumbnail());
    }

    @Override
    public int getItemCount() {
        return movieList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView movie_title;
        ImageView movie_tumbnail;
        CardView card_view;
        ToggleButton like_button;

        public MyViewHolder(View itemView) {
            super(itemView);

            movie_title = (TextView)itemView.findViewById(R.id.movie_title);
            movie_tumbnail = (ImageView)itemView.findViewById(R.id.image);
            card_view = (CardView)itemView.findViewById(R.id.card_view);
            like_button = (ToggleButton)itemView.findViewById(R.id.button_favorite);

            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {
            onItemClick(v, getAdapterPosition());
        }

        private void onItemClick(View v, int adapterPosition) {

            if(like_button.isChecked()) {
                like_button.setChecked(false);
            }
            else if (!like_button.isChecked()) {
                like_button.setChecked(true);
            }

            Log.d(MainActivity.TAG, getItem(adapterPosition).getMovie());
        }

        private Movies getItem(int adapterPosition) {
            return movieList.get(adapterPosition);
        }
    }
}
