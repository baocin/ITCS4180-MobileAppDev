package com.example.gbl.homework5;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.gbl.homework5.Tasks.GetWeatherTask;
import com.example.gbl.homework5.Utils.City;
import com.example.gbl.homework5.Utils.Weather;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class HourlyDataActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hourly_data);

        /** Getting Weather **/
        final City city = (City) getIntent().getExtras().get("SELECTED_CITY");
        try {
            final ArrayList<Weather> weathers = new GetWeatherTask(this, city).execute().get();
            city.setHourlyWeather(weathers);

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

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }catch (NullPointerException e){
            Toast.makeText(getApplicationContext(), "No Internet Connection", Toast.LENGTH_SHORT).show();
        }


    }
}
