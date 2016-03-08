//File: AddCityActivity
//Homework 05
//Group 18
//3-08-2016
//Praveenkumar Sangalad
//Michael Pedersen
//Gabriel Lima

package com.example.gbl.homework5;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.gbl.homework5.Utils.City;

public class AddCityActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_city);

        findViewById(R.id.addCityButton).setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                String cityName = ((EditText) findViewById(R.id.cityNameField)).getText().toString();
                String cityState = ((EditText) findViewById(R.id.stateInitialField)).getText().toString();


                if (City.isValidLocation(cityName, cityState)){

                    Log.d("DEBUG", "ISVALID");

                    City addedCity = new City(cityName, cityState);

                    Intent i = new Intent();
                    i.putExtra("ADDED_CITY", addedCity);
                    setResult(Activity.RESULT_OK, i);
                    finish();


                } else {
                    Toast.makeText(getApplicationContext(), "The city is not a valid one", Toast.LENGTH_SHORT).show();
                }
            }

        });
    }
}
