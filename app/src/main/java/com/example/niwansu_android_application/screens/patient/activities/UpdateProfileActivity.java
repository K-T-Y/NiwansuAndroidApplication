package com.example.niwansu_android_application.screens.patient.activities;

import static com.example.niwansu_android_application.core.Constants.PREFERENCE_NAME;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.niwansu_android_application.R;
import com.example.niwansu_android_application.core.AppointmentsList;
import com.example.niwansu_android_application.core.Constants;
import com.example.niwansu_android_application.core.NetworkClient;
import com.example.niwansu_android_application.core.NetworkService;
import com.example.niwansu_android_application.models.changeStatusModel;
import com.example.niwansu_android_application.screens.doctor.activities.ProfileActivity;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UpdateProfileActivity extends AppCompatActivity {
EditText txt_fname,txt_lname,txt_email,txt_contact,txt_password;
TextView txt_tname,txt_save;
    private NetworkService apiInterface;
    SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(com.example.niwansu_android_application.R.layout.activity_update_profile);
        sharedPreferences =getSharedPreferences(PREFERENCE_NAME, MODE_PRIVATE);
        OnCreateObjects();
        setData();
        txt_email.setEnabled(false);
        apiInterface = NetworkClient.getClient().create(NetworkService.class);
        txt_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Call<changeStatusModel> call = apiInterface.updateProfile(txt_fname.getText().toString(),txt_lname.getText().toString(),txt_email.getText().toString(),txt_contact.getText().toString(),txt_password.getText().toString());

                call.enqueue(new Callback<changeStatusModel>() {
                    @Override
                    public void onResponse(Call<changeStatusModel> call, Response<changeStatusModel> response) {
                        if(response.body().getResult()==1)
                        {
                            Toast.makeText(getApplicationContext(),"Successfully Updated !",Toast.LENGTH_LONG).show();
                            Intent intent=new Intent(getApplicationContext(), LoginActivity.class);
                            startActivity(intent);
                        }
                    }

                    @Override
                    public void onFailure(Call<changeStatusModel> call, Throwable t) {
                        Toast.makeText(getApplicationContext(),"Error Updated !",Toast.LENGTH_LONG).show();
                    }
                });
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
        txt_save=findViewById(R.id.txt_save);
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