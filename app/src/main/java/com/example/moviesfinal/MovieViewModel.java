package com.example.moviesfinal;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;


public class MovieViewModel extends AndroidViewModel {

    private RepositoryMovie repositoryMovie;

    private LiveData<List<TaskEntry>> AllMovies;

    public MovieViewModel(@NonNull Application application) {
        super(application);
        repositoryMovie = new RepositoryMovie(application);
        AllMovies = repositoryMovie.getAllMovies();
    }

    public void insert(TaskEntry taskEntry) {
        repositoryMovie.insert(taskEntry);
    }

    public void update(TaskEntry taskEntry) {
        repositoryMovie.update(taskEntry);
    }

    public void delete(TaskEntry taskEntry) {
        repositoryMovie.delete(taskEntry);
    }

public LiveData<List<TaskEntry>> getAllMovies(){
        return AllMovies;
}

}
