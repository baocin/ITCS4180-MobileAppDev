package com.github.baocin.homework03;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

public class Results extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);

        Button btnRestart = (Button) findViewById(R.id.btnRestartQuiz);
        btnRestart.setOnClickListener(this);

        int[] answerList = getIntent().getExtras().getIntArray("answerList");

        Log.d("AnswerList:", answerList.toString());

        int totalScore = 0;
        for (int i : answerList){
            totalScore += i;
        }

        Log.d("Total Score:", totalScore+"");

    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.btnRestartQuiz:
                Intent i = new Intent(getApplicationContext(), Welcome.class);
                startActivity(i);
        }
    }
}
