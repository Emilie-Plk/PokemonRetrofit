package com.example.retrofittraining.data;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.retrofittraining.model.PokemonInfo;
import com.example.retrofittraining.model.PokemonResponse;

import javax.inject.Inject;
import javax.inject.Singleton;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

@Singleton
public class PokemonRepository {

    private final PokemonAPI pokemonAPI;

    @Inject
    public PokemonRepository(PokemonAPI pokemonAPI) {
        this.pokemonAPI = pokemonAPI;
    }

    public LiveData<PokemonInfo> getRandomPokemonName(int randomInt) {
        MutableLiveData<PokemonInfo> pokemonInfoMutableLiveData = new MutableLiveData<>();

        pokemonInfoMutableLiveData.setValue(new PokemonInfo(
            "", "", "", false)
        );

        pokemonAPI.getPokemonById(randomInt).enqueue(
            new Callback<PokemonResponse>() {
                @Override
                public void onResponse(
                    @NonNull Call<PokemonResponse> call,
                    @NonNull Response<PokemonResponse> response
                ) {
                    if (response.isSuccessful()) {
                        PokemonResponse pokemonResponse = response.body();
                        String name = pokemonResponse.getName();
                        String imageUrl = pokemonResponse.getSprites().getFrontDefault();

                        String type = pokemonResponse.getTypes().get(0).getType().getName();

                        pokemonInfoMutableLiveData.setValue(
                            new PokemonInfo(
                                name, imageUrl, type, true)
                        );
                    }
                }

                @Override
                public void onFailure(@NonNull Call<PokemonResponse> call, Throwable t) {
                    pokemonInfoMutableLiveData.setValue(null);
                }
            }
        );

        return pokemonInfoMutableLiveData;
    }

}
