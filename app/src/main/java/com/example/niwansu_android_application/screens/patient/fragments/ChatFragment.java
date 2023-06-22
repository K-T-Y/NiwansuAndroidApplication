package com.example.niwansu_android_application.screens.patient.fragments;

import static android.content.Context.MODE_PRIVATE;
import static com.example.niwansu_android_application.core.Constants.PREFERENCE_NAME;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.niwansu_android_application.core.AdapterGetUsers;
import com.example.niwansu_android_application.core.NetworkClient;
import com.example.niwansu_android_application.core.NetworkService;
import com.example.niwansu_android_application.core.Users;

import com.example.niwansu_android_application.R;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChatFragment extends Fragment {
    SharedPreferences sharedPreferences;

    private List<Users> users;
    private NetworkService apiInterface;

    private RecyclerView.LayoutManager layoutManager;
    RecyclerView recyclerView;

    private AdapterGetUsers adapterDoctorList;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // buttonClick();
        fetchDoctors();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_chat, container, false);


        recyclerView = view.findViewById(R.id.recyclerViewDoctorList);
        layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        return view;
    }



    public void fetchDoctors(){
        sharedPreferences =this.getActivity().getSharedPreferences(PREFERENCE_NAME, MODE_PRIVATE);
        String usercategory = "doctor";

        apiInterface = NetworkClient.getClient().create(NetworkService.class);

        Call<List<Users>> call = apiInterface.getDoctorList(usercategory);
        call.enqueue(new Callback<List<Users>>() {
            @Override
            public void onResponse(Call<List<Users>> call, Response<List<Users>> response) {
                users = response.body();
                adapterDoctorList = new AdapterGetUsers(users, getContext());
                recyclerView.setAdapter(adapterDoctorList);
                adapterDoctorList.notifyDataSetChanged();

            }

            @Override
            public void onFailure(Call<List<Users>> call, Throwable t)
            {
                Toast.makeText(getContext(), "Error\n"+t.toString(), Toast.LENGTH_LONG).show();
            }
        });
    }
}