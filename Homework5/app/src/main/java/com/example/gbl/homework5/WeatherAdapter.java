//File: WeatherAdapter
//Homework 05
//Group 18
//3-08-2016
//Praveenkumar Sangalad
//Michael Pedersen
//Gabriel Lima
package com.example.gbl.homework5;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.gbl.homework5.Utils.Weather;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by aoi on 3/8/16.
 */
public class WeatherAdapter extends ArrayAdapter<Weather> {
    Context c;
    ArrayList<Weather> weatherItems;

    public WeatherAdapter(Context context, int resource, ArrayList<Weather> objects) {
        super(context, resource, objects);
        c = context;
        weatherItems = objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater;
        ViewHolder holder;

        if (convertView == null) {
            inflater = (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.weatheritem, parent, false);

            holder = new ViewHolder();
            holder.image = (ImageView) convertView.findViewById(R.id.itemImage);
            holder.time = (TextView) convertView.findViewById(R.id.itemTime);
            holder.status = (TextView) convertView.findViewById(R.id.itemStatus);
            holder.temp = (TextView) convertView.findViewById(R.id.itemTemp);
            convertView.setTag(holder);
        }

        holder = (ViewHolder) convertView.getTag();

        holder.status.setText(weatherItems.get(position).getClouds());
        holder.time.setText(weatherItems.get(position).getTime());
        holder.temp.setText(weatherItems.get(position).getTemperature() + "°F");

        //Show indicator for where the images were retrieved from - dark blue is disk, green is memory.
//        Picasso.with(c).setIndicatorsEnabled(true);
        Picasso.with(c).load(weatherItems.get(position).getIconUrl()).into(holder.image);

        return convertView;
    }

    public WeatherAdapter(Context context, int resource, List<Weather> objects) {
        super(context, resource, objects);
    }

    static class ViewHolder {
        ImageView image;
        TextView time;
        TextView status;
        TextView temp;
    }
}
