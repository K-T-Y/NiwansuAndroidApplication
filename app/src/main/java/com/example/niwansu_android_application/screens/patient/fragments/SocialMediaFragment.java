package com.example.niwansu_android_application.screens.patient.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.example.niwansu_android_application.R;


public class SocialMediaFragment extends Fragment {

//    SharedPreferences sharedPreferences;
//    private List<Users> users;
//
//
//
//    private RecyclerView.LayoutManager layoutManager;
//    private NetworkService apiInterface;
//    private AdapterNotification adapterNotification;
//
//    RecyclerView recyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_social_media, container, false);
        return view;
        // Inflate the layout for this fragment
//        View view = inflater.inflate(R.layout.fragment_notification, container, false);
//        recyclerView = view.findViewById(R.id.recyclerView);
//        layoutManager = new LinearLayoutManager(getContext());
//        recyclerView.setLayoutManager(layoutManager);
//        recyclerView.setHasFixedSize(true);


//        fetchNotification();
//        return view;

//
//
//    }
//
//    @Override
//    public void onDestroy() {
//        super.onDestroy();
//
//      //  ZegoUIKitPrebuiltCallInvitationService.unInit();
//    }
//
//
//    public void fetchNotification(){
//        sharedPreferences =this.getActivity().getSharedPreferences(PREFERENCE_NAME, MODE_PRIVATE);
//        String patientid = sharedPreferences.getString(KEY_NAME,null);
//        apiInterface = NetworkClient.getClient().create(NetworkService.class);
//
//        Call<List<Users>> call = apiInterface.getNotification(patientid);
//        call.enqueue(new Callback<List<Users>>() {
//            @Override
//            public void onResponse(Call<List<Users>> call, Response<List<Users>> response) {
//                users = response.body();
//                adapterNotification = new AdapterNotification(users, getContext());
//                recyclerView.setAdapter(adapterNotification);
//                adapterNotification.notifyDataSetChanged();
//                //Toast.makeText(getContext(), "asdas" , Toast.LENGTH_SHORT).show();
//            }
//
//            @Override
//            public void onFailure(Call<List<Users>> call, Throwable t)
//            {
//                Toast.makeText(getContext(), "Error\n"+t.toString(), Toast.LENGTH_LONG).show();
//            }
//        });
//    }

    }
}