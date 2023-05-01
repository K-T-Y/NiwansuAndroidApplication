package com.example.niwansu_android_application.core;

import com.google.gson.annotations.SerializedName;

public class ResponseModel {
    @SerializedName("success")
    private String success;

    @SerializedName("message")
    private String message;

    public String getSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }
}
