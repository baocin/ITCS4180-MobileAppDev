package com.github.baocin.inclass07;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.RelativeLayout;

import java.util.ArrayList;
import java.util.List;

public class TopStories extends AppCompatActivity {
    String section;
    StoryAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_top_stories);

        section = getIntent().getExtras().getString("section");

        new GetTopStoriesData(this, (RelativeLayout) findViewById(R.id.baseLayout), adapter).execute(section);



    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        List<Story> bookmarked;
//        StoryAdapter adapter = null;

        ArrayList<Story> stories = new ArrayList<Story>();

        switch (item.getItemId()){
            case R.id.show_bookmarks:
                Log.d("DEBUG", "SHOW_BOOKMARKS");


//                DataManager dm = new DataManager
//                bookmarked = dm.getAllNotes(category)

//                MainActivity.dm.getAllStories(section);
//
//                adapter = new StoryAdapter(getApplicationContext(), R.layout.storyitem, stories);
//                ListView storiesLV = (ListView) findViewById(R.id.listView);
//                storiesLV.setAdapter(adapter);
////
                stories = new ArrayList<Story>();
                stories.addAll(MainActivity.dm.getAllStories(section));
//                Log.d("debug", stories.toString());
                adapter = new StoryAdapter(getApplicationContext(), R.layout.storyitem, stories);
                ListView storiesLV = (ListView) findViewById(R.id.listView);
                storiesLV.setAdapter(adapter);

                if (adapter != null) {
//                    Log.d("ksldjfds", adapter.toString());

//                    adapter.clear();
//                    adapter.addAll(stories);
//                    adapter.notifyDataSetChanged();
                }


                return false;

            case R.id.clear_all_bookmarks:
                Log.d("DEBUG", "CLEAR_BOOKMARKS");
                MainActivity.dm.removeAllStories(section);
//
//                adapter = new StoryAdapter(getApplicationContext(), R.layout.storyitem, stories);
//                ListView storiesLV = (ListView) findViewById(R.id.listView);
//                storiesLV.setAdapter(adapter);

//                stories = new ArrayList<Story>();
//                stories.addAll(MainActivity.dm.getAllStories(section));
//                Log.d("debug", stories.toString());

                new GetTopStoriesData(this, (RelativeLayout) findViewById(R.id.baseLayout), adapter).execute(section);
//                adapter = new StoryAdapter(getApplicationContext(), R.layout.storyitem, stories);
//                ListView storiesLVa = (ListView) findViewById(R.id.listView);
//                storiesLVa.setAdapter(adapter);

//                if (adapter != null) {
//                    adapter.clear();
//                    adapter.addAll(stories);
//                    adapter.notifyDataSetChanged();
//                }

                // remove all itens from the category selected --> dm.removeAll(Categories)
                return false;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.action_bar, menu);
        return super.onCreateOptionsMenu(menu);
    }


}
