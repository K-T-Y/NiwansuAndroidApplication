package com.example.niwansu_android_application.core;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.niwansu_android_application.R;


import java.util.List;

public class AdapterPatientAppointments extends RecyclerView.Adapter <AdapterPatientAppointments.MyViewHolder>{

    private List<AppointmentsList> appointments;
    private Context context;

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



    }

    @Override
    public int getItemCount() {
        return appointments.size();
    }
    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView appointmentNo,Date,doctor,time,status;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            appointmentNo = itemView.findViewById(R.id.appointmentNo);
            Date = itemView.findViewById(R.id.Date);
            doctor = itemView.findViewById(R.id.doctor);
            time = itemView.findViewById(R.id.time);
            status = itemView.findViewById(R.id.status);




        }


    }




}
