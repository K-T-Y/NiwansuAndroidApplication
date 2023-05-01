package com.example.niwansu_android_application.screens.doctor.fragments;

import static android.content.Context.MODE_PRIVATE;
import static com.example.niwansu_android_application.core.Constants.KEY_FIRST_NAME;
import static com.example.niwansu_android_application.core.Constants.KEY_LAST_NAME;
import static com.example.niwansu_android_application.core.Constants.KEY_PROFILE_PIC;
import static com.example.niwansu_android_application.core.Constants.PREFERENCE_NAME;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.niwansu_android_application.R;
import com.example.niwansu_android_application.core.Constants;
import com.example.niwansu_android_application.screens.doctor.activities.ProfileActivity;

import de.hdodenhof.circleimageview.CircleImageView;


public class HomeFragment extends Fragment {

View view;
TextView txt_name;
CircleImageView pro_pic;
    SharedPreferences sharedPreferences;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view= inflater.inflate(R.layout.fragment_home2, container, false);
        txt_name=view.findViewById( R.id.txt_name);
        pro_pic=view.findViewById(R.id.pro_pic);
        sharedPreferences =this.getActivity().getSharedPreferences(PREFERENCE_NAME, MODE_PRIVATE);
        String name = "Dr."+(sharedPreferences.getString(Constants.KEY_FIRST_NAME,null))+" "+(sharedPreferences.getString(Constants.KEY_LAST_NAME,null));
        txt_name.setText(name);
        //get profile Image
        if(sharedPreferences.getString(Constants.KEY_PROFILE_PIC, null)!=null)
        {
            String base64 = sharedPreferences.getString(Constants.KEY_PROFILE_PIC, null);
            byte[] imgdate = Base64.decode(base64, Base64.DEFAULT);
//                 String text = new String(imgdate, StandardCharsets.UTF_8);
            Bitmap decodedByte = BitmapFactory.decodeByteArray(imgdate, 0, imgdate.length);
            pro_pic.setImageBitmap(decodedByte);
        }
        pro_pic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getActivity(), ProfileActivity.class);
                intent.addFlags((Intent.FLAG_ACTIVITY_NEW_TASK));
                startActivity(intent);
            }
        });
        return view;
    }
}