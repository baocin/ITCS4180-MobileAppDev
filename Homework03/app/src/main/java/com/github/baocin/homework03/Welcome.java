//File: Welcome
//Homework 03
//2-22-2016
//Praveenkumar Sangalad
//Michael Pedersen
//Gabriel Lima
package com.github.baocin.homework03;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class Welcome extends AppCompatActivity implements View.OnClickListener {
    ArrayList<Question> questions;
    Button btnStartQuiz;
    private ProgressBar loadingBar;
    private TextView loadingMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        findViewById(R.id.btnQuit).setOnClickListener(this);

        btnStartQuiz = (Button) findViewById(R.id.btnStartQuiz);
        btnStartQuiz.setOnClickListener(this);
        btnStartQuiz.setEnabled(false);

        loadingBar = (ProgressBar) findViewById(R.id.pbLoadingBar);
        loadingMessage = (TextView) findViewById(R.id.txtLoadingMessage);

        loadQuestions();
    }

    public void loadQuestions(){
        try {
            questions = new GetQuiz().execute().get();
            btnStartQuiz.setEnabled(true);
            loadingBar.setVisibility(View.INVISIBLE);
            loadingMessage.setVisibility(View.INVISIBLE);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        Log.d("questions", questions.toString());
    }

    public void setupUI(Question q){

    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.btnStartQuiz:
                Intent i = new Intent(getBaseContext(), Quiz.class);
                i.putExtra("questions", questions);
                startActivity(i);
                break;
            case R.id.btnQuit:
                finish();
                break;
        }
    }
}
