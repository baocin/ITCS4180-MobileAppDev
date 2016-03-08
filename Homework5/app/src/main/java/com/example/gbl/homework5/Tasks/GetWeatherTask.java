package com.example.gbl.homework5.Tasks;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.gbl.homework5.R;
import com.example.gbl.homework5.Utils.City;
import com.example.gbl.homework5.Utils.Utils;
import com.example.gbl.homework5.Utils.Weather;
import com.example.gbl.homework5.WeatherAdapter;

import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by gbl on 3/3/2016.
 */

public class GetWeatherTask extends AsyncTask<String, Void, ArrayList<Weather>> {

    private City city;

    private Context context;
    private ProgressDialog progressDialog;

    public GetWeatherTask(Context c, City city){
        context = c;
        this.city = city;
        progressDialog = new ProgressDialog(context);

    }


    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        progressDialog.setCancelable(false);
        progressDialog.setMessage(context.getString(R.string.loadingWeather));
        progressDialog.show();

    }

    @Override
    protected ArrayList<Weather> doInBackground(String... params) {

        /** API Parameters **/
        ArrayList<Weather> hourlyWeather = null;
        StringBuilder stringURL = new StringBuilder("http://api.wunderground.com/api/8e406bd1b73e1a10/hourly/q/");

        stringURL.append(city.getState() + "/");
        stringURL.append(city.getName() + ".xml");

        Log.d("CITY", city.getName());
        Log.d("STATE", city.getState());

        /** Making Connection **/
        try {

            URL url = new URL(stringURL.toString());
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            con.connect();

            if (con.getResponseCode() == HttpURLConnection.HTTP_OK) {

                hourlyWeather = Utils.HourlyXMLParser.parseWeather(con.getInputStream());

                for (Weather w: hourlyWeather) {
                    Log.d("HOURLY_WEATHER", w.toString());
                }

            } else {
                Toast.makeText(context.getApplicationContext(), "No Internet Connection", Toast.LENGTH_SHORT).show();
            }


        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        }

        return hourlyWeather;
    }


    @Override
    protected void onPostExecute(ArrayList<Weather> weathers) {

        progressDialog.dismiss();
        super.onPostExecute(weathers);
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }
}
