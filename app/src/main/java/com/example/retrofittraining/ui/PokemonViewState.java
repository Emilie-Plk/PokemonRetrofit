package com.example.retrofittraining.ui;

import androidx.annotation.NonNull;

import java.util.Objects;

public class PokemonViewState {

    @NonNull
    private final String name;


    @NonNull
    private final String imageUrl;

    @NonNull
    private final String type;

    private final boolean isResponseReceived;


    public PokemonViewState(@NonNull String name, @NonNull String imageUrl, @NonNull String type, boolean isResponseReceived) {
        this.name = name;
        this.imageUrl = imageUrl;
        this.type = type;
        this.isResponseReceived = isResponseReceived;
    }

    @NonNull
    public String getName() {
        return name;
    }

    @NonNull
    public String getImageUrl() {
        return imageUrl;
    }

    @NonNull
    public String getType() {
        return type;
    }


    public boolean isResponseReceived() {
        return isResponseReceived;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PokemonViewState that = (PokemonViewState) o;
        return isResponseReceived == that.isResponseReceived && name.equals(that.name) && imageUrl.equals(that.imageUrl) && type.equals(that.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, imageUrl, type, isResponseReceived);
    }

    @Override
    public String toString() {
        return "PokemonViewState{" +
            "name='" + name + '\'' +
            ", imageUrl='" + imageUrl + '\'' +
            ", type='" + type + '\'' +
            ", isResponseReceived=" + isResponseReceived +
            '}';
    }
}
