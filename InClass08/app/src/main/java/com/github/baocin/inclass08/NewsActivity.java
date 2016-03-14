package com.github.baocin.inclass08;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.ExecutionException;

public class NewsActivity extends AppCompatActivity {
//    private ProgressDialog progressDialog;
    ArrayList<News> newsData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);

        String url = getIntent().getExtras().getString("url");


        ListView lv = (ListView) findViewById(R.id.headlineList);

//        progressDialog = new ProgressDialog(this);
//        progressDialog.setCancelable(false);
//        progressDialog.setMessage("Loading News...");
//        progressDialog.show();

        ArrayList<String> topics = new ArrayList<>();
        try {
            newsData = new GetNewsDataAsync().execute(url).get();
            Log.d("News Data", newsData.toString());

            //Display Retrieved news Data
            NewsAdapter arrayAdapter = new NewsAdapter(this, R.layout.news_row, newsData);
            lv.setAdapter(arrayAdapter);

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

//        progressDialog.dismiss();


        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //Add to favorites
                Toast.makeText(getApplicationContext(), "Adding to Favorites", Toast.LENGTH_SHORT).show();
                DatabaseManager.getInstance().createNews(newsData.get(position));

            }
        });
    }

    public class GetNewsDataAsync extends AsyncTask<String, Void, ArrayList<News>> {
        private ProgressDialog progressDialog;

        @Override
        protected ArrayList<News> doInBackground(String... params) {
            String s = "";
            for (int i = 0; i < 10000; i++){
                s += " ";
            }

            ArrayList<News> aux = null;
            try {
                aux = FeedReader.NewsPullParser.ParseNews(params[0]);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (XmlPullParserException e) {
                e.printStackTrace();
            }

            return aux;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            progressDialog = new ProgressDialog(NewsActivity.this);
            progressDialog.setCancelable(false);
            progressDialog.setIndeterminate(true);
            progressDialog.setMessage("Loading News...");
            progressDialog.show();


        }

        @Override
        protected void onPostExecute(ArrayList<News> newses) {
            super.onPostExecute(newses);
            progressDialog.dismiss();

        }

    }

}


