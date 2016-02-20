package com.github.baocin.homework03;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class Welcome extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);


        Button btnStartQuiz = (Button) findViewById(R.id.btnStartQuiz);
        btnStartQuiz.setEnabled(false);

        final ProgressDialog pDialog = new ProgressDialog(this);
        pDialog.setMessage(getString(R.string.txtLoadingMessage));
        pDialog.setIndeterminate(false);
//        pDialog.show();

        ArrayList<Question> questions = GetQuiz();
        Log.d("questions", questions.toString());

    }

    public void LoadQuestion(int id){


    }

    public void setupUI(Question q){

    }

    public ArrayList<Question> GetQuiz(){
        ArrayList<Question> questions = new ArrayList<Question>();
        for(int id = 0; id < 7; id++){
            AsyncTask<Integer, Void, Question> asyncResult = new GetQuizQuestion().execute(id);
            try {
                Question question = asyncResult.get();
                questions.add(question);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }
        return questions;
    }
}
