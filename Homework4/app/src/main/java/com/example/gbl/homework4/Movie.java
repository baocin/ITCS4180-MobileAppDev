package com.example.gbl.homework4;

import android.os.Parcel;
import android.os.Parcelable;

import java.net.URL;

/**
 * Created by gbl on 2/26/2016.
 */
public class Movie implements Comparable<Movie>, Parcelable {

    private String title;
    private int year;
    private String imdbID;
    private String poster;
    private String released;
    private String genre;
    private String director;
    private String actors;
    private String plot;
    private int imdbRating;


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getImdbID() {
        return imdbID;
    }

    public void setImdbID(String imdbID) {
        this.imdbID = imdbID;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public String getReleased() {
        return released;
    }

    public void setReleased(String released) {
        this.released = released;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getActors() {
        return actors;
    }

    public void setActors(String actors) {
        this.actors = actors;
    }

    public String getPlot() {
        return plot;
    }

    public void setPlot(String plot) {
        this.plot = plot;
    }

    public int getImdbRating() {
        return imdbRating;
    }

    public void setImdbRating(int imdbRating) {
        this.imdbRating = imdbRating;
    }

    @Override
    public int compareTo(Movie another) {
        return another.year - this.year;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

        dest.writeString(title);
        dest.writeInt(year);
        dest.writeString(imdbID);
        dest.writeString(poster);
        dest.writeString(released);
        dest.writeString(genre);
        dest.writeString(director);
        dest.writeString(actors);
        dest.writeString(plot);
        dest.writeInt(imdbRating);
    }

    public static final Parcelable.Creator<Movie> CREATOR = new Parcelable.Creator<Movie>() {
        public Movie createFromParcel(Parcel in) {
            return new Movie(in);
        }

        public Movie[] newArray(int size) {
            return new Movie[size];
        }
    };

    Movie(){
        super();
    }

    private Movie(Parcel in) {

        this.title = in.readString();
        this.year = in.readInt();
        this.imdbID = in.readString();
        this.poster = in.readString();
        this.released = in.readString();
        this.genre = in.readString();
        this.director = in.readString();
        this.actors = in.readString();
        this.plot = in.readString();
        this.imdbRating = in.readInt();

    }

}
