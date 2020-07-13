package com.example.moviesfinal;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;



public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> {


    private static final String TAG ="error" ;
    private Movie[] Movies;
    Context context;

    // Provide a suitable constructor (depends on the kind of dataset)
    public RecyclerViewAdapter(Context context,Movie[] myDataset) {
        this.Movies = myDataset;
        this.context=context;
    }
    // Create new views (invoked by the layout manager)
    @NonNull
    @Override
    public  RecyclerViewAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // create a new view

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_details,parent,false);
        // ViewHolder holder= new ViewHolder(view);
        MyViewHolder ViewHolder= new MyViewHolder(view);
        return ViewHolder;

    }
    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdapter.MyViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        Movie mov = Movies[position];

        Picasso.get().load(mov.getPoster()).into(holder.image);
        Log.d(TAG, "onBindViewHolder:picasso ");
    }
    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return Movies.length;
    }
    public class MyViewHolder extends RecyclerView.ViewHolder {
        public  ImageView image;

        public  MyViewHolder(View itemView) {
            super(itemView);
            image = (ImageView) itemView.findViewById(R.id.myImage);
        }


    }
}
