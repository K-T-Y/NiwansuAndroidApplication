package com.example.niwansu_android_application.core;

import com.google.gson.annotations.SerializedName;

public class AppointmentsList {
    @SerializedName("id")
    private int id;

    @SerializedName("doctorname")
    private String doctorname;

    @SerializedName("patientname")
    private String patientname;
    @SerializedName("bookeddate")
    private String bookeddate;
    @SerializedName("doctorapprovalstatus")
    private String doctorapprovalstatus;
    @SerializedName("time")
    private String time;


    public int getId() {
        return id;
    }

    public String getDoctorname() {
        return doctorname;
    }

    public String getPatientname() {
        return patientname;
    }

    public String getBookeddate() {
        return bookeddate;
    }

    public String getDoctorapprovalstatus() {
        return doctorapprovalstatus;
    }

    public String getTime() {
        return time;
    }
}
