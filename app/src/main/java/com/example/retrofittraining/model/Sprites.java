package com.example.retrofittraining.model;

import androidx.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

public class Sprites {

    @SerializedName("front_default")
    private String frontDefault;


    public String getFrontDefault() {
        return frontDefault;
    }


    @NonNull
    @Override
    public String toString() {
        return
            "Sprites{" +
                ",front_default = '" + frontDefault + '\'' +
                "}";
    }
}