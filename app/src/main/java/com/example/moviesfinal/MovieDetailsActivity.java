package com.example.moviesfinal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class MovieDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);


        //collect your intent
        Intent intent = getIntent();
        Movie mov = intent.getParcelableExtra("movie");

        //collect all the property or details value
        String title = mov.getTitle();
        String img  =mov.getPoster();
        String release =mov.getRelease();
        String rate =mov.getRate();
        String overview = mov.getOverview();
        int id =mov.getId();




    }
}