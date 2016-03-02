package com.github.baocin.inclass07;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class TopStories extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_top_stories);

        String section = getIntent().getExtras().getString("section");

        try {
            ArrayList<Story> stories = new GetTopStoriesData(this, (RelativeLayout) findViewById(R.id.baseLayout)).execute(section).get();


            StoryAdapter adapter = new StoryAdapter(this, R.layout.storyitem, stories);
            ListView storiesLV = (ListView) findViewById(R.id.listView);
            storiesLV.setAdapter(adapter);

//            ListView lsv = (ListView) findViewById(R.id.listView);
//            Log.d("Testing", stories.toString());
//
//
//            Story[] a = stories.toArray(new Story[stories.size()]);
//            StoryAdapter sa = new StoryAdapter(getApplicationContext(), a);
//            lsv.setAdapter(sa);
            storiesLV.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Log.d("Clicked", ((TextView)view.findViewById(R.id.newsTitle)).getText() + "");
                }
            });

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}
