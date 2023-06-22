package com.example.niwansu_android_application.screens.doctor.activities;

import static com.example.niwansu_android_application.core.Constants.KEY_FIRST_NAME;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.example.niwansu_android_application.R;
import com.example.niwansu_android_application.core.Constants;
import com.example.niwansu_android_application.screens.doctor.fragments.AppointmentsFragment;
import com.example.niwansu_android_application.screens.doctor.fragments.DoctorChatFragment;
import com.example.niwansu_android_application.screens.doctor.fragments.FeedFragment;
import com.example.niwansu_android_application.screens.doctor.fragments.HomeFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.zegocloud.zimkit.services.ZIMKit;

import im.zego.zim.enums.ZIMErrorCode;

public class DoctorMainActivity extends AppCompatActivity {
    BottomNavigationView bottomNavigationView;

    DoctorChatFragment doctorChatFragment=new DoctorChatFragment();

    ProfileFragment profileActivity = new ProfileFragment();

    FrameLayout layout;

    public static DoctorMainActivity sInstance;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_main);
        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        layout = findViewById(R.id.container);
        sInstance = this;
        long appId =76477047 ;    // The AppID you get from ZEGOCLOUD Admin Console.
        String appSign ="b2fe26ab62fcd26ede05c1635ed448ad21b679a0a3249401812951ff06ba1dd2" ;    // The App Sign you get from ZEGOCLOUD Admin Console.
        ZIMKit.initWith(getApplication(),appId,appSign);
        // Online notification for the initialization (use the following code if this is needed).
        ZIMKit.initNotifications();
        AppointmentsFragment appointmentsFragment=new AppointmentsFragment();
        HomeFragment homeFragment=new HomeFragment();
        FeedFragment feedFragment=new FeedFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.container, homeFragment).commit();

        bottomNavigationView.setOnItemReselectedListener(new NavigationBarView.OnItemReselectedListener() {
            @Override
            public void onNavigationItemReselected(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.home:
                        getSupportFragmentManager().beginTransaction().replace(R.id.container, homeFragment).commit();
                        return;


                    case R.id.socialmedia:
                        getSupportFragmentManager().beginTransaction().replace(R.id.container, feedFragment).commit();
                        return;


                    case R.id.chat:
                      //  buttonClick();
                         getSupportFragmentManager().beginTransaction().replace(R.id.container, doctorChatFragment).commit();


                        return;


                    case R.id.myMenu:
                        getSupportFragmentManager().beginTransaction().replace(R.id.container,profileActivity ).commit();
                        return;

                    case R.id.calender:
                        getSupportFragmentManager().beginTransaction().replace(R.id.container, appointmentsFragment).commit();
                        return;

                }
                return;
            }
        });

    }

    public void buttonClick() {
        SharedPreferences sharedPreferences = this.getSharedPreferences(Constants.PREFERENCE_NAME, MODE_PRIVATE);
        // userId and userName: 1 to 32 characters, can only contain digits, letters, and the following special characters: '~', '!', '@', '#', '$', '%', '^', '&', '*', '(', ')', '_', '+', '=', '-', '`', ';', '’', ',', '.', '<', '>', '/', '\'
        //  String userId = sharedPreferences.getString(KEY_LAST_NAME, null);; // Your ID as a user.
        String userId = "Patient";
        String userName =  sharedPreferences.getString(KEY_FIRST_NAME, null);; // You name as a user.
        // String userName = "Kavindu";
        String userAvatar =   "https://storage.zego.im/IMKit/avatar/avatar-1.png";; // The image you set as the user avatar must be network image. e.g., https://storage.zego.im/IMKit/avatar/avatar-0.png
        connectUser(userId, userName,userAvatar);
    }
    public void connectUser(String userId, String userName,String userAvatar) {
        // Logs in.
        ZIMKit.connectUser(userId,userName,userAvatar, errorInfo -> {
            if (errorInfo.code == ZIMErrorCode.SUCCESS) {
                // Operation after successful login. You will be redirected to other modules only after successful login. In this sample code, you will be redirected to the conversation module.
                toConversationActivity();
            } else {

            }
        });
    }

    // Integrate the conversation list into your Activity as a Fragment
    private void toConversationActivity() {
        // Redirect to the conversation list (Activity) you created.
        getSupportFragmentManager().beginTransaction().replace(R.id.container,doctorChatFragment).commit();

    }







}