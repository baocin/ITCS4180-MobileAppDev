package com.github.baocin.inclass07;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

/**
 * Created by aoi on 2/29/2016.
 */
public class StoryUtil {
   static public class StoryJSONParser {
       static ArrayList<Story> parseStories(String in) throws JSONException, ParseException {
           ArrayList<Story> storyList = new ArrayList<Story>();
           JSONObject root = new JSONObject(in);
           JSONArray storyJSONArray = root.getJSONArray("results");

           for (int i = 0; i < storyJSONArray.length(); i++){
               JSONObject storyJSONObject = storyJSONArray.getJSONObject(i);
               Story story = new Story();
               story.setTitle(storyJSONObject.getString("title"));
               story.setAbstractString(storyJSONObject.getString("abstract"));
               story.setByline(storyJSONObject.getString("byline"));
               SimpleDateFormat sdf = new SimpleDateFormat("yyy-MM-ddTHH:mm:ss:ZZZZZ");
               story.setCreated_date((sdf.parse(storyJSONObject.getString("created_date"))));

               JSONArray multimedia = storyJSONObject.getJSONArray("multimedia");

               story.setThumb_image_url(multimedia.getJSONObject(0).getString("url"));

               story.setNormal_image_url(multimedia.getJSONObject(2).getString("url"));

               storyList.add(story);
           }

           return storyList;
       }
   }
}
