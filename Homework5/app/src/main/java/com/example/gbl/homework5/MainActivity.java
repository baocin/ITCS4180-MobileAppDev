package com.example.gbl.homework5;

import android.content.Intent;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.gbl.homework5.Utils.City;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    final static int RESULT_CITY = 42;

    private ArrayList<City> cities;
    private ArrayAdapter<City> cityArrayAdapter;

    private ListView citiesLV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cities              = new ArrayList<>();

//        cities.add(new City("Charlotte", "NC"));

        cityArrayAdapter    = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, android.R.id.text1, cities);
        cityArrayAdapter.setNotifyOnChange(true);

        citiesLV = (ListView) findViewById(R.id.citesListView);
        citiesLV.setAdapter(cityArrayAdapter);
        citiesLV.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

                City removedCity = (City) citiesLV.getItemAtPosition(position);

                cityArrayAdapter.remove(removedCity);
                cityArrayAdapter.notifyDataSetChanged();

                Toast.makeText(getApplicationContext(), "Removed: " + removedCity.toString(), Toast.LENGTH_SHORT).show();

                return false;
            }
        });

        citiesLV.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent i = new Intent(MainActivity.this, HourlyDataActivity.class);
                i.putExtra("SELECTED_CITY", (City) citiesLV.getItemAtPosition(position));
                startActivity(i);
            }
        });

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.addCityButton:
                Intent i = new Intent(MainActivity.this, AddCityActivity.class);
                startActivityForResult(i, MainActivity.RESULT_CITY);
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == MainActivity.RESULT_CITY && resultCode == RESULT_OK) {

            cityArrayAdapter.add((City) data.getExtras().get("ADDED_CITY"));
            cityArrayAdapter.notifyDataSetChanged();

            if (!cities.isEmpty()) {
                findViewById(R.id.noCitiesTextView).setVisibility(View.INVISIBLE);
            }
            else {
                findViewById(R.id.noCitiesTextView).setVisibility(View.VISIBLE);
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.actionbar, menu);
        return super.onCreateOptionsMenu(menu);
    }
}
