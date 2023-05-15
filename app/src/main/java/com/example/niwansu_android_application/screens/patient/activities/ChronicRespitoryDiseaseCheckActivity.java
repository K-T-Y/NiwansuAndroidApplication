package com.example.niwansu_android_application.screens.patient.activities;

import static com.example.niwansu_android_application.core.Constants.PREFERENCE_NAME;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.example.niwansu_android_application.R;
import com.example.niwansu_android_application.core.QuizChronicRepositiryDiseaseClass;
import com.example.niwansu_android_application.core.QuizKidneyDiseaseClass;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class ChronicRespitoryDiseaseCheckActivity extends AppCompatActivity implements View.OnClickListener {


    TextView userid, questionTextView, depressiontype;

    androidx.appcompat.widget.AppCompatRadioButton ansA, ansB, ansC, ansD;
    ImageView imgBack;
    Button submitBtn, btn_restart, SeeResults, taketodoc;

    RadioGroup radioGroup;
    int currentQuestionIndex, score = 0;
    SharedPreferences sharedPreferences;
    private static final String KEY_ID = "id";
    int totalQuestion = QuizChronicRepositiryDiseaseClass.question.length;

    private static final String KEY_DEPRESSION_TYPE = "depressionType";
    String todaydate = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());
    RelativeLayout layout;
    String selectedAnswer = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chronic_checkup);

        userid = findViewById(R.id.userid);
        imgBack = findViewById(R.id.imgBack);
        btn_restart = findViewById(R.id.btn_restart);
        depressiontype = findViewById(R.id.Depressiontype);
        SeeResults = findViewById(R.id.btnSeeResults);
        SeeResults.setVisibility(View.INVISIBLE);
        taketodoc = findViewById(R.id.btnTakeToDoctor);
        radioGroup = findViewById(R.id.radioGroup);


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
        btn_restart.setBackground(ContextCompat.getDrawable(ChronicRespitoryDiseaseCheckActivity.this, R.drawable.round_layout));


        loadNewQuestion();

        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ChronicRespitoryDiseaseCheckActivity.super.onBackPressed();
            }
        });
        btn_restart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                restartQuiz();
                btn_restart.setBackground(ContextCompat.getDrawable(ChronicRespitoryDiseaseCheckActivity.this, R.drawable.round_layout));
            }
        });

        SeeResults.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ChronicRespitoryDiseaseCheckActivity.this, MainActivity.class);


                //get total marks and type of depression from previous activities
                intent.putExtra("message", score);
                intent.putExtra("depressionType", depressiontype.getText().toString());
                startActivity(intent);
                finish();
            }
        });
        taketodoc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ChronicRespitoryDiseaseCheckActivity.this, BookDoctorActivity.class);
                intent.putExtra("DoctorName", "Dr.Sachini Gallage");
                intent.putExtra("DocImage", R.drawable.doctor4);
                startActivity(intent);
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

            if (selectedAnswer.equals(QuizChronicRepositiryDiseaseClass.Answer1[currentQuestionIndex])) {
                score = new Integer(score + 0);
                currentQuestionIndex++;
                loadNewQuestion();
            } else if (selectedAnswer.equals(QuizChronicRepositiryDiseaseClass.Answer2[currentQuestionIndex])) {
                score = new Integer(score + 2);
                currentQuestionIndex++;
                loadNewQuestion();
            } else if (selectedAnswer.equals(QuizChronicRepositiryDiseaseClass.Answer3[currentQuestionIndex])) {
                score = new Integer(score + 3);
                currentQuestionIndex++;
                loadNewQuestion();
            } else if (selectedAnswer.equals(QuizChronicRepositiryDiseaseClass.Answer4[currentQuestionIndex])) {
                score = new Integer(score + 5);
                currentQuestionIndex++;
                loadNewQuestion();
            } else if (selectedAnswer.equals("")) {
                Toast.makeText(ChronicRespitoryDiseaseCheckActivity.this, "Please select an answer", Toast.LENGTH_SHORT).show();

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

        questionTextView.setText(QuizChronicRepositiryDiseaseClass.question[currentQuestionIndex]);
        ansA.setText(QuizChronicRepositiryDiseaseClass.choices[currentQuestionIndex][0]);
        ansB.setText(QuizChronicRepositiryDiseaseClass.choices[currentQuestionIndex][1]);
        ansC.setText(QuizChronicRepositiryDiseaseClass.choices[currentQuestionIndex][2]);
        ansD.setText(QuizChronicRepositiryDiseaseClass.choices[currentQuestionIndex][3]);
        radioGroup.clearCheck();

    }

    void finishQuiz() {

        if (score < 10) {
            questionTextView.setText("Your Score is " + score + " and you don't have to worry about having Chronic respiratory diseases ");
            questionTextView.setTextSize(16);
        } else if (score <= 20) {
            questionTextView.setText("Your Score is " + score + " You have a slight chance of having Chronic respiratory diseases\n  We have specialist who are excellent in this area.\n make sure to take a look.");
            questionTextView.setTextSize(16);
        } else if (score <= 30) {
            questionTextView.setText("Your Score is " + score + " You have a over 75% chance of having Chronic respiratory diseases.\n Make sure to channel one of our doctors as soon as possible\n\n Please click button below and we will take you to specialists who are available now for you to channel");
            questionTextView.setTextSize(16);
            taketodoc.setVisibility(View.VISIBLE);
        }

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