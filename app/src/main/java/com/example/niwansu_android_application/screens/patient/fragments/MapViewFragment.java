package com.example.niwansu_android_application.screens.patient.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.niwansu_android_application.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;


public class MapViewFragment extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_map_view,container,false);


        SupportMapFragment supportMapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.MY_MAP);
        supportMapFragment.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(@NonNull GoogleMap googleMap) {
//                   googleMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
//                       @Override
//                       public void onMapClick( LatLng latLng) {
//                           MarkerOptions markerOptions = new MarkerOptions();
//                           markerOptions.position(latLng);
//
//                           googleMap.clear();
//                           googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng,20));
//                           googleMap.addMarker(markerOptions);
//
//
//                       }
//                   });






                LatLng vihara = new LatLng(6.919976718611493, 79.87052391958046);
                googleMap.addMarker(new MarkerOptions()
                        .position(vihara)
                        .title("Dr.Vihara Pathirage"));
                googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(vihara,14));

                LatLng Mahesh = new LatLng(6.913976718611493, 79.86052391958046);
                googleMap.addMarker(new MarkerOptions()
                        .position(Mahesh)
                        .title("Dr.Mahesh Pathirana"));
                googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(Mahesh,14));

                LatLng Anjela = new LatLng(6.903976718611493, 79.85052391958046);
                googleMap.addMarker(new MarkerOptions()
                        .position(Anjela)
                        .title("Dr.Anjela Colonne"));
                googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(Anjela,14));

                LatLng Sachini = new LatLng(6.915976718611493, 79.87152391958046);
                googleMap.addMarker(new MarkerOptions()
                        .position(Sachini)
                        .title("Dr.Sachini Gallage"));
                googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(Sachini,14));

                LatLng Himasha = new LatLng(6.914976718611493, 79.86252391958046);
                googleMap.addMarker(new MarkerOptions()
                        .position(Himasha)
                        .title("Dr.Himasha Perera"));
                googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(Himasha,14));

                LatLng Udari = new LatLng(6.902976718611493, 79.85352391958046);
                googleMap.addMarker(new MarkerOptions()
                        .position(Udari)
                        .title("Dr.Udari Perera"));
                googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(Udari,14));



            }
        });
        return view;
    }

}