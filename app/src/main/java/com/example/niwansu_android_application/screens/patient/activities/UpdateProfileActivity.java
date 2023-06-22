package com.example.niwansu_android_application.screens.patient.activities;

import static com.example.niwansu_android_application.core.Constants.PREFERENCE_NAME;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.niwansu_android_application.R;
import com.example.niwansu_android_application.core.Constants;
import com.example.niwansu_android_application.core.NetworkClient;
import com.example.niwansu_android_application.core.NetworkService;
import com.example.niwansu_android_application.core.ResponseModel;
import com.example.niwansu_android_application.models.changeStatusModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UpdateProfileActivity extends AppCompatActivity {
EditText txt_fname,txt_lname,txt_email,txt_contact,txt_password;
TextView txt_tname;
ImageView done,back;

Button removeaccnt;
    private NetworkService apiInterface;
    SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(com.example.niwansu_android_application.R.layout.activity_update_profile);
        sharedPreferences =getSharedPreferences(PREFERENCE_NAME, MODE_PRIVATE);
        removeaccnt = findViewById(R.id.btn_remove_account);
        OnCreateObjects();
        setData();
        txt_email.setEnabled(false);
        apiInterface = NetworkClient.getClient().create(NetworkService.class);
        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final ProgressDialog progressDialog = new ProgressDialog(UpdateProfileActivity.this);
                progressDialog.setTitle("Please wait");
                progressDialog.setMessage("Updating...");
                progressDialog.setCancelable(false);
                progressDialog.show();


                Call<ResponseModel> call = apiInterface.updateProfile(txt_fname.getText().toString(),txt_lname.getText().toString(),txt_email.getText().toString(),txt_contact.getText().toString(),txt_password.getText().toString());
                System.out.println("lll");

                call.enqueue(new Callback<ResponseModel>() {
                    @Override
                    public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                        ResponseModel responseBody = response.body();
                        if(responseBody.getSuccess().equals("1"))
                        {
                            progressDialog.dismiss();
                            Toast.makeText(getApplicationContext(),"Successfully Updated !",Toast.LENGTH_LONG).show();
                           onBackPressed();


                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseModel> call, Throwable t) {
                        Toast.makeText(getApplicationContext(),"Error Occurred!",Toast.LENGTH_LONG).show();
                    }
                });
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        removeaccnt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteAccount();
            }
        });

    }
    protected  void OnCreateObjects()
    {
        txt_fname=findViewById(R.id.txt_fname);
        txt_lname=findViewById(R.id.txt_lname);
        txt_contact=findViewById(R.id.txt_contact);
        txt_email=findViewById(R.id.txt_email);
        txt_password=findViewById(R.id.txt_password);
        txt_tname=findViewById(R.id.txt_tname);
        done=findViewById(R.id.done);
        back=findViewById(R.id.back);
    }
    protected  void setData()
    {
        txt_fname.setText(sharedPreferences.getString(Constants.KEY_FIRST_NAME, null));
        txt_lname.setText(sharedPreferences.getString(Constants.KEY_LAST_NAME, null));
        txt_contact.setText(sharedPreferences.getString(Constants.KEY_MOBILE, null));
        txt_email.setText(sharedPreferences.getString(Constants.KEY_EMAIL, null));
        txt_tname.setText("Dr."+sharedPreferences.getString(Constants.KEY_FIRST_NAME, null)+" "+sharedPreferences.getString(Constants.KEY_LAST_NAME, null));
    }
    public void deleteAccount()
    {
        final ProgressDialog progressDialog = new ProgressDialog(UpdateProfileActivity.this);
        progressDialog.setTitle("Please wait");
        progressDialog.setMessage("Removing Account...");
        progressDialog.setCancelable(false);
        progressDialog.show();


        Call<ResponseModel> call = apiInterface.deleteProfile(txt_email.getText().toString());
        System.out.println("lll");

        call.enqueue(new Callback<ResponseModel>() {
            @Override
            public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                ResponseModel responseBody = response.body();
                if(responseBody.getSuccess().equals("1"))
                {
                    progressDialog.dismiss();
                    Toast.makeText(getApplicationContext(),"Successfully Removed !",Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(UpdateProfileActivity.this,LoginActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    finish();
                    startActivity(intent);


                }
            }

            @Override
            public void onFailure(Call<ResponseModel> call, Throwable t) {
                Toast.makeText(getApplicationContext(),"Error Occurred!",Toast.LENGTH_LONG).show();
            }
        });
    }
}