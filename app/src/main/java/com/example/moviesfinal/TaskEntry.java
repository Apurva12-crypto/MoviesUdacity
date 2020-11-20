package com.example.moviesfinal;

import android.net.Uri;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "Movies_Table")
public class TaskEntry  {
    private String  title;
    private String description;
    private String release;
    private String poster;
    private String rate;

    @PrimaryKey(autoGenerate = true)
    private int id;

    public TaskEntry(String title, String description, String release, String poster, String rate, int id) {

        this.title = title;
        this.description = description;
        this.release = release;
        this.poster = poster;
        this.rate = rate;
        this.id = id;
    }



    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getRelease() {
        return release;
    }

    public  String getPoster() {
        return poster;
    }

    public String getRate() {
        return rate;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


}
