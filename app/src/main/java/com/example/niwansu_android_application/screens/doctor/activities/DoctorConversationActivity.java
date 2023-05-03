package com.example.niwansu_android_application.screens.doctor.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.niwansu_android_application.R;
import com.zegocloud.zimkit.common.ZIMKitRouter;
import com.zegocloud.zimkit.common.enums.ZIMKitConversationType;

public class DoctorConversationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_conversation);
        Intent i = getIntent();
        String userID =  i.getStringExtra("userID");

        startSingleChat(userID);
    }



    private void startSingleChat(String userId){
        ZIMKitRouter.toMessageActivity(this, userId, ZIMKitConversationType.ZIMKitConversationTypePeer);
    }
}