package com.example.retrofittraining.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;
import java.util.Objects;

public class PokemonResponse {

    @SerializedName("sprites")
    private Sprites sprites;

    @SerializedName("name")
    private String name;

    @SerializedName("types")
    private List<TypesItem> types;

    public Sprites getSprites() {
        return sprites;
    }

    public String getName() {
        return name;
    }

    public List<TypesItem> getTypes() {
        return types;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PokemonResponse response = (PokemonResponse) o;
        return Objects.equals(sprites, response.sprites) && Objects.equals(name, response.name) && Objects.equals(types, response.types);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sprites, name, types);
    }
}