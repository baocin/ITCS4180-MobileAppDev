package com.github.baocin.inclass07;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.RelativeLayout;

import java.util.concurrent.ExecutionException;

public class TopStories extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_top_stories);

        String section = getIntent().getExtras().getString("section");

        try {
            new GetTopStoriesData(this, (RelativeLayout) findViewById(R.id.baseLayout)).execute(section).get();

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}
