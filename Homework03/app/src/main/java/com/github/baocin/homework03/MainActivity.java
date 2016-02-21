package com.github.baocin.homework03;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Timer autoStartQuiz;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnStartQuiz = (Button) findViewById(R.id.btnStartQuiz);
        btnStartQuiz.setOnClickListener(this);

        final TimerTask startQuiz = new TimerTask() {
            @Override
            public void run() {
                startQuiz();
            }

        };
        autoStartQuiz = new Timer("autoStart");
        autoStartQuiz.schedule(startQuiz, 8000);

    }

    public void startQuiz(){
        Intent quiz = new Intent(getBaseContext(), Welcome.class);
        startActivity(quiz);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.btnStartQuiz:
                autoStartQuiz.cancel();
                startQuiz();
                break;
        }
    }
}
