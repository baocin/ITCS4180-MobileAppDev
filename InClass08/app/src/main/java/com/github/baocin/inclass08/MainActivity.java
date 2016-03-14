//File: MainActivity
//InClass08
//Group 18
//3-14-2016
//Praveenkumar Sangalad
//Michael Pedersen
//Gabriel Lima
package com.github.baocin.inclass08;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.support.ConnectionSource;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    ListView topicsList;
    ArrayAdapter<String> stringArrayAdapter;
    private String TAG = "Main Activity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Let the good times roll!
        DatabaseManager.init(this);

        //Find Listview
        topicsList = (ListView) findViewById(R.id.listView);

        //Topics
        final ArrayList<String> topics = new ArrayList<>();
        topics.addAll(Arrays.asList(getResources().getStringArray(R.array.topics)));

        //Set ListView Adapter
        stringArrayAdapter = new ArrayAdapter<String>(this, R.layout.simple_row, R.id.rowTextView, topics);
        topicsList.setAdapter(stringArrayAdapter);

        topicsList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String url = "";
                String topicTitle = ((TextView)view).getText().toString();
                switch (topicTitle){
                    case "Top Stories":
                        url="http://feeds.bbci.co.uk/news/rss.xml";break;
                    case "World":
                        url="http://feeds.bbci.co.uk/news/world/rss.xml";break;
                    case "UK":
                        url="http://feeds.bbci.co.uk/news/uk/rss.xml";break;
                    case "Business":
                        url="http://feeds.bbci.co.uk/news/business/rss.xml";break;
                    case "Politics":
                        url="http://feeds.bbci.co.uk/news/politics/rss.xml";break;
                    case "Health":
                        url="http://feeds.bbci.co.uk/news/health/rss.xml";break;
                    case "Education & Family":
                        url="http://feeds.bbci.co.uk/news/education/rss.xml";break;
                    case "Science & Environment":
                        url="http://feeds.bbci.co.uk/news/science_and_environment/rss.xml";break;
                    case "Technology":
                        url="http://feeds.bbci.co.uk/news/technology/rss.xml";break;
                    case "Entertainment & Arts":
                        url="http://feeds.bbci.co.uk/news/entertainment_and_arts/rss.xml";break;
                    case "Favorites":
                        Intent i = new Intent(MainActivity.this, Favorites.class);
                        startActivity(i);
                        return;
                    default:
                        Log.d(TAG, "Something bad happened... unrecognizable topic!");
                        break;
                }

                Intent i = new Intent(MainActivity.this, NewsActivity.class);
                i.putExtra("url", url);
                startActivity(i);
            }
        });
    }
}
