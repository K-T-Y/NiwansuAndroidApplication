package com.example.niwansu_android_application.core;


import com.google.gson.annotations.SerializedName;

import java.util.List;

public class UserDetailModel {
    @SerializedName("user_details")
    private List<Users> userDetails;

    public List<Users> getUserDetails() {
        return userDetails;
    }
}
