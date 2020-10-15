package com.example.moviesfinal;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

public class retrieveDataViewModel extends ViewModel {

    private LiveData<List<TaskEntry>> task;

    public retrieveDataViewModel(AppDatabase database){
        task = database.myDao().getAllMovies();

    }
    public LiveData<List<TaskEntry>> getTask() {
        return task;
    }
}
