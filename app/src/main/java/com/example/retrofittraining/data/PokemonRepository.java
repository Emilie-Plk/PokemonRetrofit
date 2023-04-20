package com.example.retrofittraining.data;

import android.content.res.Resources;
import android.util.LruCache;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.retrofittraining.model.PokemonInfo;
import com.example.retrofittraining.model.PokemonResponse;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Singleton;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

@Singleton
public class PokemonRepository {

    private final PokemonAPI pokemonAPI;

                        /*******   Cf. Session Nino 18/04/2023     *******/
    // HashMap => bien en perf
    // HashMap peut contenir une quantité illimitée d'objets avec des clés potentiellement
    // finies telles que les entiers, les chaînes de caractères, les énumérations, etc.
    // Chaque objet stocké dans un HashMap est associé à une clé unique qui est utilisée pour le récupérer à partir de la carte.

    private final Map<Integer, PokemonInfo> pokemonDetailCache = new HashMap<>();

    // SINON : LRUcache (List Recently Used) = "le moins utilisé" (les requêtes les moins get vont être trashées)
    // ici pas trop de sens car appels aléatoires
    private final LruCache<Integer, PokemonInfo> pokemonInfoLruCache = new LruCache<>(8 * 1024 * 1024);

    // Also we have DiskLruCache => more precise, where we want to store data, we have a double hookup

    // So LruCache => in-memory caching
    // and DiskLruCache => store data on the disk


    @Inject
    public PokemonRepository(PokemonAPI pokemonAPI) {
        this.pokemonAPI = pokemonAPI;
    }

    public LiveData<PokemonInfoWrapper> getRandomPokemonName(int randomInt) {
        MutableLiveData<PokemonInfoWrapper> pokemonInfoWrapperMutableLiveData = new MutableLiveData<>();

        PokemonInfo cachedPokemonInfo = pokemonDetailCache.get(randomInt);

        if (cachedPokemonInfo == null) {


            pokemonInfoWrapperMutableLiveData.setValue(new PokemonInfoWrapper.Loading());

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

                            PokemonInfo pokemonInfo = new PokemonInfo(
                                name,
                                imageUrl,
                                type,
                                true
                            );

                            pokemonDetailCache.put(randomInt, pokemonInfo); // on gère le cache

                            pokemonInfoWrapperMutableLiveData.setValue(
                                new PokemonInfoWrapper.Success(
                                   pokemonInfo
                                )
                            );
                        } else {
                            pokemonInfoWrapperMutableLiveData.setValue(
                                new PokemonInfoWrapper.Error(
                                    new Resources.NotFoundException()
                                )
                            );
                        }
                    }

                    @Override
                    public void onFailure(@NonNull Call<PokemonResponse> call, @NonNull Throwable t) {
                        t.printStackTrace();
                        pokemonInfoWrapperMutableLiveData.setValue(new PokemonInfoWrapper.Error(t) {
                        });
                    }
                }
            );
        } else {
           // pokemonInfoWrapperMutableLiveData.setValue(cachedPokemonInfo); là du coup c'est le bordel avec le Warper qui était
            // là pour l'exemple mais bon ici en gros
        }
        return pokemonInfoWrapperMutableLiveData;
    }

}
