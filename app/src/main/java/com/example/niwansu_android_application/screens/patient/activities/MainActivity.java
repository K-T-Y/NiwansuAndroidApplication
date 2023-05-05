package com.example.niwansu_android_application.screens.patient.activities;


import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;

import com.example.niwansu_android_application.R;
import com.example.niwansu_android_application.screens.doctor.activities.ProfileFragment;
import com.example.niwansu_android_application.screens.doctor.fragments.FeedFragment;
import com.example.niwansu_android_application.screens.patient.fragments.CalenderFragment;
import com.example.niwansu_android_application.screens.patient.fragments.ChatFragment;
import com.example.niwansu_android_application.screens.patient.fragments.HomeFragment;
import com.example.niwansu_android_application.screens.patient.fragments.MapFragment;
import com.example.niwansu_android_application.screens.patient.fragments.MenuFragment;
import com.example.niwansu_android_application.screens.patient.fragments.SocialMediaFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.zegocloud.zimkit.services.ZIMKit;


public class MainActivity extends AppCompatActivity {

    private Button btn;
    private TextView txtLinkLogin;


    BottomNavigationView bottomNavigationView;
    HomeFragment homeFragment = new HomeFragment();

    SocialMediaFragment socialMediaFragment = new SocialMediaFragment();

    MenuFragment menuFragment = new MenuFragment();

    CalenderFragment calenderFragment = new CalenderFragment();

    MapFragment mapFragment = new MapFragment();

    FeedFragment feedFragment = new FeedFragment();
    ChatFragment chatFragment = new ChatFragment();

    ProfileFragment profileActivity = new ProfileFragment();

    public static MainActivity sInstance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bottomNavigationView = findViewById(R.id.bottomNavigationView);

        sInstance = this;
        long appId =1237621987 ;    // The AppID you get from ZEGOCLOUD Admin Console.
        String appSign ="b40727d97be6263d11bf3e6b1847022a036a8d9b0c3cf66a766072668cedc876" ;    // The App Sign you get from ZEGOCLOUD Admin Console.
        ZIMKit.initWith(getApplication(),appId,appSign);
        // Online notification for the initialization (use the following code if this is needed).
        ZIMKit.initNotifications();


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

                        getSupportFragmentManager().beginTransaction().replace(R.id.container, chatFragment).commit();


                        return;


                    case R.id.map:
                        getSupportFragmentManager().beginTransaction().replace(R.id.container, mapFragment).commit();
                        return;

                    case R.id.calender:
                        getSupportFragmentManager().beginTransaction().replace(R.id.container, calenderFragment).commit();
                        return;

                }
                return;
            }
        });




    }




}