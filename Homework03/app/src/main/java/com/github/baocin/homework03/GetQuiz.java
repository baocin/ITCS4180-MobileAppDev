//File: GetQuiz
//Homework 03
//2-22-2016
//Praveenkumar Sangalad
//Michael Pedersen
//Gabriel Lima
package com.github.baocin.homework03;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by aoi on 2/20/16.
 */
public class GetQuiz extends AsyncTask<Void, Void, ArrayList<Question>> {
    final String apiURL = "http://dev.theappsdr.com/apis/spring_2016/hw3/index.php";
    ArrayList<Question> questions = new ArrayList<Question>();

    @Override
    protected ArrayList<Question> doInBackground(Void... params) {
        for (int id = 0; id < Quiz.NUM_QUESTIONS; id++) {
            try {
                StringBuilder sb = new StringBuilder();
                //Encode the question id
                RequestParams rp = new RequestParams(apiURL);
                rp.addParam("qid", Integer.toString(id));
                Log.d("request params", rp.toString());
                Log.d("encoded url", rp.getEncodedURL());

                URL url = new URL(rp.getEncodedURL());
                HttpURLConnection con = (HttpURLConnection) url.openConnection();
                con.setRequestMethod("GET");
                BufferedReader reader = new BufferedReader(new InputStreamReader(con.getInputStream()));
                String line = "";
                while ((line = reader.readLine()) != null) {
                    sb.append(line);
                }
                reader.close();

                String rawQuiz = sb.toString();

                //For faster debugging - mock the api data.
//                String rawQuiz = "";
//                switch(id){
//                    case 0:
//                        rawQuiz = "0;How many video games have you played in the past year?;Less than 3;0;3-10;5;More than 10;10;http://dev.theappsdr.com/apis/spring_2016/hw3/photos/0.jpg";
//                        break;
//                    case 1:
//                        rawQuiz = "1;What type of movies/ TV shows do you like best?;Romance/ Reality TV;0;Action/ Thriller /Crime;3;Drama/ Documentary /Natural Films;6;Comedy / Animation;9;Science Fiction/ Fantasy;12";
//                        break;
//                    case 2:
//                        rawQuiz = "2;Do you know any programming languages?;Nope;0;Only what I studied in school ;5;Of Course!;10;http://dev.theappsdr.com/apis/spring_2016/hw3/photos/2.jpg";
//                        break;
//                    case 3:
//                        rawQuiz = "3;Its time to upgrade your mobile phone and you have the budget for it. How do you choose the make and model?;Get whatever the sales person says is best for my needs;0;Get a model identical or similar to that of my friends;2;Ask a friend or read reviews online;5;I already know what mobile I want as I follow mobile industry updates;10;http://dev.theappsdr.com/apis/spring_2016/hw3/photos/3.jpg";
//                        break;
//                    case 4:
//                        rawQuiz = "4;Which of the following was( or is) your favorite subject in school?;Ugh. I didnâ€™t like ANYTHING in school;0;Physical Education;2;Art Literature or History;3;Computers, Math or Science;10;http://dev.theappsdr.com/apis/spring_2016/hw3/photos/4.jpg";
//                        break;
//                    case 5:
//                        rawQuiz = "5;Your mobile phone has stopped working. What do you do?;Buy a new one;0;Take it to my usual repair shop;2;Try to fix it myself with some help from the net;10;http://dev.theappsdr.com/apis/spring_2016/hw3/photos/5.jpg";
//                        break;
//                    case 6:
//                        rawQuiz = "6;On a typical Saturday night, you are most likely â€¦;Out at party or bar;0;Gaming with friends;5;Surfing the net or reading a book;10";
//                        break;
//
//                }
//                Log.d("rawQuiz", rawQuiz);


                Question question = new Question();
                String[] split = rawQuiz.split(";");

                //Populate new question object
                question.setQid(Integer.parseInt(split[0]));
                question.setQuestionText(split[1]);

                if (split.length % 2 != 0) {   //Odd number of fields means image Url is specified
                    question.setImageURL(split[split.length - 1]);
                }

                int numOptions = ((split.length / 2) - 1);
//                Log.d("numOptions", numOptions + "");

                for (int i = 0; i < numOptions * 2; i += 2) {
                    question.setOption(split[2 + i], Integer.parseInt(split[2 + i + 1]));
                }

                Log.d("Generated Question", question.toString());
                questions.add(question);



            } catch(java.net.ConnectException e) {
                e.printStackTrace();
            }catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (ProtocolException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }


        }
        return questions;
    }



}

