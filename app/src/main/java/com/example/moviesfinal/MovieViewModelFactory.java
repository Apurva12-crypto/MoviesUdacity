package com.example.moviesfinal;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

public class MovieViewModelFactory extends ViewModelProvider.NewInstanceFactory {

    private final AppDatabase mDb ;

    public MovieViewModelFactory(@NonNull AppDatabase mDb) {
        this.mDb = mDb;
    }

    @Override
    public <T extends ViewModel> T create(Class<T> modelClass) {
        //noinspection unchecked
        return (T) new MovieViewModel(mDb);
    }
}
