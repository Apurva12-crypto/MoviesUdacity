package com.example.moviesfinal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class MovieDetailsActivity extends AppCompatActivity {

    ImageView posterImg;
    TextView titleTv,releaseDateTv,ratingTv,overviewTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);

        posterImg=findViewById(R.id.img1);
        titleTv=findViewById(R.id.Movtitle);
        releaseDateTv=findViewById(R.id.relDate);
        ratingTv=findViewById(R.id.rating1);
        overviewTv=findViewById(R.id.over);

        //collect your intent
        Intent intent = getIntent();
        Movie mov = intent.getParcelableExtra("movie");
        Log.d("details", mov.getPoster());

        //collect all the property or details value
        String title = mov.getTitle();
        String img  =mov.getPoster();
        String release =mov.getRelease();
        String rate =mov.getRate();
        String overview = mov.getOverview();
        int id =mov.getId();
        Picasso.get().load(mov.getPoster()).into(posterImg);
        titleTv.setText(mov.getTitle());
        ratingTv.setText(mov.getRate());
        releaseDateTv.setText(mov.getRelease());
        overviewTv.setText(mov.getOverview());




    }
}