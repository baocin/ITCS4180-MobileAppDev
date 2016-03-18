//package com.github.baocin.combined.Async;
//
//import android.app.ProgressDialog;
//import android.content.Context;
//import android.os.AsyncTask;
//import android.util.Log;
//
//import com.github.baocin.combined.Utility.RequestParams;
//
//import org.json.JSONException;
//
//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.InputStreamReader;
//import java.net.HttpURLConnection;
//import java.net.MalformedURLException;
//import java.net.URL;
//import java.text.ParseException;
//
///**
// * Created by aoi on 3/18/16.
// */
//public class BasicAsync extends AsyncTask<String, Void, Void> {
//    ProgressDialog pd;
//    Context context;
//
//    public BasicAsync(Context context){
//        this.context = context;
//    }
//
//    @Override
//    protected void onPreExecute() {
//        super.onPreExecute();
//
//        //Make Progress Dialog
//        pd = new ProgressDialog(context);
//        pd.setCancelable(false);
//        pd.setMessage("Loading Stories");
//        pd.setIndeterminate(true);
//        pd.show();
//
//
//    }
//
//    @Override
//    protected void onPostExecute(Void aVoid) {
//        super.onPostExecute(aVoid);
//
//        //Dismiss Progress Dialog
//        pd.dismiss();
//    }
//
//    @Override
//    protected Void doInBackground(String... params) {
//
//        //Stall
//        String s = "";
//        for (int i = 0; i < 10000; i++)
//            s += " ";
//
//        //Calling Parser
//        try {
//            //Add Parameters
//            RequestParams rp = new RequestParams(apiURL + params[0] + ".json");
//            rp.addParam("api-key", apiKey);
//
//            //Connect
//            URL url = new URL(rp.getEncodedURL());
//            HttpURLConnection con = (HttpURLConnection) url.openConnection();
//            con.setRequestMethod("GET");
//            con.connect();
//
//            //Get status
//            int statusCode = con.getResponseCode();
//            Log.d("connection status", statusCode + "");
//            if (statusCode == HttpURLConnection.HTTP_OK) {
//                BufferedReader reader = new BufferedReader(new InputStreamReader(con.getInputStream()));
//                StringBuilder sb = new StringBuilder();
//                String line = reader.readLine();
//                while (line != null) {
//                    sb.append(line);
//                    line = reader.readLine();
//
//                }
//
//                //For JSON
//                return StoryUtil.StoryJSONParser.parseStories(sb.toString());
//
//                return
//            }
//        } catch (MalformedURLException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        } catch (ParseException e) {
//            e.printStackTrace();
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//
//
//        return null;
//    }
//}
