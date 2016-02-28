package com.example.gbl.homework4;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.searchButton).setOnClickListener(new View.OnClickListener() {

            EditText movieTextField;
            Intent i;

            @Override
            public void onClick(View v) {

                movieTextField = (EditText) findViewById(R.id.movieTextField);

                i = new Intent(getBaseContext(), SearchMovieActivity.class);
                i.putExtra("MOVIE", movieTextField.getText().toString());
                startActivity(i);
            }

        });

    }
}
