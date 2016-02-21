package com.github.baocin.homework03;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class Quiz extends AppCompatActivity implements View.OnClickListener {
    final public static int NUM_QUESTIONS = 7;
    ArrayList<Question> questions;
    int currentQuestionID = 0;
    RadioGroup rdg;
    private TextView lblQuestionNumber;
    private TextView txtDescription;
    private ImageView questionImage;
    private int[] answerList;
    private boolean selectedRadioButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        answerList = new int[NUM_QUESTIONS];
        rdg = ((RadioGroup) findViewById(R.id.rdgChoices));
        lblQuestionNumber = ((TextView)findViewById(R.id.lblQuestionNumber));
        txtDescription = ((TextView)findViewById(R.id.txtDescription));
        questionImage = ((ImageView) findViewById(R.id.imgQuestionImage));

        questions = (ArrayList<Question>) getIntent().getExtras().get("questions");
        rdg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                selectedRadioButton = true;
                int questionNumber = (int)group.getTag();
                Log.d("QuestionNum:", questionNumber+"");
                Log.d("child:", checkedId-1 +"");
                Log.d("obj?", group.findViewById(checkedId).getTag().toString());
//                int points = (int)group.getChildAt(checkedId-1).getTag();
                int points = (int) group.findViewById(checkedId).getTag();
                Log.d("points:", points+"");
                answerList[questionNumber] = points;
            }
        });

        populate(0);

        findViewById(R.id.btnNext).setOnClickListener(this);
        findViewById(R.id.btnQuit).setOnClickListener(this);

    }

//    public void clear(){
//            selectedRadioButton = false;
//        ((TextView)findViewById(R.id.lblQuestionNumber)).setText("");
//        ((TextView)findViewById(R.id.txtDescription)).setText("");
//        ((RadioGroup) findViewById(R.id.rdgChoices)).removeAllViews();
//        ((ImageView) findViewById(R.id.imgQuestionImage)).setImageDrawable(null);
//    }

    public void populate(int id){
        populate(questions.get(id));
    }

    public void populate(Question q){
        //Reset some variables...
        selectedRadioButton = false;


        Log.d("populating quiz with ", q.toString());

        if (!q.getImageURL().equals("")) {
            AsyncTask<String, Void, Bitmap> asyncResult = new GetImage(this).execute(q.getImageURL());
            try{
                Bitmap image = asyncResult.get();
                questionImage.setImageBitmap(image);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }else{
            //Clear the image
            questionImage.setImageResource(android.R.color.transparent);
        }

        lblQuestionNumber.setText("Q" + q.getQid());
        txtDescription.setText(q.getQuestionText());

        rdg.setTag(q.getQid());
        rdg.removeAllViews();
        for (String key : q.getOptions().keySet()){
            RadioButton rb = new RadioButton(getApplicationContext());
            rb.setText(key);
            rb.setTag(q.getOption(key));
            Log.d("Made option:", rb.getText() + "  " + rb.getTag());
            rdg.addView(rb);
        }
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.btnNext:
                if (!selectedRadioButton){
                    Toast.makeText(Quiz.this, R.string.blankAnswerErrorMessage, Toast.LENGTH_SHORT).show();
                    break;
                }
                currentQuestionID+=1;
                if (currentQuestionID >= NUM_QUESTIONS){
                    Intent i = new Intent(getApplicationContext(), Results.class);
                    i.putExtra("answerList", answerList);
                    startActivity(i);
                }else {
//                currentQuestionID %= NUM_QUESTIONS;
                    populate(questions.get(currentQuestionID));
                }
                break;
            case R.id.btnQuit:
//                finish();
                Intent i = new Intent(getApplicationContext(), Welcome.class);
                startActivity(i);
                break;
        }
    }
}
