package com.example.niwansu_android_application.screens.patient.fragments;

import static android.content.Context.MODE_PRIVATE;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.niwansu_android_application.R;

import com.google.android.material.bottomnavigation.BottomNavigationView;


public class MenuFragment extends Fragment {



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       View view = inflater.inflate(R.layout.fragment_menu, container, false);
//
//        username =view.findViewById(R.id.username);
//        btnlogout = view.findViewById(R.id.btnLogOut);
//        pro_pic_view = view.findViewById(R.id.pro_pic);
//
//        btnlogout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(getContext(), SplashScreen.class);
//                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//                intent.putExtra("EXIT", true);
//                startActivity(intent);
//            }
//        });
        return view;
    }

    public void onViewCreated(View view, Bundle savedInstanceState) {
//        sharedPreferences = getActivity().getSharedPreferences(PREFERENCE_NAME, MODE_PRIVATE);
//        String name = sharedPreferences.getString(KEY_NAME, null);
//        username.setText(name);
//
//        String base64 = sharedPreferences.getString(KEY_PROFILE_PIC,null);
//        byte[] imgdate = Base64.decode(base64,Base64.DEFAULT);
//        // String text = new String(imgdate, StandardCharsets.UTF_8);
//        Bitmap decodedByte = BitmapFactory.decodeByteArray(imgdate, 0, imgdate.length);
//
//        pro_pic_view.setImageBitmap(decodedByte);

    }
}