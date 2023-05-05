package com.example.niwansu_android_application.screens.patient.fragments;

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
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import com.example.niwansu_android_application.R;

import com.example.niwansu_android_application.core.Constants;
import com.example.niwansu_android_application.screens.doctor.activities.ProfileFragment;
import com.example.niwansu_android_application.screens.patient.activities.BookDoctorActivity;
import com.example.niwansu_android_application.screens.patient.activities.ChronicRespitoryDiseaseCheckActivity;
import com.example.niwansu_android_application.screens.patient.activities.DiabetesCheckActivity;
import com.example.niwansu_android_application.screens.patient.activities.HeartDiseaseCheckActivity;
import com.example.niwansu_android_application.screens.patient.activities.KidneyDiseaseCheckActivity;

import de.hdodenhof.circleimageview.CircleImageView;


public class HomeFragment extends Fragment {
    SharedPreferences sharedPreferences;
    CircleImageView pro_pic;

    CardView doctor1, doctor2, doctor3, doctor4, doctor5, doctor6, crdDiabetes,crdHeartDisease,crdKidney,crdChronic;


    TextView txtUsername;

    ProfileFragment profileActivity = new ProfileFragment();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_home, container, false);

        pro_pic = view.findViewById(R.id.pro_pic);
        doctor1 = view.findViewById(R.id.Doctor1);
        doctor2 = view.findViewById(R.id.Doctor2);
        doctor3 = view.findViewById(R.id.Doctor3);
        doctor4 = view.findViewById(R.id.Doctor4);
        doctor5 = view.findViewById(R.id.Doctor5);
        doctor6 = view.findViewById(R.id.Doctor6);
        txtUsername = view.findViewById(R.id.username);

        crdDiabetes = view.findViewById(R.id.crdDiabetes);
        crdHeartDisease = view.findViewById(R.id.crdHeartDisease);
        crdKidney = view.findViewById(R.id.crdKidneyDisease);
        crdChronic = view.findViewById(R.id.crdChronicRespitoryDisease);


        //set username

        sharedPreferences =this.getActivity().getSharedPreferences(PREFERENCE_NAME, MODE_PRIVATE);
        String name = (sharedPreferences.getString(Constants.KEY_FIRST_NAME,null))+" "+(sharedPreferences.getString(Constants.KEY_LAST_NAME,null));
        txtUsername.setText(name);


        //  txtName = view.findViewById(R.id.txtName);

        doctor1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), BookDoctorActivity.class);
                intent.putExtra("DoctorName", "Dr.Vihara Pathirage");
                intent.putExtra("DocImage", R.drawable.doctor1);
                startActivity(intent);
            }
        });
        doctor2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), BookDoctorActivity.class);
                intent.putExtra("DoctorName", "Dr.Mahesh Pathirana");
                intent.putExtra("DocImage", R.drawable.doctor2);
                startActivity(intent);
            }
        });
        doctor3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), BookDoctorActivity.class);
                intent.putExtra("DoctorName", "Dr.Anjela Colonne");
                intent.putExtra("DocImage", R.drawable.doctor3);
                startActivity(intent);
            }
        });
        doctor4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), BookDoctorActivity.class);
                intent.putExtra("DoctorName", "Dr.Sachini Gallage");
                intent.putExtra("DocImage", R.drawable.doctor4);
                startActivity(intent);
            }
        });
        doctor5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), BookDoctorActivity.class);
                intent.putExtra("DoctorName", "Dr.Himasha Perera");
                intent.putExtra("DocImage", R.drawable.doctor5);
                startActivity(intent);
            }
        });
        doctor6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), BookDoctorActivity.class);
                intent.putExtra("DoctorName", "Dr.Lalani Gamage");
                intent.putExtra("DocImage", R.drawable.doctor6);
                startActivity(intent);
            }
        });
        crdDiabetes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), DiabetesCheckActivity.class);

                startActivity(intent);
            }
        });
        crdHeartDisease .setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), HeartDiseaseCheckActivity.class);

                startActivity(intent);
            }
        });
        crdKidney.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), KidneyDiseaseCheckActivity.class);

                startActivity(intent);
            }
        });
        crdChronic .setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), ChronicRespitoryDiseaseCheckActivity.class);

                startActivity(intent);
            }
        });
        pro_pic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

               getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.container, profileActivity).commit();
                return;
            }
        });

        return view;
    }

    public void onViewCreated(View view, Bundle savedInstanceState) {


//get User Details - First and last name
        sharedPreferences = this.getActivity().getSharedPreferences(PREFERENCE_NAME, MODE_PRIVATE);
        String firstname = sharedPreferences.getString(KEY_FIRST_NAME, null);
        String lastname = sharedPreferences.getString(KEY_LAST_NAME, null);

//get profile Image
        String base64 = sharedPreferences.getString(KEY_PROFILE_PIC, null);
        byte[] imgdate = Base64.decode(base64, Base64.DEFAULT);
//                 String text = new String(imgdate, StandardCharsets.UTF_8);
        Bitmap decodedByte = BitmapFactory.decodeByteArray(imgdate, 0, imgdate.length);


        pro_pic.setImageBitmap(decodedByte);

        // txtName.setText(firstname+" "+lastname);


    }


}