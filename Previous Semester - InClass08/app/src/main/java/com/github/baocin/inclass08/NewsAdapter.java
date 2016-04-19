//File: NewsAdapter
//InClass08
//Group 18
//3-14-2016
//Praveenkumar Sangalad
//Michael Pedersen
//Gabriel Lima
package com.github.baocin.inclass08;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by aoi on 3/14/16.
 */
public class NewsAdapter extends ArrayAdapter<News> {
    Context c;
    ArrayList<News> stories;


    public NewsAdapter(Context context, int resource, ArrayList<News> objects) {
        super(context, resource, objects);

        c = context;
        stories = objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater;
        ViewHolder holder;

        if (convertView == null) {

            inflater = (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.news_row, parent, false);

            holder = new ViewHolder();
            holder.thumb = (ImageView) convertView.findViewById(R.id.img);
            holder.title = (TextView) convertView.findViewById(R.id.title);
            convertView.setTag(holder);

        }

        holder = (ViewHolder) convertView.getTag();

        ImageView tb = holder.thumb;
        TextView title = holder.title;

        Picasso.with(c).load(stories.get(position).getThumbnail()).into(tb);
        title.setText(stories.get(position).getStoryTitle());

        return convertView;
    }


    static class ViewHolder {
        ImageView thumb;
        TextView title;
    }
}
