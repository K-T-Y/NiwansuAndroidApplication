package com.example.niwansu_android_application.screens.patient.activities;

import static com.example.niwansu_android_application.core.Constants.PREFERENCE_NAME;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import com.example.niwansu_android_application.R;
import com.example.niwansu_android_application.core.Constants;

public class UpdateProfileActivity extends AppCompatActivity {
EditText txt_fname,txt_lname,txt_email,txt_contact,txt_password;
TextView txt_tname;
    SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(com.example.niwansu_android_application.R.layout.activity_update_profile);
        sharedPreferences =getSharedPreferences(PREFERENCE_NAME, MODE_PRIVATE);
        OnCreateObjects();
        setData();

    }
    protected  void OnCreateObjects()
    {
        txt_fname=findViewById(R.id.txt_fname);
        txt_lname=findViewById(R.id.txt_lname);
        txt_contact=findViewById(R.id.txt_contact);
        txt_email=findViewById(R.id.txt_email);
        txt_password=findViewById(R.id.txt_password);
        txt_tname=findViewById(R.id.txt_tname);
    }
    protected  void setData()
    {
        txt_fname.setText(sharedPreferences.getString(Constants.KEY_FIRST_NAME, null));
        txt_lname.setText(sharedPreferences.getString(Constants.KEY_LAST_NAME, null));
        txt_contact.setText(sharedPreferences.getString(Constants.KEY_MOBILE, null));
        txt_email.setText(sharedPreferences.getString(Constants.KEY_EMAIL, null));
        txt_tname.setText("Dr."+sharedPreferences.getString(Constants.KEY_FIRST_NAME, null)+" "+sharedPreferences.getString(Constants.KEY_LAST_NAME, null));
    }
}