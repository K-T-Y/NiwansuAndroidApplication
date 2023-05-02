package com.example.niwansu_android_application.screens.patient.activities;

import static com.example.niwansu_android_application.core.Constants.PREFERENCE_NAME;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.niwansu_android_application.R;
import com.example.niwansu_android_application.core.QuizDiabetesClass;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DiabetesCheckActivity extends AppCompatActivity implements View.OnClickListener{


    TextView userid, questionTextView, depressiontype;

    androidx.appcompat.widget.AppCompatRadioButton ansA, ansB, ansC, ansD;
    ImageView imgBack;
    Button  submitBtn, btn_restart, SeeResults;

    int currentQuestionIndex, score = 0;
    SharedPreferences sharedPreferences;
    private static final String KEY_ID = "id";
    int totalQuestion = QuizDiabetesClass.question.length;

    private static final String KEY_DEPRESSION_TYPE = "depressionType";
    String todaydate = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());
    RelativeLayout layout;
    String selectedAnswer = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diabetes_checkup);

        userid = findViewById(R.id.userid);
        imgBack = findViewById(R.id.imgBack);
        btn_restart = findViewById(R.id.btn_restart);
        depressiontype = findViewById(R.id.Depressiontype);
        SeeResults = findViewById(R.id.btnSeeResults);
        SeeResults.setVisibility(View.INVISIBLE);


        /* Get user details using shared preferances*/
        sharedPreferences = getSharedPreferences(PREFERENCE_NAME, MODE_PRIVATE);
        String id = sharedPreferences.getString(KEY_ID, null);
        String Depressiontype = sharedPreferences.getString(KEY_DEPRESSION_TYPE, null);
        userid.setText(id);
        depressiontype.setText(Depressiontype);







        questionTextView = findViewById(R.id.question);
        ansA = findViewById(R.id.answerA);
        ansB = findViewById(R.id.answerB);
        ansC = findViewById(R.id.answerC);
        ansD = findViewById(R.id.answerD);
        submitBtn = findViewById(R.id.submit_btn);


        ansA.setOnClickListener(this);
        ansB.setOnClickListener(this);
        ansC.setOnClickListener(this);
        ansD.setOnClickListener(this);
        submitBtn.setOnClickListener(this);
        SeeResults.setOnClickListener(this);
        btn_restart.setBackground(ContextCompat.getDrawable(DiabetesCheckActivity.this, R.drawable.round_layout));


        loadNewQuestion();

        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DiabetesCheckActivity.super.onBackPressed();
            }
        });
        btn_restart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                restartQuiz();
                btn_restart.setBackground(ContextCompat.getDrawable(DiabetesCheckActivity.this, R.drawable.round_layout));
            }
        });

        SeeResults.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DiabetesCheckActivity.this, MainActivity.class);


                //get total marks and type of depression from previous activities
                intent.putExtra("message", score);
                intent.putExtra("depressionType", depressiontype.getText().toString());
                startActivity(intent);
                finish();
            }
        });

    }


    @Override
    public void onClick(View view) {


        ansA.setBackground(ContextCompat.getDrawable(this, R.drawable.round_layout_lite_green));
        ansB.setBackground(ContextCompat.getDrawable(this, R.drawable.round_layout_lite_green));
        ansC.setBackground(ContextCompat.getDrawable(this, R.drawable.round_layout_lite_green));
        ansD.setBackground(ContextCompat.getDrawable(this, R.drawable.round_layout_lite_green));

        //Validating answer selection ( User should select an answer before pressing submit button, and also user should answer all 10 questions)

        Button clickedButton = (Button) view;
        if (clickedButton.getId() == R.id.submit_btn) {

                if (selectedAnswer.equals(QuizDiabetesClass.Answer1[currentQuestionIndex])) {
                    score = new Integer(score + 0);
                    currentQuestionIndex++;
                    loadNewQuestion();
                } else if (selectedAnswer.equals(QuizDiabetesClass.Answer2[currentQuestionIndex])) {
                    score = new Integer(score + 1);
                    currentQuestionIndex++;
                    loadNewQuestion();
                } else if (selectedAnswer.equals(QuizDiabetesClass.Answer3[currentQuestionIndex])) {
                    score = new Integer(score + 2);
                    currentQuestionIndex++;
                    loadNewQuestion();
                } else if (selectedAnswer.equals(QuizDiabetesClass.Answer4[currentQuestionIndex])) {
                    score = new Integer(score + 3);
                    currentQuestionIndex++;
                    loadNewQuestion();
                } else if (selectedAnswer.equals("")) {
                    Toast.makeText(DiabetesCheckActivity.this, "Please select an answer", Toast.LENGTH_SHORT).show();

                }



        } else {
            //choices button clicked
            selectedAnswer = clickedButton.getText().toString();
            // clickedButton.setBackgroundColor(Color.MAGENTA);
            clickedButton.setBackground(ContextCompat.getDrawable(this, R.drawable.round_layout_lite_green));

        }

    }


    //Loading the next question from data set
    void loadNewQuestion() {

        if (currentQuestionIndex == totalQuestion) {

            finishQuiz();
            submitBtn.setVisibility(View.INVISIBLE);

            //   CreatepopUpwindow();

            return;
        }

        questionTextView.setText(QuizDiabetesClass.question[currentQuestionIndex]);
        ansA.setText(QuizDiabetesClass.choices[currentQuestionIndex][0]);
        ansB.setText(QuizDiabetesClass.choices[currentQuestionIndex][1]);
        ansC.setText(QuizDiabetesClass.choices[currentQuestionIndex][2]);
        ansD.setText(QuizDiabetesClass.choices[currentQuestionIndex][3]);

    }

    void finishQuiz() {

        questionTextView.setText("Test completed.");
        ansA.setVisibility(View.INVISIBLE);
        ansB.setVisibility(View.INVISIBLE);
        ansC.setVisibility(View.INVISIBLE);
        ansD.setVisibility(View.INVISIBLE);
        btn_restart.setVisibility(View.INVISIBLE);
      //  uploadDataset();

    }


    void restartQuiz() {
        score = 0;
        currentQuestionIndex = 0;
        loadNewQuestion();
    }

}