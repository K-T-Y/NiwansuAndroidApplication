package com.example.niwansu_android_application.screens.patient.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import com.example.niwansu_android_application.R;
import com.example.niwansu_android_application.core.Constants;
import com.example.niwansu_android_application.core.LoginResponseModel;
import com.example.niwansu_android_application.core.NetworkClient;
import com.example.niwansu_android_application.core.NetworkService;
import com.example.niwansu_android_application.screens.doctor.activities.DoctorMainActivity;
import com.google.android.material.textfield.TextInputEditText;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    AppCompatButton login;

    TextInputEditText etEmail,etPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);
        login = findViewById(R.id.btnLogin);


        etPassword.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    hideSoftKeyboard(LoginActivity.this);
                }
            }
        });

            login.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    login();
                }
            });
    }

    public static void hideSoftKeyboard(Activity activity) {
        InputMethodManager inputMethodManager =
                (InputMethodManager) activity.getSystemService(
                        Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(
                activity.getCurrentFocus().getWindowToken(), 0);
    }

    private void login() {

        final ProgressDialog progressDialog = new ProgressDialog(LoginActivity.this);
        progressDialog.setTitle("Please wait");
        progressDialog.setMessage("Logging in...");
        progressDialog.setCancelable(false);
        progressDialog.show();


        NetworkService networkService = NetworkClient.getClient().create(NetworkService.class);
        Call<LoginResponseModel> login1 = networkService.login1(etEmail.getText().toString(), etPassword.getText().toString());

        login1.enqueue(new Callback<LoginResponseModel>() {
            @Override
            public void onResponse(@NonNull Call<LoginResponseModel> call, @NonNull Response<LoginResponseModel> response) {
                LoginResponseModel responseBody = response.body();
                if (responseBody != null) {
                    if (responseBody.getSuccess().equals("1")) {

                        SharedPreferences preferences = getSharedPreferences(Constants.PREFERENCE_NAME, MODE_PRIVATE);
                        SharedPreferences.Editor editor = preferences.edit();
                        editor.putBoolean(Constants.KEY_ISE_LOGGED_IN, true);
                        editor.putString(Constants.KEY_FIRST_NAME, responseBody.getUserDetailObject().getUserDetails().get(0).getName());
                        editor.putString(Constants.KEY_LAST_NAME, responseBody.getUserDetailObject().getUserDetails().get(0).getLastname());
                        editor.putString(Constants.KEY_MOBILE, responseBody.getUserDetailObject().getUserDetails().get(0).getMobile() );
                        editor.putString(Constants.KEY_EMAIL, responseBody.getUserDetailObject().getUserDetails().get(0).getEmail());
                        editor.putString(Constants.KEY_PROVINCE, responseBody.getUserDetailObject().getUserDetails().get(0).getProvince() );
                        editor.putString(Constants.KEY_CITY, responseBody.getUserDetailObject().getUserDetails().get(0).getCity());
                        editor.putString(Constants.KEY_DISTRICT, responseBody.getUserDetailObject().getUserDetails().get(0).getDistrict());
                        editor.putString(Constants.KEY_PROFILE_PIC, responseBody.getUserDetailObject().getUserDetails().get(0).getProfilepicture());
                        editor.putString(Constants.KEY_DATE_CREATED, responseBody.getUserDetailObject().getUserDetails().get(0).getDate());
                        editor.putString(Constants.KEY_USER_CATEGORY, responseBody.getUserDetailObject().getUserDetails().get(0).getUsercategory());
                        editor.putString(Constants.KEY_PROFILE_PIC, responseBody.getUserDetailObject().getUserDetails().get(0).getProfilepicture());




                        editor.apply();


                        if (responseBody.getUserDetailObject().getUserDetails().get(0).getUsercategory().equals("patient")) {
                            startActivity(new Intent(getApplicationContext(), MainActivity.class));
                            finish();

                        }else if(responseBody.getUserDetailObject().getUserDetails().get(0).getUsercategory().equals("doctor"))
                        {
                            startActivity(new Intent(getApplicationContext(), DoctorMainActivity.class));
                            finish();
                        }
                    } else {
                        Toast.makeText(LoginActivity.this, responseBody.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
                progressDialog.dismiss();

            }

            @Override
            public void onFailure(@NonNull Call<LoginResponseModel> call, @NonNull Throwable t) {

                progressDialog.dismiss();
            }
        });

    }
}