package com.example.moviesfinal;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import org.json.JSONException;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {



    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private Context context;
    private ImageView imageView;




    private String sortType = "popular";
    private FetchData task;


    private MovieViewModel movieViewModel;



    private LiveData<List<TaskEntry>> AllMovies;

    private ArrayList<TaskEntry> movies;


    List<TaskEntry> FavList = new ArrayList<>();

   //  Create AppDatabase member variable for the Database
    private AppDatabase mDb;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView s1 =findViewById(R.id.dear_RecyclerView);
        s1.setLayoutManager(new LinearLayoutManager(this));
        s1.setHasFixedSize(true);

        final storeMovieAdapter adapter = new storeMovieAdapter();
        s1.setAdapter(adapter);

        movieViewModel = ViewModelProviders.of(this).get(MovieViewModel.class);
        movieViewModel.getAllMovies().observe(this, new Observer<List<TaskEntry>>() {
            @Override
            public void onChanged(List<TaskEntry> taskEntries) {

            adapter.getMovieDb(taskEntries);
            FavList = (List<TaskEntry>) movieViewModel.getAllMovies();

            }
        });
        String Sort_Popular = "http://api.themoviedb.org/3/movie/popular?api_key=338b39a38ed5065e52e0281a6aa38361";
        String Sort_Rating = "http://api.themoviedb.org/3/movie/top_rated?api_key=338b39a38ed5065e52e0281a6aa38361";

        task = new FetchData();
        task.execute("popular");

        recyclerView =  findViewById(R.id.dear_RecyclerView);
        imageView = findViewById(R.id.myImage);

        // use a linear layout manager
        layoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(layoutManager);

        // specify an adapter (see also next example)

        RecyclerViewAdapter mAdapter = new RecyclerViewAdapter(context,movies );
                recyclerView.setAdapter(mAdapter);

        // COMPLETED (2) Initialize member variable for the data base
        mDb = AppDatabase.getInstance(getApplicationContext());
                
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int menuItemThatWasSelected = item.getItemId();

        Context context = MainActivity.this;


        switch (menuItemThatWasSelected) {
            case R.id.popular:
                sortType = "popular";
                Toast.makeText(getApplicationContext(), "Sort By Popular", Toast.LENGTH_LONG).show();
                // Network Call (popular)
                break;
//                return true;
            case R.id.topRated:
                sortType = "top_rated";
                Toast.makeText(getApplicationContext(), "Sort By Rating", Toast.LENGTH_LONG).show();
                break;
            case R.id.fav:
                sortType = "favorites";
                Toast.makeText(getApplicationContext(), "Sort By favorites", Toast.LENGTH_LONG).show();
                break;


        }
        task.cancel(true);
        task = new FetchData();
        task.execute(sortType);
        return super.onOptionsItemSelected(item);
    }
    //implementing image library Picasso

    public void onClick(View view) {
        Picasso.get().load("http://image.tmdb.org/t/p/w185/").into(imageView);
    }


    public class FetchData extends AsyncTask<String, Void, String> {
        private static final String TAG = "error";

        @Override
        protected String doInBackground(String... strings) {
            Log.d(TAG, "doInBackground: get response error");
            try {
                // build the proper URL
                URL url = NetworkUtils.buildUrl(strings[0]);
                // fetch movies from the API
                String string = NetworkUtils.getResponseFromHttpUrl(url);
                return string;
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            Log.d(TAG, "onPostExecute: error");
            super.onPostExecute(s);

            try {
               ArrayList<TaskEntry> movies;


                Movie[] Movies = JsonUtils.getMovieInformationsFromJson(MainActivity.this, s);
                new GridLayoutManager(MainActivity.this, 2);
                recyclerView.setLayoutManager(layoutManager);
                mAdapter = new RecyclerViewAdapter(MainActivity.this,movies);
                recyclerView.setAdapter(mAdapter);


            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

    }


}





