package com.example.niwansu_android_application.core;

import com.google.gson.annotations.SerializedName;

public class Users {
    @SerializedName("id")
    private int id;
    @SerializedName("name")
    private String name;
    @SerializedName("lastname")
    private String lastname;

    @SerializedName("email")
    private String email;
    @SerializedName("password")
    private String password;
    @SerializedName("passwordtwo")
    private String passwordtwo;
    @SerializedName("mobile")
    private String mobile;
    @SerializedName("province")
    private String province;

    @SerializedName("district")
    private String district;
    @SerializedName("city")
    private String city;

    @SerializedName("date")
    private String date;
    @SerializedName("profilepicture")
    private String profilepicture;
    @SerializedName("usercategory")
    private String usercategory;

    @SerializedName("doctorname")
    private String doctorname;

    @SerializedName("bookeddate")
    private String bookeddate;

    @SerializedName("doctorapprovalstatus")
    private String doctorapprovalstatus;

    @SerializedName("occupation")
    private String occupation;
    @SerializedName("sex")
    private String sex;

    public String getSex() {
        return sex;
    }

    public String getOccupation() {
        return occupation;
    }

    public String getDoctorname() {
        return doctorname;
    }

    public String getBookeddate() {
        return bookeddate;
    }

    public String getDoctorapprovalstatus() {
        return doctorapprovalstatus;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getLastname() {
        return lastname;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getPasswordtwo() {
        return passwordtwo;
    }

    public String getMobile() {
        return mobile;
    }

    public String getProvince() {
        return province;
    }

    public String getDistrict() {
        return district;
    }

    public String getCity() {
        return city;
    }

    public String getDate() {
        return date;
    }

    public String getProfilepicture() {
        return profilepicture;
    }

    public String getUsercategory() {
        return usercategory;
    }
}
