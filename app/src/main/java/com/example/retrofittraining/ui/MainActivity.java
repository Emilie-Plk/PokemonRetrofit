package com.example.retrofittraining.ui;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.bumptech.glide.Glide;
import com.example.retrofittraining.R;
import com.example.retrofittraining.databinding.MainActivityBinding;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class MainActivity extends AppCompatActivity {

    private MainActivityBinding binding;

    private MainViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = MainActivityBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        viewModel = new ViewModelProvider(this).get(MainViewModel.class);

        viewModel.getPokemonNameViewState().observe(this, pokemonViewState -> {
                binding.pokemonName.setText(pokemonViewState.getName());

                binding.pokemonType.setText(getResources().getString(R.string.pokemon_type, pokemonViewState.getType()));

                Glide.with(this)
                    .load(pokemonViewState.getImageUrl())
                    .override(500, 500)
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