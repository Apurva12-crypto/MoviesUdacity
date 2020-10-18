package com.example.moviesfinal;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

public class RepositoryMovie {
    private MyDao myDao;
    private LiveData<List<TaskEntry>> AllMovies;

    public RepositoryMovie(AppDatabase application){
       AppDatabase database = AppDatabase.getInstance(application);
myDao = database.myDao();
AllMovies = myDao.getAllMovies();
    }
    public void insert(TaskEntry taskEntry){
new InsertAsyncTask(myDao).execute(taskEntry);
    }

   public void delete(TaskEntry taskEntry){
        new deleteAsyncTask(myDao).execute(taskEntry);

   }
   public void update(TaskEntry taskEntry){
       new updateAsyncTask(myDao).execute(taskEntry);

   }


   public LiveData<List<TaskEntry>>getAllMovies(){
        return AllMovies;
   }

   public static class InsertAsyncTask extends AsyncTask<TaskEntry,Void,Void>{
private MyDao myDao;

private InsertAsyncTask(MyDao myDao){
    this.myDao = myDao;
}
       @Override
       protected Void doInBackground(TaskEntry... taskEntries) {
    myDao.insertTask(taskEntries[0]);
           return null;
       }
   }

    public static class deleteAsyncTask extends AsyncTask<TaskEntry,Void,Void>{
        private MyDao myDao;

        private deleteAsyncTask(MyDao myDao){
            this.myDao = myDao;
        }
        @Override
        protected Void doInBackground(TaskEntry... taskEntries) {
            myDao.onDeleteTask(taskEntries[0]);
            return null;
        }
    }

    public static class updateAsyncTask extends AsyncTask<TaskEntry,Void,Void>{
        private MyDao myDao;

        private updateAsyncTask(MyDao myDao){
            this.myDao = myDao;
        }
        @Override
        protected Void doInBackground(TaskEntry... taskEntries) {
            myDao.updateTask(taskEntries[0]);
            return null;
        }
    }




}
