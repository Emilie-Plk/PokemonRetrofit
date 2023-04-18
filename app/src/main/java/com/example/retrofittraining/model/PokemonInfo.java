package com.example.retrofittraining.model;

import androidx.annotation.NonNull;

import java.util.Objects;

public class PokemonInfo {
    private final String name;
    private final String imageUrl;
    private final String type;
    private final boolean isResponseReceived;

    public PokemonInfo(
        @NonNull String name,
        @NonNull String imageUrl,
        @NonNull String type,
        boolean isResponseReceived
    ) {
        this.name = name;
        this.imageUrl = imageUrl;
        this.isResponseReceived = isResponseReceived;
        this.type = type;
    }

    public @NonNull String getName() {
        return name;
    }

    public @NonNull String getImageUrl() {
        return imageUrl;
    }

    public @NonNull String getType() {
        return type;
    }

    public boolean isResponseReceived() {
        return isResponseReceived;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PokemonInfo that = (PokemonInfo) o;
        return isResponseReceived == that.isResponseReceived && Objects.equals(name, that.name) && Objects.equals(imageUrl, that.imageUrl) && Objects.equals(type, that.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, imageUrl, type, isResponseReceived);
    }

    @NonNull
    @Override
    public String toString() {
        return "PokemonInfo{" +
            "name='" + name + '\'' +
            ", imageUrl='" + imageUrl + '\'' +
            ", type='" + type + '\'' +
            ", isResponseReceived=" + isResponseReceived +
            '}';
    }
}