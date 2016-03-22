package com.github.baocin.inclass07;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by aoi on 2/29/2016.
 */
public class StoryAdapter extends ArrayAdapter<Story> {
    Context c;
    ArrayList<Story> stories;


    public StoryAdapter(Context context, int resource, ArrayList<Story> objects) {
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
            convertView = inflater.inflate(R.layout.storyitem, parent, false);

            holder = new ViewHolder();
            holder.thumb = (ImageView) convertView.findViewById(R.id.storiesThumbnail);
            holder.bookmark = (ImageView) convertView.findViewById(R.id.bookmark);
            holder.title = (TextView) convertView.findViewById(R.id.newsTitle);
            holder.date = (TextView) convertView.findViewById(R.id.newsDate);
            convertView.setTag(holder);

        }

        holder = (ViewHolder) convertView.getTag();

        ImageView tb = holder.thumb;
        ImageView bm = holder.bookmark;
        TextView title = holder.title;
        TextView date = holder.date;

        title.setText(stories.get(position).getTitle());


        Story isInDatabase = MainActivity.dm.getStory(stories.get(position).getAbstractString());

        if (isInDatabase != null && stories.get(position).getTitle().equals(isInDatabase.getTitle())) {
            Log.d("isindatabase", isInDatabase + "");

            bm.setImageResource(R.drawable.bookmark_filled);
        }


        date.setText(stories.get(position).getCreated_date());


//        Log.d("img", stories.get(position).getThumb_image_url() + "");
        if (stories.get(position).getThumb_image_url() == null){
//            Picasso.with(c).load(R.drawable.none).into(tb);
            Picasso.with(c).load(stories.get(position).getNormal_image_url()).into(tb);
        }else {
            Picasso.with(c).load(stories.get(position).getThumb_image_url()).into(tb);
        }

        return convertView;
    }


    static class ViewHolder {
        ImageView thumb;
        TextView title;
        TextView date;
        ImageView bookmark;
    }
}