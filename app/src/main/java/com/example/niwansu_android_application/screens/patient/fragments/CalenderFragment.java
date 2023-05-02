package com.example.niwansu_android_application.screens.patient.fragments;

import static android.content.Context.MODE_PRIVATE;

import static com.example.niwansu_android_application.core.Constants.KEY_EMAIL;
import static com.example.niwansu_android_application.core.Constants.KEY_FIRST_NAME;
import static com.example.niwansu_android_application.core.Constants.KEY_LAST_NAME;
import static com.example.niwansu_android_application.core.Constants.PREFERENCE_NAME;

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
import com.example.niwansu_android_application.core.NetworkClient;
import com.example.niwansu_android_application.core.NetworkService;
import com.google.android.material.textfield.TextInputLayout;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CalenderFragment extends Fragment {
    SharedPreferences sharedPreferences;
    private List<AppointmentsList> appointments;

    TextView txtSeeAll,txtAccepted,txtDeclined,txtPending;
    CalendarView calendarView;
    private RecyclerView.LayoutManager layoutManager;
    private NetworkService apiInterface;
    private AdapterPatientAppointments adapterNotification;

    RecyclerView recyclerView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment



        View view = inflater.inflate(R.layout.fragment_calender, container, false);
        recyclerView = view.findViewById(R.id.recyclerView);
        txtAccepted = view.findViewById(R.id.txtAccepted);
        txtDeclined = view.findViewById(R.id.txtDeclined);
        txtPending = view.findViewById(R.id.txtPending);
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
        txtAccepted.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // txtSeeAll.setBackground(Drawa);
                fetchAccepted();
            }
        });
        txtDeclined.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // txtSeeAll.setBackground(Drawa);
                fetchDeclined();
            }
        });
        txtPending.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // txtSeeAll.setBackground(Drawa);
                fetchPending();
            }
        });



        return view;

    }

//    @Override
//    public void onDestroy() {
//        super.onDestroy();
//
//        ZegoUIKitPrebuiltCallInvitationService.unInit();
//    }


    public void fetchNotification(){
        sharedPreferences =this.getActivity().getSharedPreferences(PREFERENCE_NAME, MODE_PRIVATE);
        String patientemail = sharedPreferences.getString(KEY_EMAIL,null);

        apiInterface = NetworkClient.getClient().create(NetworkService.class);

        Call<List<AppointmentsList>> call = apiInterface.getAppointments(patientemail);
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


    public void fetchNotificationondate(String date){
        sharedPreferences =this.getActivity().getSharedPreferences(PREFERENCE_NAME, MODE_PRIVATE);
        String patientemail = sharedPreferences.getString(KEY_EMAIL,null);
        apiInterface = NetworkClient.getClient().create(NetworkService.class);

        Call<List<AppointmentsList>> call = apiInterface.getAppointmentsondate(patientemail,date);
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

    public void fetchAccepted(){
        sharedPreferences =this.getActivity().getSharedPreferences(PREFERENCE_NAME, MODE_PRIVATE);
        String patientemail = sharedPreferences.getString(KEY_EMAIL,null);

        apiInterface = NetworkClient.getClient().create(NetworkService.class);

        Call<List<AppointmentsList>> call = apiInterface.getfetchAccepted(patientemail);
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
    public void fetchDeclined(){
        sharedPreferences =this.getActivity().getSharedPreferences(PREFERENCE_NAME, MODE_PRIVATE);
        String patientemail = sharedPreferences.getString(KEY_EMAIL,null);

        apiInterface = NetworkClient.getClient().create(NetworkService.class);

        Call<List<AppointmentsList>> call = apiInterface.getfetchDeclined(patientemail);
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
    public void fetchPending(){
        sharedPreferences =this.getActivity().getSharedPreferences(PREFERENCE_NAME, MODE_PRIVATE);
        String patientemail = sharedPreferences.getString(KEY_EMAIL,null);

        apiInterface = NetworkClient.getClient().create(NetworkService.class);

        Call<List<AppointmentsList>> call = apiInterface.getfetchPending(patientemail);
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