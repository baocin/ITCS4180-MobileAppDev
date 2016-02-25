package com.github.baocin.homework04;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by aoi on 2/24/2016.
 */
public class MoviesUtil {
    static public class MoviesJSONParser {
        static ArrayList<Movie>  parseMovies(String in) throws JSONException, ParseException {
            ArrayList<Movie> movieList = new ArrayList<Movie>();
            JSONObject root = new JSONObject(in);
            JSONArray movieJSONArray = root.getJSONArray("Search");

            for (int i = 0; i < movieJSONArray.length(); i++){
                JSONObject movieJSONObject = movieJSONArray.getJSONObject(i);
                Movie movie = new Movie();
                movie.setTitle(movieJSONObject.getString("Title"));
                movie.setYear(new SimpleDateFormat("yyyy").parse(movieJSONObject.getString("Year")));
                movie.setImdbID(movieJSONObject.getString("imdbID"));
                movie.setType(movieJSONObject.getString("Type"));
                movie.setPoster(movieJSONObject.getString("Poster"));

                movieList.add(movie);
            }

            return movieList;
        }
    }
}
