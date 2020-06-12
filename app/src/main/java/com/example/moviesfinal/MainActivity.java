package com.example.moviesfinal;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private Context context;

    private ImageView imageView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageView = (ImageView)findViewById(R.id.myImage);

        // Execute AsyncTask to get data from the API
        // send sort criteria (popular or top_rated)
        new FetchData().execute("popular");
        recyclerView = (RecyclerView) findViewById(R.id.dear_RecyclerView);

        // use a linear layout manager
        layoutManager = new GridLayoutManager(this,3);
        recyclerView.setLayoutManager(layoutManager);

        // specify an adapter (see also next example)
        mAdapter = new RecyclerViewAdapter(myDataset);
        recyclerView.setAdapter(mAdapter);
    }
    //implementing image library Picasso
    @Override
    public void onClick(View view) {

        Picasso.get().load("http://image.tmdb.org/t/p/w185").into(imageView);

    }


    class FetchData extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... strings) {
            try {

                // build the proper URL
                URL url = NetworkUtils.buildUrl(strings[0]);

                // fetch movies from the API
                String result = NetworkUtils.getResponseFromHttpUrl(url);

            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }
    }


    }
