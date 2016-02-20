package com.github.baocin.homework03;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;

public class Results extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);

        int[] answerList = getIntent().getExtras().getIntArray("answerList");

        int totalScore = 0;
        for (int i : answerList){
            totalScore += i;
        }

        Log.d("Total Score:", totalScore+"");

    }
}
