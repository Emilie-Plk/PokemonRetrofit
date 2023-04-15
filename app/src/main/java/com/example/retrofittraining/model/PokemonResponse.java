package com.example.retrofittraining.model;

import androidx.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

public class PokemonResponse{

	@SerializedName("sprites")
	private Sprites sprites;

	@SerializedName("name")
	private String name;

	public Sprites getSprites(){
		return sprites;
	}

	public String getName(){
		return name;
	}

	@NonNull
	@Override
 	public String toString(){
		return 
			"PokemonResponse{" +
			",sprites = '" + sprites + '\'' +
			",name = '" + name + '\'' +
			"}";
		}
}