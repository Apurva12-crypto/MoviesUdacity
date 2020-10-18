package com.example.moviesfinal;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
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


    private static final String TAG ="Actively retrieving the tasks from the DataBase" ;
    private RecyclerView recyclerView;

    //adapter for top rated and highest rated
    private RecyclerView.Adapter mAdapter;

    //adapter for fav list of movies
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private Context context;
    private ImageView imageView;
    private Context con;

    private ViewModel movieViewModel;

    private LiveData<List<TaskEntry>> AllMovies;




    private String sortType = "popular";
    private FetchData task;

    // create an empty  ArrayList String type ro fetch the movies by json response
    private ArrayList<Movie> Movies = new ArrayList<>();

    //create an empty lIst to fetch all fav movies from room database
    private ArrayList<TaskEntry>taskEntries = new ArrayList<>();

    //  Create AppDatabase member variable for the Database
    private AppDatabase mDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //(DATA BASE RECYCLER VIEW)
         recyclerView = findViewById(R.id.dear_RecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        storeMovieAdapter adapter = new storeMovieAdapter();
        recyclerView.setAdapter(adapter);

        String Sort_Popular = "http://api.themoviedb.org/3/movie/popular?api_key=338b39a38ed5065e52e0281a6aa38361";
        String Sort_Rating = "http://api.themoviedb.org/3/movie/top_rated?api_key=338b39a38ed5065e52e0281a6aa38361";

        task = new FetchData();
        task.execute("popular");

        recyclerView = findViewById(R.id.dear_RecyclerView);
        imageView = findViewById(R.id.myImage);

        // use a linear layout manager
        layoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(layoutManager);

        // specify an adapter (see also next example)

        RecyclerViewAdapter mAdapter = new RecyclerViewAdapter(context, Movies);
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

    @SuppressLint("LongLogTag")
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
                Log.d(TAG, "Actively retrieving a specific task from the DataBase");




                mDb = AppDatabase.getInstance(getApplicationContext());
                retrieveTasks();
                Toast.makeText(getApplicationContext(), " favorites", Toast.LENGTH_LONG).show();
                break;


        }
        task.cancel(true);
        task = new FetchData();
        task.execute(sortType);
        return super.onOptionsItemSelected(item);
    }

    @SuppressLint("LongLogTag")
    private void retrieveTasks() {
        Log.d(TAG, "onCreate: Actively retrieving the tasks from the DataBase");

        //(DATA BASE RECYCLER VIEW)
        recyclerView = findViewById(R.id.dear_RecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        final storeMovieAdapter adapter = new storeMovieAdapter();
        recyclerView.setAdapter(adapter);

        // init the view model and get all movies from the DB
        movieViewModel =ViewModelProviders.of(this).get(MovieViewModel.class);

        // Observe changes in the DB

        ((MovieViewModel) movieViewModel).getAllMovies().observe(this, new Observer<List<TaskEntry>>() {
            @Override
            public void onChanged(List<TaskEntry> taskEntries) {
                Log.d(TAG, "Updating list of tasks from LiveData in ViewModel");
                // taskEnteries is the updated list
                // pass it to the adapter
                adapter.setTasks(taskEntries);
            }
        });
    }

    /**
     * This method is called after this activity has been paused or restarted.
     * Often, this is after new data has been inserted through an AddTaskActivity,
     * so this re-queries the database data for any changes.
     */

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
                ArrayList<Movie> Movies = (ArrayList<Movie>) JsonUtils.getMovieInformationsFromJson(MainActivity.this,s);

                new GridLayoutManager(MainActivity.this, 2);
                recyclerView.setLayoutManager(layoutManager);
                mAdapter = new RecyclerViewAdapter(MainActivity.this, Movies);
                recyclerView.setAdapter(mAdapter);


            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
}





