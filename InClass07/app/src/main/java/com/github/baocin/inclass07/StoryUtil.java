package com.github.baocin.inclass07;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by aoi on 2/29/2016.
 */
public class StoryUtil {
   static public class StoryJSONParser {
       static ArrayList<Story> parseStories(String in) throws JSONException, ParseException {
           ArrayList<Story> storyList = new ArrayList<Story>();
           JSONObject root = new JSONObject(in);
           JSONArray storyJSONArray = root.getJSONArray("results");
//           Log.d("num stories", storyJSONArray.length()+"");
           for (int i = 0; i < storyJSONArray.length(); i++){
               JSONObject storyJSONObject = storyJSONArray.getJSONObject(i);
               Story story = new Story();
               story.setTitle(storyJSONObject.getString("title"));
               story.setAbstractString(storyJSONObject.getString("abstract"));
               story.setByline(storyJSONObject.getString("byline"));
               SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZZZZZ");
//               Log.d("parse this date!", storyJSONObject.getString("created_date"));

               story.setCreated_date((sdf.parse(storyJSONObject.getString("created_date"))));
//               Log.d("parsed date", (sdf.parse(storyJSONObject.getString("created_date"))).toString());
//                story.setCreated_date(new Date());

//               JSONObject newRoot = new JSONObject(storyJSONObject.toString());
               try {
//                   Log.d("Test", storyJSONObject.getJSONArray("multimedia").toString());
                   JSONArray multimedia = storyJSONObject.getJSONArray("multimedia");
//               Log.d("Test2", multimedia + "");
//               Log.d("Test3", multimedia.getJSONObject(0).toString());
//               storyJSONArray.getJSONObject();
//               JSONArray multimedia = storyJSONObject.getJSONArray("multimedia");

                   String thumbURL = multimedia.getJSONObject(0).getString("url");
                   String normalURL = multimedia.getJSONObject(2).getString("url");

                   story.setThumb_image_url(thumbURL);
                   story.setNormal_image_url(normalURL);
               }catch (Exception e){
                   Log.d("yeah, fix this.", "NOW");
               }



               storyList.add(story);
           }

           Log.d("storyList", storyList.toString());
           return storyList;
       }
   }
}
