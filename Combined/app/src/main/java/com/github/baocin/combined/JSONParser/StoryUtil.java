//package com.github.baocin.combined.JSONParser;
//
//import android.util.Log;
//
//import org.json.JSONArray;
//import org.json.JSONException;
//import org.json.JSONObject;
//
//import java.text.ParseException;
//import java.text.SimpleDateFormat;
//import java.util.ArrayList;
//
///**
// * Created by aoi on 2/29/2016.
// */
//public class StoryUtil {
//   static public class StoryJSONParser {
//       static ArrayList<Story> parseStories(String in) throws JSONException, ParseException {
//           ArrayList<Story> storyList = new ArrayList<Story>();
//           JSONObject root = new JSONObject(in);
//           String category = root.getString("section");
//           Log.d("Getting Cat", category + "");
//
//           JSONArray storyJSONArray = root.getJSONArray("results");
////           Log.d("num stories", storyJSONArray.length()+"");
//           for (int i = 0; i < storyJSONArray.length(); i++){
//               JSONObject storyJSONObject = storyJSONArray.getJSONObject(i);
//               Story story = new Story();
//               story.setTitle(storyJSONObject.getString("title"));
//               story.setAbstractString(storyJSONObject.getString("abstract"));
//               story.setByline(storyJSONObject.getString("byline"));
//               SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZZZZZ");
//               SimpleDateFormat ssdf = new SimpleDateFormat("MM'/'dd");
//               story.setCreated_date(ssdf.format((sdf.parse(storyJSONObject.getString("created_date")))));
//               story.setCategory(category);
//               try {
//                   JSONArray multimedia = storyJSONObject.getJSONArray("multimedia");
//
//                   String thumbURL = multimedia.getJSONObject(0).getString("url");
//                   String normalURL = multimedia.getJSONObject(2).getString("url");
//
//                   story.setThumb_image_url(thumbURL);
//                   story.setNormal_image_url(normalURL);
//               }catch (Exception e){
//                   Log.d("yeah, fix this.", "NOW");
//               }
//
//
//
//               storyList.add(story);
//           }
//
//           Log.d("storyList", storyList.toString());
//           return storyList;
//       }
//   }
//}
