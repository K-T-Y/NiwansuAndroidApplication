package com.example.niwansu_android_application.screens.patient.activities;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.example.niwansu_android_application.R;
import com.example.niwansu_android_application.screens.doctor.activities.DoctorMainActivity;
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

    FrameLayout layout;
    public static MainActivity sInstance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        layout = findViewById(R.id.container);
//        sInstance = this;
//        long appId =1237621987 ;    // The AppID you get from ZEGOCLOUD Admin Console.
//        String appSign ="b40727d97be6263d11bf3e6b1847022a036a8d9b0c3cf66a766072668cedc876" ;    // The App Sign you get from ZEGOCLOUD Admin Console.
//        ZIMKit.initWith(getApplication(),appId,appSign);
//        // Online notification for the initialization (use the following code if this is needed).
//        ZIMKit.initNotifications();


        sInstance = this;
        long appId =76477047 ;    // The AppID you get from ZEGOCLOUD Admin Console.
        String appSign ="b2fe26ab62fcd26ede05c1635ed448ad21b679a0a3249401812951ff06ba1dd2" ;    // The App Sign you get from ZEGOCLOUD Admin Console.
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
                       // getSupportFragmentManager().beginTransaction().replace(R.id.container, chatFragment).commit();

                        CreatepopUpwindow();
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

    private void CreatepopUpwindow() {

        LayoutInflater inflater = (LayoutInflater) MainActivity.this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View popUpView = inflater.inflate(R.layout.premium_feature_popup, null);


        int width = ViewGroup.LayoutParams.MATCH_PARENT;
        int height = ViewGroup.LayoutParams.MATCH_PARENT;

        boolean focusable = true;
        PopupWindow popupWindow = new PopupWindow(popUpView, width, height, focusable);


        layout.post(new Runnable() {
            @Override
            public void run() {


                popupWindow.showAtLocation(layout, Gravity.BOTTOM, 0, 0);


            }
        });


        TextView Gotit;
        ImageView iconclose;

        Gotit = popUpView.findViewById(R.id.Gotit);
        iconclose = popUpView.findViewById(R.id.iconclose);

        Gotit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                popupWindow.dismiss();
                PremiumPaymentPopupWindow();


            }
        });
        iconclose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                popupWindow.dismiss();


            }
        });
//         and if you want to close popup when touch Screen
        popUpView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {


                popupWindow.dismiss();
                return true;

            }

        });

//        popUpView1.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//                fadePopup.dismiss();
//
//                popupWindow.dismiss();
//                return true;
//
//            }
//
//        });




    }

    private void PremiumPaymentPopupWindow() {
        LayoutInflater inflater = (LayoutInflater) MainActivity.this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View popUpView = inflater.inflate(R.layout.premium_feature_paymentgate_popup, null);

        int width = ViewGroup.LayoutParams.MATCH_PARENT;
        int height = ViewGroup.LayoutParams.MATCH_PARENT;


        boolean focusable = true;

        PopupWindow popupWindow = new PopupWindow(popUpView, width, height, focusable);
        layout.post(new Runnable() {
            @Override
            public void run() {

                popupWindow.showAtLocation(layout, Gravity.BOTTOM, 0, 0);


            }
        });

        TextView Gotit;
        ImageView iconclose;

        Gotit = popUpView.findViewById(R.id.Gotit);
        iconclose = popUpView.findViewById(R.id.iconClosepayementGate);

        Gotit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                popupWindow.dismiss();
                PremiumPaymentCardDetails();


            }
        });
        iconclose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                popupWindow.dismiss();

            }
        });
        popUpView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                popupWindow.dismiss();
                return true;
            }
        });

    }

    private void PremiumPaymentCardDetails() {
        LayoutInflater inflater = (LayoutInflater) MainActivity.this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View popUpView = inflater.inflate(R.layout.premium_payment_details_enter_popup, null);


        int width = ViewGroup.LayoutParams.MATCH_PARENT;
        int height = ViewGroup.LayoutParams.MATCH_PARENT;

        boolean focusable = true;

        PopupWindow popupWindow = new PopupWindow(popUpView, width, height, focusable);
        layout.post(new Runnable() {
            @Override
            public void run() {

                popupWindow.showAtLocation(layout, Gravity.BOTTOM, 0, 0);


            }
        });

        TextView Gotit;
        ImageView iconclose;

        Gotit = popUpView.findViewById(R.id.Gotit);
        iconclose = popUpView.findViewById(R.id.iconclose);

        Gotit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                popupWindow.dismiss();
                PremiumPaymentDone();


            }
        });
        iconclose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                popupWindow.dismiss();

            }
        });
        popUpView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                popupWindow.dismiss();
                return true;
            }
        });

    }


    private void PremiumPaymentDone() {
        LayoutInflater inflater = (LayoutInflater) MainActivity.this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View popUpView = inflater.inflate(R.layout.premium_payment_approved_popup, null);


        int width = ViewGroup.LayoutParams.MATCH_PARENT;
        int height = ViewGroup.LayoutParams.MATCH_PARENT;

        boolean focusable = true;
        PopupWindow popupWindow = new PopupWindow(popUpView, width, height, focusable);
        layout.post(new Runnable() {
            @Override
            public void run() {

                popupWindow.showAtLocation(layout, Gravity.BOTTOM, 0, 0);


            }
        });
        TextView Gotit;
        ImageView iconclose;
        Gotit = popUpView.findViewById(R.id.Gotit);
        iconclose = popUpView.findViewById(R.id.iconclose);

        Gotit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                popupWindow.dismiss();
                //      Intent i = new Intent(getActivity(), ForumActivity.class);


                getSupportFragmentManager().beginTransaction().replace(R.id.container, chatFragment).commit();

                // startActivity(i);

            }
        });
        iconclose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                popupWindow.dismiss();

            }
        });
        popUpView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                popupWindow.dismiss();
                return true;
            }
        });

    }




}