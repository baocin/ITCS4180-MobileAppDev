//File: DetailsActivity
//Homework 05
//Group 18
//3-08-2016
//Praveenkumar Sangalad
//Michael Pedersen
//Gabriel Lima
package com.example.gbl.homework5;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.gbl.homework5.Utils.City;
import com.example.gbl.homework5.Utils.Weather;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class DetailsActivity extends AppCompatActivity {
    int weatherIndex = 0;
    ArrayList<Weather> weathers;
    City city;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        city = (City) getIntent().getExtras().get("city");
        weathers = city.getHourlyWeather();

        int defaultWeatherPosition = (int) getIntent().getExtras().get("weatherPosition");
        weatherIndex = defaultWeatherPosition % weathers.size();
        Log.d("city", city.toString());
        Log.d("weathers", weathers.toString());

        update(city, weathers.get(weatherIndex));


        findViewById(R.id.detailNext).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                weatherIndex++;
                weatherIndex %= weathers.size();
                update(city, weathers.get(weatherIndex));
            }
        });

        findViewById(R.id.detailPrevious).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                weatherIndex--;
                if (weatherIndex < 0) weatherIndex = weathers.size()-1;
                update(city, weathers.get(weatherIndex));
            }
        });
    }

    public void update(City c, Weather w){

        ((TextView) findViewById(R.id.currentLocation)).setText("Current Location: " + c.getName().replace("_", " ") + ", " + c.getState().toUpperCase() + "  (" + w.getTime() + ")");
        Picasso.with(this).load(w.getIconUrl()).into((ImageView) findViewById(R.id.detailImage));
        ((TextView) findViewById(R.id.detailTemperature)).setText(w.getTemperature() + "°F");
        ((TextView) findViewById(R.id.detailCloudStatus)).setText(w.getClimateType());
        ((TextView) findViewById(R.id.detailMaxTemperature)).setText("Max Temperature:  " + c.getMaxTemp() + " Fahrenheit");
        ((TextView) findViewById(R.id.detailMinTemperature)).setText("Min Temperature:  " + c.getMinTemp() + " Fahrenheit" );
        ((TextView) findViewById(R.id.detailFeelsLikeOutput)).setText(w.getFeelsLike() + " Fahrenheit");
        ((TextView) findViewById(R.id.detailHumidityOutput)).setText(w.getHumidity() + "%");
        ((TextView) findViewById(R.id.detailDewpointOutput)).setText(w.getDewpoint() + " Fahrenheit");
        ((TextView) findViewById(R.id.detailPressureOutput)).setText(w.getPressure() + " hPa");
        ((TextView) findViewById(R.id.detailCloudsOutput)).setText(w.getClouds());
        ((TextView) findViewById(R.id.detailWindOutput)).setText(w.getWindSpeed() + "mph, " + " " + w.getWindDirection());

    }
}
