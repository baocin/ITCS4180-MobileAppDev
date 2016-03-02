package com.github.baocin.inclass07;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    String topic = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        Spinner spinner = (Spinner)findViewById(R.id.topicSpinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.sections, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
    // Apply the adapter to the spinner
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
                if (topic.equals("")){
                    Toast.makeText(getApplicationContext(), "Please select a section!", Toast.LENGTH_SHORT).show();
                }else{
                    Intent i = new Intent(MainActivity.this, TopStories.class);
                    i.putExtra("section", topic);
                    startActivity(i);
                }


            }
        });

    }
}
