package com.example.niwansu_android_application.core;

public class HorizontalUserModel {
    String image;
    String Username;

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String username) {
        Username = username;
    }

    public HorizontalUserModel(String image, String username) {
        this.image = image;
        Username = username;
    }
}
