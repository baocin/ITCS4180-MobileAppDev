//File: Favorites
//InClass08
//Group 18
//3-14-2016
//Praveenkumar Sangalad
//Michael Pedersen
//Gabriel Lima
package com.github.baocin.inclass08;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class Favorites extends AppCompatActivity {
    NewsAdapter na;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorites);

        final ArrayList<News> favorites = new ArrayList<>();
        favorites.addAll(DatabaseManager.getInstance().getAllNews());

        final ListView lv = (ListView) findViewById(R.id.favoritesList);
        na = new NewsAdapter(this, R.layout.news_row, favorites);
        lv.setAdapter(na);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //Delete News Item from Database
                Toast.makeText(getApplicationContext(), "Removed item from Favorites :(", Toast.LENGTH_SHORT).show();
                DatabaseManager.getInstance().removeNews(favorites.get(position));


                //Tell listview that data changed so it can update UI appropriately.
                na.clear();
                na.addAll(DatabaseManager.getInstance().getAllNews());
                na.notifyDataSetChanged();
            }
        });
    }
}
