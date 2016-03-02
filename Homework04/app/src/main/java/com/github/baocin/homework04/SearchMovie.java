//File: SearchMovie
//Homework 04
//Group 18
//2-27-2016
//Praveenkumar Sangalad
//Michael Pedersen
//Gabriel Lima
package com.github.baocin.homework04;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TableLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.concurrent.ExecutionException;
import java.util.logging.LogRecord;

public class SearchMovie extends AppCompatActivity implements View.OnClickListener {
    ArrayList<Movie> moviesList = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_movie);

        String searchTerm = getIntent().getExtras().getString("searchTerm");




        Handler handler = new android.os.Handler(){
            @Override
            public void handleMessage(Message msg) {
                Bundle b = msg.getData();
                moviesList = (ArrayList<Movie>) b.getSerializable("movies");

                //Populate UI
                TableLayout movieScrollList = (TableLayout) findViewById(R.id.movieList);
                int id = 0;
                Collections.sort(moviesList);
                for (Movie movie : moviesList){
                    LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                    View item = inflater.inflate(R.layout.movie_list_item, null);
                    item.setId(id);
                    item.setTag(movie.getImdbID());
                    ((TextView) item.findViewById(R.id.itemName)).setText(movie.getTitle() + "  (" + movie.getYear() + ")");
                    item.setOnClickListener(SearchMovie.this);
                    movieScrollList.addView(item);

                    id += 1;
                }
            }
        };

        Thread thread = new Thread(new GetGeneralMovieData(this, handler, searchTerm));
        thread.start();



    }

    @Override
    public void onClick(View v) {
        String imdbID = (String) v.getTag();
        for (Movie m : moviesList){
            if (m.getImdbID().equals(imdbID)){
                Intent i = new Intent(SearchMovie.this, MovieDetails.class);
                i.putExtra("movieList", moviesList);
                i.putExtra("movie", m);
                startActivity(i);
            }
        }
    }
}
