package com.example.retrofittraining.ui;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;

import com.example.retrofittraining.data.PokemonRepository;

import java.util.Random;

public class MainViewModel extends ViewModel {
    @NonNull
    private final PokemonRepository pokemonRepository;


    @NonNull
    private final MutableLiveData<Integer> randomIntegerMutableLiveData;

    int randomNumber = new Random().nextInt(898) + 1;

    @NonNull
    private final MutableLiveData<Boolean> isLoading = new MutableLiveData<>(false);


    public MainViewModel(
        @NonNull PokemonRepository pokemonRepository) {
        this.pokemonRepository = pokemonRepository;
        // Get a random Pokemon name from the repository

        randomIntegerMutableLiveData = new MutableLiveData<>(randomNumber);

    }

    public LiveData<PokemonViewState> getPokemonNameViewState() {
        isLoading.setValue(true);
        return Transformations.switchMap(randomIntegerMutableLiveData, randomInt -> {
            isLoading.setValue(false);
            return Transformations.map(pokemonRepository.getRandomPokemonName(randomInt),
                pokemon -> new PokemonViewState(pokemon.getName(), pokemon.getImageUrl(), pokemon.isResponseReceived()));
        });
    }

    public void onClickGenerateRandomPokemon() {
        int newRandomInt = new Random().nextInt(898) + 1;
        randomIntegerMutableLiveData.setValue(newRandomInt);
    }

    public LiveData<Boolean> isLoading() {
        return isLoading;
    }
}

