package com.example.retrofittraining.ui;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.retrofittraining.ViewModelFactory;
import com.example.retrofittraining.databinding.MainActivityBinding;

public class MainActivity extends AppCompatActivity {

    private MainActivityBinding binding;

    private MainViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = MainActivityBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        viewModel = new ViewModelProvider(this, ViewModelFactory.getInstance()).get(MainViewModel.class);

        viewModel.getPokemonNameViewState().observe(this, pokemonViewState -> {
                binding.pokemonName.setText(pokemonViewState.getName());
                Glide.with(this)
                    .load(pokemonViewState.getImageUrl())
                    .apply(new RequestOptions().override(400, 400))
                    .centerCrop()
                    .into(binding.pokemonImage);
                if (pokemonViewState.isResponseReceived()) {
                    // not so great but hey
                    binding.progressBar.setVisibility(View.GONE);
                } else {
                    binding.progressBar.setVisibility(View.VISIBLE);
                }
            }
        );


        binding.randomPokemonBtn.setOnClickListener(v -> viewModel.onClickGenerateRandomPokemon());
    }

}