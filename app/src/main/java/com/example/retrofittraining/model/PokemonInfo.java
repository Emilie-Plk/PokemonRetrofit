package com.example.retrofittraining.model;

import androidx.annotation.NonNull;

public class PokemonInfo {
    private final String name;
    private final String imageUrl;
    private final boolean isResponseReceived;

    public PokemonInfo(@NonNull String name, @NonNull String imageUrl, boolean isResponseReceived) {
        this.name = name;
        this.imageUrl = imageUrl;
        this.isResponseReceived = isResponseReceived;
    }

    public String getName() {
        return name;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public boolean isResponseReceived() {
        return isResponseReceived;
    }
}