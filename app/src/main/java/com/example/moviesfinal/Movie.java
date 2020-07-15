package com.example.moviesfinal;

import android.os.Parcel;
import android.os.Parcelable;

public class Movie implements Parcelable {
    private String title, poster, release, rate, overview;
    private int id;


    //constructor
    public Movie(){
    }

    public Movie(String title, String poster, String release, String rate, String overview,int id){
        this.title = title;
        this.poster = poster;
        this.release = release;
        this.rate = rate;
        this.overview = overview;
        this.id=id;

    }

    protected Movie(Parcel in) {
        title = in.readString();
        poster = in.readString();
        release = in.readString();
        rate = in.readString();
        overview = in.readString();
        id = in.readInt();
    }

    public static final Creator<Movie> CREATOR = new Creator<Movie>() {
        @Override
        public Movie createFromParcel(Parcel in) {
            return new Movie(in);
        }

        @Override
        public Movie[] newArray(int size) {
            return new Movie[size];
        }
    };

    //generate getters and setters
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public  String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }


    public String getRelease() {
        return release;
    }

    public void setRelease(String release) {
        this.release = release;
    }

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }
    public int getId(){
        return id;
    }
    public void setId(int id){
        this.id= id;
    }
    public void get(int position) {
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(title);
        dest.writeString(poster);
        dest.writeString(release);
        dest.writeString(rate);
        dest.writeString(overview);
        dest.writeInt(id);
    }
}
