package com.example.moviesfinal;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;


public class MovieViewModel extends AndroidViewModel {

    private RepositoryMovie repositoryMovie;

    private LiveData<List<TaskEntry>> AllMovies;

    public MovieViewModel(@NonNull AppDatabase application) {
        super(application);

        repositoryMovie = new RepositoryMovie(application);
        AllMovies = repositoryMovie.getAllMovies();
    }

    public LiveData<List<TaskEntry>> getAllMovies(){
        return AllMovies;
}
    public LiveData<List<TaskEntry>> getTask() {
        return AllMovies;
    }

}
