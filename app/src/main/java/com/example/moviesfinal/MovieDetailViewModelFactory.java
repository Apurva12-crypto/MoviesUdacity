package com.example.moviesfinal;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class MovieDetailViewModelFactory extends ViewModelProvider.NewInstanceFactory {

    // COMPLETED (2) Add two member variables. One for the database and one for the taskId
    private final AppDatabase mDb;
    private final int id;

    // COMPLETED (3) Initialize the member variables in the constructor with the parameters received
    public MovieDetailViewModelFactory(AppDatabase database, int taskId) {
        mDb = database;
        id = taskId;
    }

    // COMPLETED (4) Uncomment the following method
    @Override
    public <T extends ViewModel> T create(@NotNull Class<T> modelClass) {

        return (T) new MovieDetailViewModelFactory(mDb,id);
    }

}
