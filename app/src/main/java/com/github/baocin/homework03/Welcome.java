package com.github.baocin.homework03;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class Welcome extends AppCompatActivity implements View.OnClickListener {
    ArrayList<Question> questions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        findViewById(R.id.btnQuit).setOnClickListener(this);

        Button btnStartQuiz = (Button) findViewById(R.id.btnStartQuiz);
        btnStartQuiz.setOnClickListener(this);
        btnStartQuiz.setEnabled(false);



        try {
            questions = new GetQuiz(this).execute().get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        Log.d("questions", questions.toString());


        btnStartQuiz.setEnabled(true);

    }

    public void LoadQuestion(int id){


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
