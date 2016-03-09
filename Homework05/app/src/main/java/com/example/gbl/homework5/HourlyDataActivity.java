//File: HourlyDataActivity
//Homework 05
//Group 18
//3-08-2016
//Praveenkumar Sangalad
//Michael Pedersen
//Gabriel Lima
package com.example.gbl.homework5;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.gbl.homework5.Tasks.GetWeatherTask;
import com.example.gbl.homework5.Utils.City;
import com.example.gbl.homework5.Utils.Weather;

import java.util.ArrayList;
import java.util.Collections;
import java.util.concurrent.ExecutionException;

public class HourlyDataActivity extends AppCompatActivity {
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hourly_data);

        /** Getting Weather **/
        final City city = (City) getIntent().getExtras().get("SELECTED_CITY");
        progressDialog = new ProgressDialog(this);
        progressDialog.setCancelable(false);
        progressDialog.setMessage(getString(R.string.loadingWeather));
        progressDialog.show();

        Handler handler = new android.os.Handler(){
            @Override
            public void handleMessage(Message msg) {
                Bundle b = msg.getData();
                final ArrayList<Weather> weathers = (ArrayList<Weather>) b.getSerializable("weathers");
                city.setHourlyWeather(weathers);

                //Populate UI
                ((TextView) findViewById(R.id.currentLocation)).setText("Current Location: " + city.getName().replace("_", " ") + ", " + city.getState().toUpperCase());

                WeatherAdapter adapter = new WeatherAdapter(getApplicationContext(), R.layout.weatheritem, weathers);
                ListView weatherList = (ListView) findViewById(R.id.hourList);
                weatherList.setAdapter(adapter);

                weatherList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                Log.d("Clicked", ((TextView) view.findViewById(R.id.newsTitle)).getText() + "");
                        Intent i = new Intent(getApplicationContext(), DetailsActivity.class);
                        i.putExtra("city", city);
                        i.putExtra("weatherPosition", position);
                        startActivity(i);
                    }
                });


                progressDialog.dismiss();
            }
        };

        Thread thread = new Thread(new GetWeatherTask(this, city, handler));
        thread.start();

    }
}
