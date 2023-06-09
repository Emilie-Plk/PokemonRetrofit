package com.example.retrofittraining.model;

import androidx.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

public class Ability {

    @SerializedName("name")
    private String name;

    @SerializedName("url")
    private String url;

    public String getName() {
        return name;
    }

    public String getUrl() {
        return url;
    }

    @NonNull
    @Override
    public String toString() {
        return
            "Ability{" +
                "name = '" + name + '\'' +
                ",url = '" + url + '\'' +
                "}";
    }
}