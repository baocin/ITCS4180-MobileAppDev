//File: CheckValidLocationTask
//Homework 05
//Group 18
//3-08-2016
//Praveenkumar Sangalad
//Michael Pedersen
//Gabriel Lima
package com.example.gbl.homework5.Tasks;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import com.example.gbl.homework5.Utils.Utils;
import com.example.gbl.homework5.Utils.Weather;

import org.xmlpull.v1.XmlPullParserException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

/**
 * Created by aoi on 3/8/16.
 */
public class CheckValidLocationTask extends AsyncTask<String, Void, Boolean> {

    @Override
    protected Boolean doInBackground(String... params) {
        String stringURL = new String("http://api.wunderground.com/api/8e406bd1b73e1a10/hourly/q/");
        String cityName = params[0].trim().replace(" ", "_");
        String stateCode = params[1].trim().toUpperCase();
        //By putting an invalid state code we can retrieve the list of all known cities by the provided name
        //API only provides a
        stringURL += stateCode + "/" + cityName + ".xml";

        Log.d("Testign URL: ", stringURL);

        try {

            URL url = new URL(stringURL);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            con.connect();

            if (con.getResponseCode() == HttpURLConnection.HTTP_OK) {

                BufferedReader reader = new BufferedReader(new InputStreamReader(con.getInputStream()));
                StringBuilder sb = new StringBuilder();
                String line = reader.readLine();
                while (line != null) {
                    sb.append(line);
                    line = reader.readLine();

                }
                String result = sb.toString();
                Log.d("Test:", result);
                if (!result.contains("<hourly_forecast>") && !result.contains("<results>") && !result.contains("<state>"+ stateCode +"</state>"))
                    return false;
                }



        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;

    }
}
