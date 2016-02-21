package com.github.baocin.homework03;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class Results extends AppCompatActivity implements View.OnClickListener {

    TextView resultTextView, resultDescriptionView;
    ImageView imageResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);

        resultTextView = (TextView) findViewById(R.id.txtQuizResult);
        resultDescriptionView = (TextView) findViewById(R.id.txtResultDescription);
        imageResult = (ImageView) findViewById(R.id.imgResultPicture);

        Button btnRestart = (Button) findViewById(R.id.btnRestartQuiz);
        btnRestart.setOnClickListener(this);

        int[] answerList = getIntent().getExtras().getIntArray("answerList");

        Log.d("AnswerList:", answerList.toString());

        int totalScore = 0;
        for (int i : answerList){
            totalScore += i;
        }

        if (totalScore >= 0 && totalScore <= 10) {

            resultTextView.setText(R.string.nonGeek);
            resultTextView.setTextColor(Color.RED);

            imageResult.setImageResource(R.drawable.non_geek);

            resultDescriptionView.setText(R.string.nonGeekDescription);
        }
        else if (totalScore >= 11 && totalScore <= 50) {

            resultTextView.setText(R.string.semiGeek);
            resultTextView.setTextColor(Color.GREEN);

            imageResult.setImageResource(R.drawable.semi_geek);

            resultDescriptionView.setText(R.string.semiGeekDescription);

        } else if (totalScore >= 51 && totalScore <= 72){

            resultTextView.setText(R.string.uberGeek);
            resultTextView.setTextColor(Color.BLUE);

            imageResult.setImageResource(R.drawable.uber_geek);

            resultDescriptionView.setText(R.string.uberGeekDescription);

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
