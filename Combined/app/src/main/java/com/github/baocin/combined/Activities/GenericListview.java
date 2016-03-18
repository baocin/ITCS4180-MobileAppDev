//package com.github.baocin.combined.Activities;
//
//import android.os.Bundle;
//import android.support.v7.app.AppCompatActivity;
//import android.util.Log;
//import android.view.Menu;
//import android.view.MenuItem;
//import android.widget.ListView;
//import android.widget.RelativeLayout;
//
//import java.util.ArrayList;
//import java.util.List;
//
//public class GenericListview extends AppCompatActivity {
//    String section;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_top_stories);
//
//        section = getIntent().getExtras().getString("section");
//
//        //
//        new GetTopStoriesData(this, (RelativeLayout) findViewById(R.id.baseLayout)).execute(section);
//
//
//
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//
//        List<Story> bookmarked;
//        StoryAdapter adapter;
//
//        switch (item.getItemId()){
//            case R.id.show_bookmarks:
//                Log.d("DEBUG", "SHOW_BOOKMARKS");
//
//                //DataManager dm = new DataManager
//                //bookmarked = dm.getAllNotes(category)
//                ArrayList<Story> stories = new ArrayList<Story>();
//                stories.addAll(MainActivity.dm.getAllStories(section));
//
//                adapter = new StoryAdapter(getApplicationContext(), R.layout.storyitem, stories);
//                ListView storiesLV = (ListView) findViewById(R.id.listView);
//                storiesLV.setAdapter(adapter);
////
////                na.clear();
////                na.addAll(DatabaseManager.getInstance().getAllNews());
////                na.notifyDataSetChanged();
//
//                return true;
//
//            case R.id.clear_all_bookmarks:
//                Log.d("DEBUG", "CLEAR_BOOKMARKS");
//                // remove all itens from the category selected --> dm.removeAll(Categories)
//                return true;
//
//            default:
//                return super.onOptionsItemSelected(item);
//        }
//    }
//
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.action_bar, menu);
//        return super.onCreateOptionsMenu(menu);
//    }
//
//
//}
