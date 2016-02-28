package com.example.gbl.homework4;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.LinearLayout;

public class SearchMovieActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_movie);

        new GetMovieListTask(this, (LinearLayout) findViewById(R.id.moviesLayout)).execute("http://www.omdbapi.com/", getIntent().getExtras().getString("MOVIE"));

    }
}
