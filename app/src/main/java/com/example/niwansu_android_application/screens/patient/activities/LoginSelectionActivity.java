package com.example.niwansu_android_application.screens.patient.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.niwansu_android_application.R;

public class LoginSelectionActivity extends AppCompatActivity {

    private TextView Login,Signup;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_selection);



        Login = findViewById(R.id.txtLogin);
        Signup = findViewById(R.id.txtSignup);



        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            Intent intent = new Intent(LoginSelectionActivity.this,LoginActivity.class);
            startActivity(intent);
            }
        });
        Signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginSelectionActivity.this, ResetPasswordActivity.class);
                startActivity(intent);
            }
        });


    }

}