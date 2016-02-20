//package com.github.baocin.homework03;
//
//import android.os.AsyncTask;
//import android.util.Log;
//
//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.InputStreamReader;
//import java.net.HttpURLConnection;
//import java.net.MalformedURLException;
//import java.net.ProtocolException;
//import java.net.URL;
//
///**
// * Created by aoi on 2/20/16.
// */
//
//public class GetQuizQuestion extends AsyncTask<Integer, Void, Question> {
//    final String apiURL = "http://dev.theappsdr.com/apis/spring_2016/hw3/index.php";
//
//    public GetQuizQuestion(){
//
//    }
//
//    @Override
//    protected Question doInBackground(Integer... params) {
//        try {
//            StringBuilder sb = new StringBuilder();
//
//            //Encode the question id
//            RequestParams rp = new RequestParams(apiURL);
//            rp.addParam("id", params[0].toString());
//            Log.d("request params", rp.toString());
//            Log.d("encoded url", rp.getEncodedURL());
//
//            URL url = new URL(rp.getEncodedURL());
//            HttpURLConnection con = (HttpURLConnection) url.openConnection();
//            con.setRequestMethod("GET");
//            BufferedReader reader = new BufferedReader(new InputStreamReader(con.getInputStream()));
//            String line = "";
//            while((line = reader.readLine()) != null){
//                sb.append(line);
//            }
//            reader.close();
//
//            String rawQuiz = sb.toString();
//            Log.d("rawQuiz", rawQuiz);
////            ArrayList<String> split = ArrayList<String>();
//            Question question = new Question();
//            String[] split = rawQuiz.split(";");
//
//            //Populate new question object
//            question.setQid(Integer.parseInt(split[0]));
//            question.setQuestionText(split[1]);
//            if (split.length % 2 == 0){   //Odd number of fields means image Url is specified
//                question.setImageURL(split[split.length-1]);
//            }
//
//            int numOptions = ((split.length/2) - 1);
//            Log.d("numOptions", numOptions + "");
//
//            for (int i = 0;i<numOptions*2;i+=2){
//                question.setOption(split[2 + i], Integer.parseInt(split[2 + i + 1]));
//            }
//
//            Log.d("Generated Question", question.toString());
//            return question;
//        } catch (MalformedURLException e) {
//            e.printStackTrace();
//        } catch (ProtocolException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        return null;
//    }
//
//    @Override
//    protected void onPreExecute() {
//        super.onPreExecute();
//    }
//
//}
