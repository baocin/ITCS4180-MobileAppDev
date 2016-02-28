package com.example.gbl.homework4;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class MovieDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);

        Log.d("TEST2", getIntent().getExtras().getParcelable("SELECTED_MOVIE").toString());
    }
}
