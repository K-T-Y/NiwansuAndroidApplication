package com.example.niwansu_android_application.screens.patient.fragments;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.niwansu_android_application.R;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.SupportMapFragment;

public class MapFragment extends Fragment {



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_map,container,false);



        //MAP VIEW setup
        MapViewFragment mapViewFragment = new MapViewFragment();


        getParentFragmentManager().beginTransaction().replace(R.id.framelayout, mapViewFragment).commit();

        return view;
    }
}