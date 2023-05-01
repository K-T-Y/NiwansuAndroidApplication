package com.example.niwansu_android_application.screens.patient.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.niwansu_android_application.R;

public class SplashScreenActivity extends AppCompatActivity {
    TextView tvGetStarted;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        tvGetStarted = findViewById(R.id.tvGetStarted);

        tvGetStarted.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SplashScreenActivity.this,LoginSelectionActivity.class);
                startActivity(intent);
            }
        });
    }
}