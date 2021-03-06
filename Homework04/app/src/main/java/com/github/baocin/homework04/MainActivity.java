//File: MainActivity
//Homework 04
//Group 18
//2-27-2016
//Praveenkumar Sangalad
//Michael Pedersen
//Gabriel Lima
package com.github.baocin.homework04;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button submitButton = (Button) findViewById(R.id.btnSubmit);
        submitButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.btnSubmit:
                String searchTerm = ((TextView) findViewById(R.id.inSearchTerm)).getText().toString();
                if (searchTerm.length() >= 1) {
                    Intent i = new Intent(MainActivity.this, SearchMovie.class);
                    i.putExtra("searchTerm", searchTerm);
                    startActivity(i);
                }else{
                    Toast.makeText(getApplicationContext(), "Please type in a query!", Toast.LENGTH_SHORT).show();
                }
                break;

        }
    }
}
