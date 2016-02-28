package com.github.baocin.homework04;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.*;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class MovieDetails extends AppCompatActivity {
    private int currentIndex = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);

//        Bundle movieListBundle = (Bundle) getIntent().getExtras().get("movieList");
        final ArrayList<Movie> movieList = (ArrayList<Movie>) getIntent().getExtras().get("movieList");
        final Movie movie = (Movie) getIntent().getExtras().get("movie");

        Log.d("Passed MovieList:", movieList.toString());
        Log.d("Selected movie:", movie.toString());

        //Default display selected Movie
        new GetMovieData(this, (RelativeLayout) findViewById(R.id.baseLayout)).execute(movie.getImdbID());

        findViewById(R.id.detailLeftButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentIndex = currentIndex - 1;
                if (currentIndex < 0)
                    currentIndex = movieList.size() - 1;
                new GetMovieData(MovieDetails.this, (RelativeLayout) findViewById(R.id.baseLayout)).execute(movieList.get(currentIndex).getImdbID());

            }
        });

        findViewById(R.id.detailRightButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentIndex = currentIndex + 1;
                currentIndex %= movieList.size();
                new GetMovieData(MovieDetails.this, (RelativeLayout) findViewById(R.id.baseLayout)).execute(movieList.get(currentIndex).getImdbID());

            }
        });

        findViewById(R.id.detailFinishButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        ((ImageView)findViewById(R.id.detailPoster)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MovieDetails.this, WebView.class);
                i.putExtra("url", movieList.get(currentIndex).getUrl());
                startActivity(i);
            }
        });



    }


}
