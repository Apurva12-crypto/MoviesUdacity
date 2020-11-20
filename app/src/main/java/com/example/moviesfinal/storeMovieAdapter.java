package com.example.moviesfinal;

import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class storeMovieAdapter extends RecyclerView.Adapter<storeMovieAdapter.storeMovieViewHolder> {

    private List<TaskEntry> taskEntries = new ArrayList<>();
    Context context;


    // Create new views (invoked by the layout manager)
    @NonNull
    @Override
    public storeMovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemV = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.saved_movies, parent, false);
        return new storeMovieViewHolder(itemV);
    }


    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(@NonNull storeMovieViewHolder holder, int position) {

        final TaskEntry currentMov = taskEntries.get(position);
        Picasso.get().load(currentMov.getPoster()).into(holder.poster);

        holder.title.setText(currentMov.getTitle());
        holder.release.setText(currentMov.getRelease());
        holder.rating.setText(currentMov.getRate());
        holder.description.setText(currentMov.getDescription());




    }

    @Override
    public int getItemCount() {

        return taskEntries.size();
    }

    public List<TaskEntry> getTasks() {
        return taskEntries;
    }

    /**
     * When data changes, this method updates the list of taskEntries
     * and notifies the adapter to use the new values on it
     */

    public void setTasks(List<TaskEntry> taskEntries) {
        this.taskEntries = taskEntries;
        notifyDataSetChanged();
    }

    class storeMovieViewHolder extends RecyclerView.ViewHolder {


        private TextView title;
        private TextView description;
        private TextView rating;
        private TextView release;
        private ImageView poster;


        public storeMovieViewHolder(@NonNull View itemView) {
            super(itemView);
            poster = (ImageView) itemView.findViewById(R.id.myImage);
            title = itemView.findViewById(R.id.Movtitle);
            description = itemView.findViewById(R.id.over);
            rating = itemView.findViewById(R.id.rating1);
            release = itemView.findViewById(R.id.relDate);




        }
    }
}






