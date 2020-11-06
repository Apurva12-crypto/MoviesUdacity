package com.example.moviesfinal;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

public class MovieDetailsViewModelFactory extends ViewModelProvider.NewInstanceFactory {

    private final AppDatabase mDb;
    private final int id;

    public MovieDetailsViewModelFactory(AppDatabase database, int taskId) {
        mDb = database;
       id = taskId;

    }
    // COMPLETED (4) Uncomment the following method
    @Override
    public <T extends ViewModel> T create(Class<T> modelClass) {
        //noinspection unchecked
        return (T) new MovieDetailsViewModel(mDb, id);
    }
}
