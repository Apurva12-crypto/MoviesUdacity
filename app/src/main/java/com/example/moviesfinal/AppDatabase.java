package com.example.moviesfinal;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.TabHost;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = {TaskEntry.class}, version = 1, exportSchema = false)

public abstract class AppDatabase extends RoomDatabase {
    private static final String LOG_TAG = AppDatabase.class.getSimpleName();
    private static final Object LOCK = new Object();
    private static final String DATABASE_NAME = "TheMovieDatabase";
    private static AppDatabase sInstance;

    public abstract MyDao myDao();

    public static AppDatabase getInstance(Context context) {
        if (sInstance == null) {
            synchronized (LOCK) {
                Log.d(LOG_TAG, "Creating new database instance");
                sInstance = Room.databaseBuilder(context.getApplicationContext(),
                        AppDatabase.class, AppDatabase.DATABASE_NAME)
                        // Queries should be done in a separate thread to avoid locking the UI
                        // We will allow this ONLY TEMPORALLY to see that our DB is working
                        .fallbackToDestructiveMigration()
                        .addCallback(roomCallBack)
                        .build();
            }
        }
        Log.d(LOG_TAG, "Getting the database instance");
        return sInstance;
    }

    private static RoomDatabase.Callback roomCallBack = new RoomDatabase.Callback() {
        @Override
        public void onOpen(@NonNull SupportSQLiteDatabase db) {
            super.onOpen(db);
            new populatedbAsyncTask(sInstance).execute();
        }
    };



//populate database in the background
    private static class populatedbAsyncTask extends AsyncTask<Void,Void,Void>{

        private  MyDao myDao;



        private populatedbAsyncTask(AppDatabase appDatabase){
            myDao=appDatabase.myDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            // Start the app with a clean database every time.
            // Not needed if you only populate the database
            // when it is first created
            myDao.deleteAll();

            return null;
        }
    }
}