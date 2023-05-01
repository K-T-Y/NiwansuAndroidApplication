package com.example.niwansu_android_application.screens.doctor.activities;

import static com.example.niwansu_android_application.core.Constants.PREFERENCE_NAME;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.TextView;

import com.example.niwansu_android_application.R;
import com.example.niwansu_android_application.core.Constants;
import com.example.niwansu_android_application.screens.patient.activities.UpdateProfileActivity;

import de.hdodenhof.circleimageview.CircleImageView;

public class ProfileActivity extends AppCompatActivity {
TextView txt_name,txt_spec,txt_phone,txt_email,txt_update;
    SharedPreferences sharedPreferences;
    CircleImageView profile_pic;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        sharedPreferences =getSharedPreferences(PREFERENCE_NAME, MODE_PRIVATE);
        txt_name=findViewById(R.id.txt_name);
//        txt_spec=findViewById(R.id.txt_spec);
        txt_phone=findViewById(R.id.txt_phone);
        txt_email=findViewById(R.id.txt_email);
        profile_pic=findViewById(R.id.profile_pic);
        txt_update=findViewById(R.id.txt_update);

        txt_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(), UpdateProfileActivity.class);
                startActivity(intent);

            }
        });
        if(sharedPreferences.getString(Constants.KEY_PROFILE_PIC, null)!=null)
        {
            String base64 = sharedPreferences.getString(Constants.KEY_PROFILE_PIC, null);
            byte[] imgdate = Base64.decode(base64, Base64.DEFAULT);
//                 String text = new String(imgdate, StandardCharsets.UTF_8);
            Bitmap decodedByte = BitmapFactory.decodeByteArray(imgdate, 0, imgdate.length);
            profile_pic.setImageBitmap(decodedByte);
        }

        txt_email.setText(sharedPreferences.getString(Constants.KEY_EMAIL,null));
        txt_name.setText("Dr."+sharedPreferences.getString(Constants.KEY_FIRST_NAME,null)+" "+ sharedPreferences.getString(Constants.KEY_LAST_NAME,null));
        txt_phone.setText(sharedPreferences.getString(Constants.KEY_MOBILE,null));
//        txt_spec.setVisibility(View.GONE);




    }
}