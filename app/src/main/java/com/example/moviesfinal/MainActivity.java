package com.example.moviesfinal;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
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

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private Context context;

    private ImageView imageView;
  private TextView title;
    private Movie[] Movies=new Movie[0];
    private  String sortType="popular";
    private FetchData task;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Execute AsyncTask to get data from the API
        // send sort criteria (popular or top_rated)
        task=  new FetchData();
        task.execute("popular");






        imageView = (ImageView) findViewById(R.id.myImage);
        title = (TextView) findViewById(R.id.movieName);


        recyclerView = (RecyclerView) findViewById(R.id.dear_RecyclerView);

        // use a linear layout manager
        layoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(layoutManager);

        // specify an adapter (see also next example)
        mAdapter = new RecyclerViewAdapter(context,Movies);
        recyclerView.setAdapter(mAdapter);
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


            switch (menuItemThatWasSelected){
                case R.id.popular:
                    sortType="popular";
                    Toast.makeText(getApplicationContext(),"Sort By Popular",Toast.LENGTH_LONG).show();


                    // Network Call (popular)

                    return true;
                case R.id.topRated:
                    sortType="top_rated";
                    Toast.makeText(getApplicationContext(),"Sort By Rating",Toast.LENGTH_LONG).show();



            }
        task.cancel(true);
        task=new FetchData();
        task.execute(sortType);
        return super.onOptionsItemSelected(item);
        }




    //implementing image library Picasso

    public void onClick(View view) {

        Picasso.get().load("http://image.tmdb.org/t/p/w185").into(imageView);

    }


   public class FetchData extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... strings) {
            try {

                // build the proper URL
                URL url = NetworkUtils.buildUrl(strings[0]);

                // fetch movies from the API
                String result = NetworkUtils.getResponseFromHttpUrl(url);

                return result;



            }
            catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            try {
                Movie[] Movies = JsonUtils.getMovieInformationsFromJson(MainActivity.this, s);

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
