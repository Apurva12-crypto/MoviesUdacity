package com.example.moviesfinal;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;


public class MovieViewModel extends AndroidViewModel {

    private static final String TAG =MovieViewModel.class.getSimpleName(); ;
    private RepositoryMovie repositoryMovie;

    private LiveData<List<TaskEntry>> AllMovies;


    public MovieViewModel(@NonNull Application application) {
        super(application);
        AppDatabase database = AppDatabase.getInstance(this.getApplication());
        Log.d(TAG, "Actively retrieving the tasks from the DataBase");
        AllMovies = database.myDao().getAllMovies();

    }
    public LiveData<List<TaskEntry>> getTasks() {
        return AllMovies;
    }


}
