package com.example.retrofittraining;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.retrofittraining.data.PokemonRepository;
import com.example.retrofittraining.data.RetrofitService;
import com.example.retrofittraining.ui.MainViewModel;

public class ViewModelFactory implements ViewModelProvider.Factory {

    private static ViewModelFactory factory;

    public static ViewModelFactory getInstance() {
        if (factory == null) {
            synchronized (ViewModelFactory.class) {
                if (factory == null) {
                    factory = new ViewModelFactory();
                }
            }
        }
        return factory;
    }

    private final PokemonRepository pokemonRepository = new PokemonRepository(
        RetrofitService.getPokemonApi()
    );

    private ViewModelFactory() {
    }

    @NonNull
    @Override
    @SuppressWarnings("unchecked")
    public <T extends ViewModel> T create(Class<T> modelClass) {
        if (modelClass.isAssignableFrom(MainViewModel.class)) {
            // We inject the Repository in the ViewModel constructor
            return (T) new MainViewModel(pokemonRepository);
        }
        throw new IllegalArgumentException("Unknown ViewModel class");
    }
}