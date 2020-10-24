package com.example.moviesfinal;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;


public class MovieViewModel extends ViewModel {

    private RepositoryMovie repositoryMovie;

    private LiveData<List<TaskEntry>> AllMovies;

    public MovieViewModel(@NonNull AppDatabase database) {


        repositoryMovie = new RepositoryMovie(database);
        AllMovies = repositoryMovie.getAllMovies();
    }

    public LiveData<List<TaskEntry>> getAllMovies(){
        return AllMovies;
}
    public LiveData<List<TaskEntry>> getTask() {
        return AllMovies;
    }

}
