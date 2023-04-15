package com.example.retrofittraining.data;

import com.example.retrofittraining.model.PokemonResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface PokemonAPI {

    @GET("pokemon/{id}/")
    Call<PokemonResponse> getPokemonById(@Path("id") int id);

}
