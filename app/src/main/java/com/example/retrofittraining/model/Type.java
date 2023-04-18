package com.example.retrofittraining.model;

import androidx.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

public class Type {

    @SerializedName("name")
    private String name;

    public String getName() {
        return name;
    }

    @NonNull
    @Override
    public String toString() {
        return "Type{}";
    }
}