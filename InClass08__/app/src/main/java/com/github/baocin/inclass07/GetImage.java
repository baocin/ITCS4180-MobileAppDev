package com.github.baocin.inclass07;//File: com.github.baocin.inclass07.GetImage
//Homework 04
//Group 18
//2-27-2016
//Praveenkumar Sangalad
//Michael Pedersen
//Gabriel Lima

import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by aoi on 2/20/16.
 */
public class GetImage extends AsyncTask<String, Void, Bitmap> {

    private ProgressDialog pDialog;

//    public com.github.baocin.inclass07.GetImage(Quiz quiz) {
//        Log.d("Constructor", "using welcome" + quiz.getPackageName());
//        pDialog = new ProgressDialog(quiz);
//        pDialog.setMessage(quiz.getResources().getString(R.string.txtLoadingMessage));
//        pDialog.setIndeterminate(false);
//    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
//        pDialog.show();
    }

    @Override
    protected void onPostExecute(Bitmap bm) {
        super.onPostExecute(bm);
//        pDialog.hide();
    }

    @Override
    protected Bitmap doInBackground(String... params) {
        try {
//            Log.d("Image Url:", params[0]);
            URL url = new URL(params[0]);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            Bitmap bm = BitmapFactory.decodeStream(con.getInputStream());
            return bm;
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}
