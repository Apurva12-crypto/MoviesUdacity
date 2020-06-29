package com.example.moviesfinal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ToggleButton;

public class MovieDetailsActivity extends AppCompatActivity {

    //Declare views variables
    ImageView imageView;
    TextView  title, poster, release, rate, overview;

    Context context = this;

    //Declare variables
    static String  movieID;

    //Getter for the movieID variable
    public static String getMovieID() {
        return movieID;
    }
    //Get the values from the incoming Intent





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.movie_details);
    }
}