package com.example.niwansu_android_application.screens.doctor.fragments;

import static android.content.Context.MODE_PRIVATE;
import static com.example.niwansu_android_application.core.Constants.KEY_EMAIL;
import static com.example.niwansu_android_application.core.Constants.KEY_FIRST_NAME;
import static com.example.niwansu_android_application.core.Constants.KEY_LAST_NAME;
import static com.example.niwansu_android_application.core.Constants.PREFERENCE_NAME;

import android.app.Application;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.niwansu_android_application.R;
import com.example.niwansu_android_application.core.AdapterPatientAppointments;
import com.example.niwansu_android_application.core.AppointmentsList;
import com.example.niwansu_android_application.core.Constants;
import com.example.niwansu_android_application.core.NetworkClient;
import com.example.niwansu_android_application.core.NetworkService;
import com.zegocloud.uikit.prebuilt.call.config.ZegoNotificationConfig;
import com.zegocloud.uikit.prebuilt.call.invite.ZegoUIKitPrebuiltCallInvitationConfig;
import com.zegocloud.uikit.prebuilt.call.invite.ZegoUIKitPrebuiltCallInvitationService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class AppointmentsFragment extends Fragment {


    SharedPreferences sharedPreferences;
    private List<AppointmentsList> appointments;

    TextView txtSeeAll;
    CalendarView calendarView;
    private RecyclerView.LayoutManager layoutManager;
    private NetworkService apiInterface;
    private AdapterPatientAppointments adapterNotification;
    RecyclerView recyclerView;
    View view;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view =inflater.inflate(R.layout.fragment_appointments, container, false);
        recyclerView = view.findViewById(R.id.recyclerView);
        layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        txtSeeAll = view.findViewById(R.id.txtseeAll);
        recyclerView.setHasFixedSize(true);
        calendarView = view.findViewById(R.id.calender);


        fetchNotification();

        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView calendarView, int year, int month, int dayOfMonth) {

                String date = String.format("%04d/%02d/%02d", year, month + 1, dayOfMonth);


                fetchNotificationondate(date);
            }
        });

        txtSeeAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // txtSeeAll.setBackground(Drawa);
                fetchNotification();
            }
        });
        return view;
    }

    public void fetchNotification(){
        sharedPreferences =this.getActivity().getSharedPreferences(PREFERENCE_NAME, MODE_PRIVATE);
        String name = "Dr."+(sharedPreferences.getString(Constants.KEY_FIRST_NAME,null))+" "+(sharedPreferences.getString(Constants.KEY_LAST_NAME,null));
        System.out.println(name);
        apiInterface = NetworkClient.getClient().create(NetworkService.class);

        Call<List<AppointmentsList>> call = apiInterface.getAppointmentsDoctor(name);
        call.enqueue(new Callback<List<AppointmentsList>>() {
            @Override
            public void onResponse(Call<List<AppointmentsList>> call, Response<List<AppointmentsList>> response) {
                appointments = response.body();
             //   System.out.println(appointments.get(0).getBookeddate());
                adapterNotification = new AdapterPatientAppointments(appointments, getContext());
                recyclerView.setAdapter(adapterNotification);
                adapterNotification.notifyDataSetChanged();
                //Toast.makeText(getContext(), "asdas" , Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<List<AppointmentsList>> call, Throwable t)
            {
                Toast.makeText(getContext(), "Error\n"+t.toString(), Toast.LENGTH_LONG).show();
                System.out.println(t.toString());
            }
        });
    }


    public void fetchNotificationondate(String date){
        sharedPreferences =this.getActivity().getSharedPreferences(PREFERENCE_NAME, MODE_PRIVATE);
        String name = "Dr."+(sharedPreferences.getString(Constants.KEY_FIRST_NAME,null))+" "+(sharedPreferences.getString(Constants.KEY_LAST_NAME,null));

        apiInterface = NetworkClient.getClient().create(NetworkService.class);

        Call<List<AppointmentsList>> call = apiInterface.getAppointmentsondateDoc(name,date);
        call.enqueue(new Callback<List<AppointmentsList>>() {
            @Override
            public void onResponse(Call<List<AppointmentsList>> call, Response<List<AppointmentsList>> response) {
                appointments = response.body();
                adapterNotification = new AdapterPatientAppointments(appointments, getContext());
                recyclerView.setAdapter(adapterNotification);
                adapterNotification.notifyDataSetChanged();
                //Toast.makeText(getContext(), "asdas" , Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<List<AppointmentsList>> call, Throwable t)
            {
                Toast.makeText(getContext(), "Error\n"+t.toString(), Toast.LENGTH_LONG).show();
            }
        });
    }


}