package com.github.baocin.homework04;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.json.JSONException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

/**
 * Created by aoi on 2/24/2016.
 */
public class GetMovieData extends AsyncTask<String, Void, Movie> {
    private final ProgressDialog progressDialog;
    final String apiURL = "http://www.omdbapi.com/";
    private final Context context;
    private final RelativeLayout searchLayout;

    public GetMovieData(Context c, RelativeLayout rl){
        context = c;
        searchLayout = rl;
        progressDialog = new ProgressDialog(context);
        progressDialog.setCancelable(false);
        progressDialog.setMessage(context.getString(R.string.loadingDialogMessage));
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        progressDialog.show();
    }

    @Override
    protected void onPostExecute(Movie movie) {
        super.onPostExecute(movie);
//        SimpleDateFormat sdf = new SimpleDateFormat("dd mm yyyy");

        Log.d("Populating movie:", movie.toString());

        ((TextView) searchLayout.findViewById(R.id.detailTitle)).setText(movie.getTitle());
        ((TextView) searchLayout.findViewById(R.id.detailReleaseDate)).setText(movie.getReleased());
        ((TextView) searchLayout.findViewById(R.id.detailGenre)).setText(movie.getGenre());
        ((TextView) searchLayout.findViewById(R.id.detailDirector)).setText(movie.getDirector());
        ((TextView) searchLayout.findViewById(R.id.detailActors)).setText(movie.actors.toString().substring(1, movie.actors.toString().length() - 1));

        RatingBar rb = ((RatingBar) searchLayout.findViewById(R.id.detailRating));
        try{
            rb.setRating(Float.parseFloat(movie.getImdbRating()) / 2);
        }catch (NumberFormatException e){
            rb.setRating(0);
        }
        ((TextView) searchLayout.findViewById(R.id.detailPlot)).setText(movie.getPlot());
        try {
            Bitmap bm = new GetImage().execute(movie.getPoster()).get();
            ((ImageView) searchLayout.findViewById(R.id.detailPoster)).setImageBitmap(bm);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }


        progressDialog.hide();
    }

    @Override
    protected Movie doInBackground(String... params) {
        try {
            RequestParams rp = new RequestParams(apiURL);
            rp.addParam("i", params[0]);

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
                return MoviesUtil.MovieJSONParser.parseMovie(sb.toString());
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
