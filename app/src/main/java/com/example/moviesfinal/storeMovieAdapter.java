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

    private static final String TAG ="storedMovieAdapterImg" ;
    private List<TaskEntry> taskEntries = new ArrayList<>();
    Context context;


    // Create new views (invoked by the layout manager)
    @NonNull
    @Override
    public storeMovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemV = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.details_saved, parent, false);
        return new storeMovieViewHolder(itemV);
    }


    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(@NonNull storeMovieViewHolder holder, int position) {

        final TaskEntry currentMov = taskEntries.get(position);




        Picasso.get().load(currentMov.getPoster()).into(holder.posterS);
        Log.d(TAG, "storedMovieAdapterImg ");

        holder.titleS.setText(currentMov.getTitle());
        holder.releaseS.setText(currentMov.getRelease());
        holder.ratingS.setText(currentMov.getRate());


        holder.descriptionS.setText(currentMov.getDescription());







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



        private ImageView posterS;
        private TextView titleS;
        private TextView descriptionS;
        private TextView ratingS;
        private TextView releaseS;




        public storeMovieViewHolder(@NonNull View itemView) {
            super(itemView);
            posterS = (ImageView) itemView.findViewById(R.id.img1);
            titleS = itemView.findViewById(R.id.Movtitle);
            releaseS = itemView.findViewById(R.id.relDate);
            ratingS = itemView.findViewById(R.id.rating1);
            descriptionS = itemView.findViewById(R.id.over);







        }
    }
}






