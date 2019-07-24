package com.example.movie.adapters;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.movie.DetailActivity;
import com.example.movie.R;
import com.example.movie.models.Movie;

import org.parceler.Parcels;

import java.util.List;

public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.ViewHolder> {

    Context context;
    List<Movie> movies;

    public MoviesAdapter(Context context, List<Movie> movies) {
        this.context = context;
        this.movies = movies;
    }



    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        Log.d("smile", "onCreateViewHolder");
        View view = LayoutInflater.from(context).inflate(R.layout.item_movie,parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MoviesAdapter.ViewHolder holder, int position) {
        Log.d("smile", "onBindViewHolder : " + position);
       Movie movie= movies.get(position);
       //bind the movie data into the View holder
        holder.bind(movie); 

    }

    @Override
    public int getItemCount() {
        return movies.size();
    }



    class ViewHolder extends RecyclerView.ViewHolder {


        TextView tvTitle;
        TextView tvOverview;
        ImageView ivPoster;
        ConstraintLayout container;


        public ViewHolder( View itemView) {
            super(itemView);
          tvTitle=  itemView.findViewById(R.id.tvTitle);
          tvOverview = itemView.findViewById(R.id.tvOverview);
          ivPoster = itemView.findViewById(R.id.ivposter);
          container=itemView.findViewById(R.id.container);
        }

        public void bind(final Movie movie) {
          tvTitle.setText(movie.getTitle());
          tvOverview.setText(movie.getOverview());
          //reference the backdrop path if phone is in landscape

            String imageUrl = movie.getPosterPath();
            if (context.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE){
                  imageUrl =movie.getBackdropPath();
            }
            Glide.with(context).load(imageUrl).into(ivPoster);

            tvTitle.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //Navigate to detail activity on tap
                    Intent intent = new Intent(context, DetailActivity.class);
                    intent.putExtra("movie", Parcels.wrap(movie));
                    context.startActivity(intent);
                }
            });

            //add click listener on the whole row




        }
    }
}
