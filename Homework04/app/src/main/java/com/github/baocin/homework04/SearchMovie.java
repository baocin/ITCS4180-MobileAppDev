package com.github.baocin.homework04;

import android.content.Intent;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TableLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class SearchMovie extends AppCompatActivity implements View.OnClickListener {
    ArrayList<Movie> moviesList = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_movie);

        String searchTerm = getIntent().getExtras().getString("searchTerm");

        try {
            moviesList = new GetGeneralMovieData(this, (RelativeLayout) findViewById(R.id.baseLayout)).execute(searchTerm).get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        TableLayout movieScrollList = (TableLayout) findViewById(R.id.movieList);
        int id = 0;
        for (Movie movie : moviesList){
            View item = getLayoutInflater().inflate(R.layout.movie_list_item, null);
            item.setId(id);
            item.setTag(movie.getImdbID());
            ((TextView) item.findViewById(R.id.itemName)).setText(movie.getTitle() + "  (" + movie.getYear() + ")");
            item.setOnClickListener(this);
            movieScrollList.addView(item);

            id += 1;
        }
    }

    @Override
    public void onClick(View v) {
        String imdbID = (String) v.getTag();
        for (Movie m : moviesList){
            if (m.getImdbID().equals(imdbID)){
                Intent i = new Intent(SearchMovie.this, MovieDetails.class);
//                Bundle bun = new Bundle();
//                bun.putParcelableArrayList("movieList", moviesList);


                i.putExtra("movieList", moviesList);
                i.putExtra("movie", m);
                startActivity(i);
            }
        }
    }
}
