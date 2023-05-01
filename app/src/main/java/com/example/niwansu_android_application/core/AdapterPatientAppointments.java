package com.example.niwansu_android_application.core;

import static android.content.Context.MODE_PRIVATE;
import static com.example.niwansu_android_application.core.Constants.PREFERENCE_NAME;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.niwansu_android_application.R;
import com.example.niwansu_android_application.models.changeStatusModel;


import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AdapterPatientAppointments extends RecyclerView.Adapter <AdapterPatientAppointments.MyViewHolder>{

    private List<AppointmentsList> appointments;
    private Context context;
    SharedPreferences sharedPreferences;
    public AdapterPatientAppointments(List<AppointmentsList> appointments, Context context) {
        this.appointments = appointments;
        this.context = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.drawer_item, parent, false);
        return new MyViewHolder(view);


    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.appointmentNo.setText("Appointment No : "+appointments.get(position).getId());
        holder.Date.setText("" + appointments.get(position).getBookeddate());
        holder.doctor.setText(""+appointments.get(position).getDoctorname());
        holder.time.setText("" + appointments.get(position).getTime());
        holder.status.setText(""+appointments.get(position).getDoctorapprovalstatus());
        sharedPreferences =context.getSharedPreferences(PREFERENCE_NAME, MODE_PRIVATE);
        String category=  sharedPreferences.getString(Constants.KEY_USER_CATEGORY,null);

        if(category.equals("doctor"))
        {
            if(appointments.get(position).getDoctorapprovalstatus().equals("Accepted"))
            {

                holder.btn_reject.setVisibility(View.INVISIBLE);
                holder.btn_accept.setVisibility(View.INVISIBLE);
            }
            else if(appointments.get(position).getDoctorapprovalstatus().equals("Rejected"))
             {
                holder.btn_reject.setVisibility(View.INVISIBLE);
                holder.btn_accept.setVisibility(View.INVISIBLE);
                holder.btn_video.setVisibility(View.INVISIBLE);
             }
            else
            {

                holder.btn_video.setVisibility(View.INVISIBLE);
            }
        }
        else {
            holder.btn_video.setVisibility(View.INVISIBLE);
            holder.btn_reject.setVisibility(View.INVISIBLE);
            holder.btn_accept.setVisibility(View.INVISIBLE);
        }

holder.btn_accept.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        NetworkService apiInterface;
        apiInterface = NetworkClient.getClient().create(NetworkService.class);
        Call<changeStatusModel> call = apiInterface.changeStatus(appointments.get(position).getId(),"Accepted");
        call.enqueue(new Callback<changeStatusModel>() {
            @Override
            public void onResponse(Call<changeStatusModel> call, Response<changeStatusModel> response) {
                if(response.body().getStatus().equals("ok"))
                {
                    Toast.makeText(context.getApplicationContext(),"Successfully Accepted",Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<changeStatusModel> call, Throwable t) {
                Toast.makeText(context.getApplicationContext(),"Error While Connecting",Toast.LENGTH_LONG).show();
            }
        });
    }
});

        holder.btn_reject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NetworkService apiInterface;
                apiInterface = NetworkClient.getClient().create(NetworkService.class);
                Call<changeStatusModel> call = apiInterface.changeStatus(appointments.get(position).getId(),"Rejected");
                call.enqueue(new Callback<changeStatusModel>() {
                    @Override
                    public void onResponse(Call<changeStatusModel> call, Response<changeStatusModel> response) {
                        if(response.body().getStatus().equals("ok"))
                        {
                            Toast.makeText(context.getApplicationContext(),"Successfully Rejected",Toast.LENGTH_LONG).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<changeStatusModel> call, Throwable t) {
                        Toast.makeText(context.getApplicationContext(),"Error While Connecting",Toast.LENGTH_LONG).show();
                    }
                });
            }
        });
    }

    @Override
    public int getItemCount() {
        return appointments.size();
    }
    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView appointmentNo,Date,doctor,time,status;
        Button btn_video,btn_accept,btn_reject;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            appointmentNo = itemView.findViewById(R.id.appointmentNo);
            Date = itemView.findViewById(R.id.Date);
            doctor = itemView.findViewById(R.id.doctor);
            time = itemView.findViewById(R.id.time);
            status = itemView.findViewById(R.id.status);

            btn_accept=itemView.findViewById(R.id.btn_accept);
            btn_reject=itemView.findViewById(R.id.btn_reject);
            btn_video=itemView.findViewById(R.id.btn_video);



        }


    }




}
