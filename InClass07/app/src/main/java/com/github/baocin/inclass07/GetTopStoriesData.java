//File: GetGeneralMovieData
//Homework 04
//Group 18
//2-27-2016
//Praveenkumar Sangalad
//Michael Pedersen
//Gabriel Lima
package com.github.baocin.inclass07;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TableLayout;
import android.widget.TextView;

import org.json.JSONException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by aoi on 2/24/2016.
 */
public class GetTopStoriesData extends AsyncTask<String, Void, ArrayList<Story>> {
    private final ProgressDialog progressDialog;
    final String apiURL = "http://api.nytimes.com/svc/topstories/v1/";
    final String apiKey = "144002450f6f2c23673be6692276975d:7:74582505";
    private final TopStories context;
    private final RelativeLayout searchLayout;
    ArrayList<Story> stories = new ArrayList<Story>();

    public GetTopStoriesData(TopStories c, RelativeLayout rl){
        context = c;
        searchLayout = rl;
        progressDialog = new ProgressDialog(context);
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Loading Stories");
    }
    
    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        progressDialog.show();
    }

    @Override
    protected void onPostExecute(ArrayList<Story> story) {
        super.onPostExecute(stories);
//        TableLayout movieScrollList = (TableLayout) searchLayout.findViewById(R.id.movieList);
//        int id = 0;
//        Collections.sort(movies);
//        for (Movie movie : movies){
//            LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//            View item = inflater.inflate(R.layout.movie_list_item, null);
//            item.setId(id);
//            item.setTag(movie.getImdbID());
//            ((TextView) item.findViewById(R.id.itemName)).setText(movie.getTitle() + "  (" + movie.getYear() + ")");
//            item.setOnClickListener(context);
//            movieScrollList.addView(item);
//
//            id += 1;
//        }

        progressDialog.hide();
    }

    @Override
    protected ArrayList<Story> doInBackground(String... params) {
        try {
            RequestParams rp = new RequestParams(apiURL);
            rp.addParam("response-format", "json");
            rp.addParam("api-key", apiKey);
            rp.addParam("section", params[0]);

            URL url = new URL(rp.getEncodedURL());
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            con.connect();
            int statusCode = con.getResponseCode();
            if (statusCode == HttpURLConnection.HTTP_OK){
                BufferedReader reader = new BufferedReader(new InputStreamReader(con.getInputStream()));
                StringBuilder sb = new StringBuilder();
                String line = reader.readLine();
                while(line != null){
                    sb.append(line);
                    line = reader.readLine();

                }
//                Log.d("demo", sb.toString());
                return StoryUtil.StoryJSONParser.parseStories(sb.toString());
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;

    }
}
