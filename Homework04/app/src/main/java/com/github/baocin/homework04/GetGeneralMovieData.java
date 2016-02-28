package com.github.baocin.homework04;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.RelativeLayout;
import android.widget.ScrollView;

import org.json.JSONException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.ParseException;
import java.util.ArrayList;

/**
 * Created by aoi on 2/24/2016.
 */
public class GetGeneralMovieData extends AsyncTask<String, Void, ArrayList<com.github.baocin.homework04.Movie>> {
    private final ProgressDialog progressDialog;
    final String apiURL = "http://www.omdbapi.com/";
    private final Context context;
    private final RelativeLayout searchLayout;

    public GetGeneralMovieData(Context c, RelativeLayout rl){
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
    protected void onPostExecute(ArrayList<Movie> movies) {
        super.onPostExecute(movies);
        progressDialog.hide();
    }

    @Override
    protected ArrayList<com.github.baocin.homework04.Movie> doInBackground(String... params) {
        try {
            RequestParams rp = new RequestParams(apiURL);
            rp.addParam("type", "movie");
            rp.addParam("s", params[0]);

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
                return MoviesUtil.MoviesJSONParser.parseMovies(sb.toString());
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
