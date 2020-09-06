package com.example.moviesfinal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.squareup.picasso.Picasso;

public class MovieDetailsActivity extends AppCompatActivity {

    ImageView posterImg;
    TextView titleTv,releaseDateTv,ratingTv,overviewTv;

Button favButton;

private AppDatabase mDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);

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
        int id = mov.getId();
        Picasso.get().load(mov.getPoster()).into(posterImg);
        titleTv.setText(mov.getTitle());
        ratingTv.setText(mov.getRate());
        releaseDateTv.setText(mov.getRelease());
        overviewTv.setText(mov.getOverview());

        //Initialize member variable for the data base
        mDb = AppDatabase.getInstance(getApplicationContext());
        favButton = findViewById(R.id.favButton);

        favButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                TaskEntry taskEntry = new TaskEntry(title,description,release,poster,rate);


                mDb.myDao().insertTask(taskEntry);

                favButton.setBackgroundResource(R.drawable.ic_baseline_favorite_24);
                Context context = getApplicationContext();
                Toast.makeText(context,"saved to fav",Toast.LENGTH_SHORT).show();



            }
        });



    }





    }
