package com.github.baocin.homework04;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

/**
 * Created by aoi on 2/24/2016.
 */
public class Movie implements Serializable{
    String title;
    String type;
    String poster;
    String released;
    String genre;
    String director;
    ArrayList<String> actors;
    String plot;
    String imdbRating;
    String year;
    String imdbID;

    public Movie() {
        title = null;
        type = null;
        poster = null;
        released = null;
        genre = null;
        director = null;
        actors = null;
        plot = null;
        imdbRating = null;
        year = null;
        imdbID = null;

    }

    protected Movie(Parcel in) {
        title = in.readString();
        type = in.readString();
        poster = in.readString();
        released = in.readString();
        genre = in.readString();
        director = in.readString();
        actors = in.createStringArrayList();
        actors = (ArrayList<String>) in.readSerializable();
        plot = in.readString();
        imdbRating = in.readString();
        year = in.readString();
        imdbID = in.readString();
    }

//    public static final Creator<Movie> CREATOR = new Creator<Movie>() {
//        @Override
//        public Movie createFromParcel(Parcel in) {
//            return new Movie(in);
//        }
//
//        @Override
//        public Movie[] newArray(int size) {
//            return new Movie[size];
//        }
//    };

    @Override
    public String toString() {
        return "Movie{" +
                "title='" + title + '\'' +
                ", type='" + type + '\'' +
                ", poster='" + poster + '\'' +
                ", released=" + released +
                ", genre='" + genre + '\'' +
                ", director='" + director + '\'' +
                ", actors=" + actors +
                ", plot='" + plot + '\'' +
                ", imdbRating='" + imdbRating + '\'' +
                ", year=" + year +
                ", imdbID='" + imdbID + '\'' +
                '}';
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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

    public ArrayList<String> getActors() {
        return actors;
    }

    public void setActors(ArrayList<String> actors) {
        this.actors = actors;
    }

    public void setActors(String[] actors) {
        this.actors = new ArrayList<String>(Arrays.asList(actors));
    }

    public String getPlot() {
        return plot;
    }

    public void setPlot(String plot) {
        this.plot = plot;
    }

    public String getImdbRating() {
        return imdbRating;
    }

    public void setImdbRating(String imdbRating) {
        this.imdbRating = imdbRating;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getImdbID() {
        return imdbID;
    }

    public void setImdbID(String imdbID) {
        this.imdbID = imdbID;
    }

    public String getUrl() {
        return "http://m.imdb.com/title/" + imdbID;
    }

//    @Override
//    public int describeContents() {
//        return 0;
//    }
//
//    @Override
//    public void writeToParcel(Parcel dest, int flags) {
//        dest.writeString(title);
//        dest.writeString(type);
//        dest.writeString(poster);
//        dest.writeString(released);
//        dest.writeString(genre);
//        dest.writeString(director);
//        dest.writeSerializable(actors);
//        dest.writeString(plot);
//        dest.writeString(imdbRating);
//        dest.writeString(year);
//        dest.writeString(imdbID);
//    }


}
