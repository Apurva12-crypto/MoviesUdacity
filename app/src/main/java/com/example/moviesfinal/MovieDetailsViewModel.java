package com.example.moviesfinal;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

public class MovieDetailsViewModel extends ViewModel {

    // Add a task member variable for the TaskEntry object wrapped in a LiveData
    private LiveData<TaskEntry> obj;

    //  Create a constructor where you call loadTaskById of the taskDao to initialize the tasks variable
    // Note: The constructor should receive the database and the taskId
    public MovieDetailsViewModel(AppDatabase database, int id) {
        obj = database.myDao().loadTaskById(id);
    }
    // Create a getter for the task variable
    public LiveData<TaskEntry> getTask() {
        return obj;
    }
}
