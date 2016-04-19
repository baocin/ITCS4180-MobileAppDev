package com.github.baocin.inclass07;

import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.concurrent.ExecutionException;

public class DetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        Story story = (Story) getIntent().getExtras().get("story");
        Bitmap thumbnail = null;
        Bitmap normal = null;
        try {
            thumbnail = new GetImage().execute(story.getThumb_image_url()).get();
            normal = new GetImage().execute(story.getNormal_image_url()).get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        ((TextView) findViewById(R.id.textViewStoryTitle)).setText(story.getTitle());
        ((TextView) findViewById(R.id.textViewData)).setText(story.getAbstractString());
        ((TextView) findViewById(R.id.textViewStoryByLine)).setText(story.getByline());
        ((ImageView) findViewById(R.id.imageView)).setImageBitmap(normal);

        Story isInDatabase = MainActivity.dm.getStory(story.getAbstractString());
        if (isInDatabase != null) {
            ((ImageView) findViewById(R.id.bookmarkStatus)).setImageResource(R.drawable.bookmark_filled);
        }else{
            ((ImageView) findViewById(R.id.bookmarkStatus)).setImageResource(R.drawable.bookmark_empty);
        }

    }
}
