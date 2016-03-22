//File: MainActivity
//InClass08
//Group 18
//2-27-2016
//Praveenkumar Sangalad
//Michael Pedersen
//Gabriel Lima
package com.github.baocin.inclass07;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    public static DataManager dm;
    String topic = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dm = new DataManager(this);

        Spinner spinner = (Spinner)findViewById(R.id.topicSpinner);
        ArrayList<String> st = new ArrayList<String>();
        st.addAll(Arrays.asList(getResources().getStringArray(R.array.sections)));
        ArrayList<String> finalList = new ArrayList<>();
        for (String s : st){
            int count = MainActivity.dm.countStoriesInCategory(s);
            if (count > 0){
                finalList.add(s + "[" + count + "]");
            }else{
                finalList.add(s);
            }
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, finalList);
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String [] sections = getResources().getStringArray(R.array.sections);
                topic = sections[position];

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                topic = "";
            }
        });

        findViewById(R.id.submitButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (topic.equals("")) {
                    Toast.makeText(getApplicationContext(), "Please select a section!", Toast.LENGTH_SHORT).show();
                } else {
                    Intent i = new Intent(MainActivity.this, TopStories.class);
                    i.putExtra("section", topic);
                    startActivity(i);
                }


            }
        });

    }


    @Override
    protected void onResume() {
        super.onResume();
        Spinner spinner = (Spinner)findViewById(R.id.topicSpinner);
        ArrayList<String> st = new ArrayList<String>();
        st.addAll(Arrays.asList(getResources().getStringArray(R.array.sections)));
        ArrayList<String> finalList = new ArrayList<>();
        for (String s : st){
            int count = MainActivity.dm.countStoriesInCategory(s);
            if (count > 0){
                finalList.add(s + "[" + count + "]");
            }else{
                finalList.add(s);
            }
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, finalList);
        spinner.setAdapter(adapter);
    }
}
