package com.example.moviesfinal;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class storeMovieAdapter extends RecyclerView.Adapter<storeMovieAdapter.storeMovieViewHolder> {
    private List<TaskEntry> MoviesDb =new ArrayList<>();

    @NonNull
    @Override
    public storeMovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemV = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.movie_details,parent,false);
        return new storeMovieViewHolder(itemV);
    }

    @Override
    public void onBindViewHolder(@NonNull storeMovieViewHolder holder, int position) {
        TaskEntry currentMov = MoviesDb.get(position);
        holder.title.setText(currentMov.getTitle());
        holder.description.setText(currentMov.getDescription());
        holder.rating.setText(currentMov.getRate());
        holder.release.setText(currentMov.getRelease());
        holder.poster.setText(currentMov.getPoster());

    }

    @Override
    public int getItemCount() {
        return MoviesDb.size();
    }

    public void getMovieDb(List<TaskEntry> MoviesDb){
        this.MoviesDb =MoviesDb;
        notifyDataSetChanged();
    }

    class storeMovieViewHolder extends RecyclerView.ViewHolder{

        private TextView title;
        private TextView description;
        private TextView rating;
        private TextView release;
        private TextView poster;


        public storeMovieViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.Movtitle);
            description = itemView.findViewById(R.id.over);
            rating = itemView.findViewById(R.id.rating1);
            release = itemView.findViewById(R.id.relDate);
            poster = itemView.findViewById(R.id.myImage);
        }
    }
}
