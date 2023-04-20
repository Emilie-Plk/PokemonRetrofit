package com.example.retrofittraining.data;

import com.example.retrofittraining.model.PokemonInfo;

public abstract class PokemonInfoWrapper {

    public static class Loading extends PokemonInfoWrapper {
// what we want to do in case of Loading
    }

    public static class Success extends PokemonInfoWrapper {

        private final PokemonInfo pokemonInfo;

        public Success(PokemonInfo pokemonInfo) {
            this.pokemonInfo = pokemonInfo;
        }

        public PokemonInfo getPokemonInfo() {
            return pokemonInfo;
        }
    }

    public static class Error extends PokemonInfoWrapper {
        private final Throwable throwable;

        public Error(Throwable throwable) {
            this.throwable = throwable;
        }


        public Throwable getThrowable() {
            return throwable;
        }
    }

}
