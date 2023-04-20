package com.example.retrofittraining.ui;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;

import com.example.retrofittraining.data.PokemonInfoWrapper;
import com.example.retrofittraining.data.PokemonRepository;
import com.example.retrofittraining.model.PokemonInfo;

import java.util.Random;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class MainViewModel extends ViewModel {
    @NonNull
    private final PokemonRepository pokemonRepository;

    @NonNull
    private final MutableLiveData<Integer> randomIntegerMutableLiveData = new MutableLiveData<>(new Random().nextInt(898) + 1);


    @Inject
    public MainViewModel(@NonNull PokemonRepository pokemonRepository) {
        this.pokemonRepository = pokemonRepository;
    }

    public LiveData<PokemonViewState> getPokemonNameViewState() {
        return Transformations.switchMap(randomIntegerMutableLiveData, randomInt -> {
                return Transformations.map(
                    pokemonRepository.getRandomPokemonName(randomInt), pokemonInfoWrapper -> {
                        if (pokemonInfoWrapper instanceof PokemonInfoWrapper.Loading) {
                            // PAS OUF EN JAVA, KOTLIN CA SERA PLUS SIMPLE
                            return new PokemonViewState(
                                "",
                                "",
                                "",
                                false
                            );
                        } else if (pokemonInfoWrapper instanceof PokemonInfoWrapper.Success) {
                            PokemonInfo pokemonInfo = ((PokemonInfoWrapper.Success) pokemonInfoWrapper).getPokemonInfo();
                            return new PokemonViewState(
                                pokemonInfo.getName(),
                                pokemonInfo.getImageUrl(),
                                pokemonInfo.getType(),
                                true
                            );
                        } else {
                            Throwable throwable = ((PokemonInfoWrapper.Error) pokemonInfoWrapper).getThrowable();
                        }
                        return new PokemonViewState(
                            "error",
                            "",
                            "",
                            true
                        );
                    }
                );
            }
        );
    }

    public void onClickGenerateRandomPokemon() {
        int newRandomInt = new Random().nextInt(898) + 1;
        randomIntegerMutableLiveData.setValue(newRandomInt);
    }
}

