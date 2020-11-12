package com.example.moviesfinal;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;

import android.os.Bundle;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.squareup.picasso.Picasso;

import java.util.Date;
import java.util.List;

public class MovieDetailsActivity extends AppCompatActivity {

    ImageView posterImg;
    TextView titleTv, releaseDateTv, ratingTv, overviewTv;

    Button favButton;

    private AppDatabase mDb;

    private RecyclerView recyclerView;


    //adapter for fav list of movies
    private storeMovieAdapter adapter;

    private storeMovieAdapter.storeMovieViewHolder holder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);

        //(DATA BASE RECYCLER VIEW)
        recyclerView = findViewById(R.id.dear_RecyclerView);



        final storeMovieAdapter adapter = new storeMovieAdapter();


        posterImg = findViewById(R.id.img1);
        titleTv = findViewById(R.id.Movtitle);
        releaseDateTv = findViewById(R.id.relDate);
        ratingTv = findViewById(R.id.rating1);
        overviewTv = findViewById(R.id.over);


        //collect your intent
        Intent intent = getIntent();
        Movie mov = intent.getParcelableExtra("movie");
        Log.d("details", mov.getPoster());

        //collect all the property or details value
        final String title = mov.getTitle();
        final String poster = mov.getPoster();
        final String release = mov.getRelease();
        final String rate = mov.getRate();
        final String description = mov.getOverview();
        final int id = mov.getId();
        Picasso.get().load(mov.getPoster()).into(posterImg);
        titleTv.setText(mov.getTitle());
        ratingTv.setText(mov.getRate());
        releaseDateTv.setText(mov.getRelease());
        overviewTv.setText(mov.getOverview());




        //Initialize member variable for the data base
        mDb = AppDatabase.getInstance(getApplicationContext());


        favButton = findViewById(R.id.favButton);
        //add to favorite button (clickable)

        favButton.setOnClickListener(new View.OnClickListener() {

            private static final String TAG ="is implemented" ;
            boolean clicked = true;
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: is implemented");

                if(clicked)
                {
                    clicked = false;
                    favButton.setBackgroundResource(R.drawable.ic_baseline_favorite_24);
                    final TaskEntry taskEntry = new TaskEntry(title,description,release,poster,rate,0);
                    AppExecutors.getInstance().diskIO().execute(new Runnable() {
                        @Override
                        public void run() {
                            taskEntry.getId();
                            mDb.myDao().insertTask(taskEntry);
                        }
                    });
                    Context context = getApplicationContext();
                    Toast.makeText(context,"saved to favorites",Toast.LENGTH_SHORT).show();

                }else
                {
                    clicked = true;
                    favButton.setBackgroundResource(R.drawable.ic_baseline_shadow_24);

                    final TaskEntry taskEntry = new TaskEntry(title,description,release,poster,rate,0);
                    AppExecutors.getInstance().diskIO().execute(new Runnable() {
                        @Override
                        public void run() {
                            taskEntry.getId();
                            mDb.myDao().onDeleteTask(taskEntry);
                        }
                    });
                    Context context = getApplicationContext();
                    Toast.makeText(context,"removed from favorites",Toast.LENGTH_SHORT).show();
                }
            }
        });
            }
}







