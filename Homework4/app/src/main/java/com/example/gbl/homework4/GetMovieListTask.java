package com.example.gbl.homework4;

import android.app.ActionBar;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by gbl on 2/26/2016.
 */
public class GetMovieListTask extends AsyncTask<String, Void, ArrayList<Movie>> {

    Context context;
    LinearLayout searchLayout;

    URL imdbAPI;
    httpParametersBuilder builder;
    HttpURLConnection connection;
    BufferedReader reader;
    StringBuilder buffer;
    String line="";
    ProgressDialog progress;
    Button movieButton;

    GetMovieListTask(Context c, LinearLayout ll) {
        context = c;
        searchLayout = ll;
    }


    @Override
    protected void onPreExecute() {
        super.onPreExecute();

        buffer = new StringBuilder();

        progress = new ProgressDialog(context);
        progress.setMessage("Loading Movie List");
        progress.setCancelable(false);
        progress.setProgressStyle(ProgressDialog.STYLE_SPINNER);
    }

    @Override
    protected ArrayList<Movie> doInBackground(String... params) {

        ArrayList<Movie> moviesList = new ArrayList<>();

        try {

            builder = new httpParametersBuilder(params[0]);
            builder.addParameters("type", "movie");
            builder.addParameters("s", params[1]);

            imdbAPI = new URL(builder.getParametersURL());
            Log.d("TEST", imdbAPI.toString());

            connection = (HttpURLConnection) imdbAPI.openConnection();
            connection.setRequestMethod("GET");

            reader  = new BufferedReader(new InputStreamReader(connection.getInputStream()));

            while ((line = reader.readLine()) != null) {
                buffer.append(line);
            }

            reader.close();

            MoviesJSONParser(buffer.toString(), moviesList);

            Collections.sort(moviesList);

            for (Movie aux : moviesList) {
                Log.d("TEST (Title)", aux.getTitle());
                Log.d("TEST (ID)", aux.getImdbID());
                Log.d("TEST (URL)", aux.getPoster().toString());
                Log.d("TEST (Year)", Integer.toString(aux.getYear()));
            }


        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return moviesList;
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);

        progress.show();
    }

    @Override
    protected void onPostExecute(ArrayList<Movie> movies) {

        super.onPostExecute(movies);

        for (Movie m : movies) {

            movieButton = new CustomButton(context.getApplicationContext(), m);
            searchLayout.addView(movieButton);
        }

        progress.dismiss();
    }

    protected void MoviesJSONParser(String in, ArrayList<Movie> moviesList) {

        JSONObject root, rootAux;
        JSONArray movieJSONarray;
        Movie mov;
        String aux;

        try {

            root = new JSONObject(in);
            movieJSONarray = root.getJSONArray("Search");

            for (int i = 0; i < movieJSONarray.length(); i++) {

                rootAux = movieJSONarray.getJSONObject(i);

                mov = new Movie();
                mov.setImdbID(rootAux.getString("imdbID"));
                mov.setPoster(rootAux.getString("Poster"));

                aux = rootAux.getString("Year");
                mov.setYear(Integer.parseInt(aux));

                mov.setTitle(rootAux.getString("Title") + " (" + aux + ")");

                moviesList.add(mov);
            }


        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

}
