//File: GetGeneralMovieData
//Homework 04
//Group 18
//2-27-2016
//Praveenkumar Sangalad
//Michael Pedersen
//Gabriel Lima
package com.github.baocin.homework04;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
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
public class GetGeneralMovieData implements Runnable{
    private final ProgressDialog progressDialog;
    final String apiURL = "http://www.omdbapi.com/";
    private final Context context;
    private final String searchTerm;
    private final Handler handler;

    public GetGeneralMovieData(Context c, Handler handler, String s){
        context = c;
        searchTerm = s;
        this.handler = handler;

        progressDialog = new ProgressDialog(c);
        progressDialog.setCancelable(false);
        progressDialog.setMessage(context.getString(R.string.loadingDialogMessage));
        progressDialog.show();
    }



    protected ArrayList<com.github.baocin.homework04.Movie> getMovies(String s) {
        try {
            RequestParams rp = new RequestParams(apiURL);
            rp.addParam("type", "movie");
            rp.addParam("s", s);

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
    @Override
    public void run() {
        ArrayList<Movie> movies = getMovies(searchTerm);

        String s = "";
        for(int i = 0; i < 100; i++){
            for (int k = 0; k < 200; k++) {
                s += " ";
            }
        }


        Message msg = new Message();
        Bundle b =new Bundle();
        b.putSerializable("movies", movies);
        msg.setData(b);
        handler.sendMessage(msg);

        //Dismiss Dialog
        progressDialog.dismiss();
    }

}