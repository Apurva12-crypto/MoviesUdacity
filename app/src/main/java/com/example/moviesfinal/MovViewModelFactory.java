package com.example.moviesfinal;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import org.jetbrains.annotations.NotNull;

public class MovViewModelFactory extends ViewModelProvider.NewInstanceFactory {

    private final AppDatabase mDb;
    private final int id;


    public MovViewModelFactory(AppDatabase mDb, int id) {
        this.mDb = mDb;
        this.id = id;
    }  @Override
    public <T extends ViewModel> T create(@NotNull Class<T> modelClass) {
        //noinspection unchecked
        return (T) new MovViewModelFactory(mDb,id);
    }
}