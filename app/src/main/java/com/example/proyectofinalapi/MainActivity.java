package com.example.proyectofinalapi;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.example.proyectofinalapi.modelos.Pokemon;
import com.example.proyectofinalapi.modelos.pokemonRespuesta;
import com.example.proyectofinalapi.pokemonApi.pokemonService;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "POKEDEX";

    private Retrofit retrofit;
    private int offset;
    private String ability;


    private boolean aptoParaCargar;

    private RecyclerView recyclerView;
    private ListaPokemonAdapter listaPokemonAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = (RecyclerView) findViewById(R.id.reciclerView);
        listaPokemonAdapter = new ListaPokemonAdapter(this);
        recyclerView.setAdapter(listaPokemonAdapter);
        recyclerView.setHasFixedSize(true);
        GridLayoutManager layoutManager = new GridLayoutManager(this, 3);
        recyclerView.setLayoutManager(layoutManager);

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                if (dy > 0) {
                    int visibleItemCount = layoutManager.getChildCount();
                    int totalItemCount = layoutManager.getItemCount();
                    int pastVisibleItems = layoutManager.findFirstCompletelyVisibleItemPosition();

                    if (aptoParaCargar){
                        if ((visibleItemCount + pastVisibleItems) >= totalItemCount) {
                            Log.i(TAG, "Llegamos al final de la lista");

                            aptoParaCargar = false;
                            offset += 20;
                            obtenerInfo(offset);
                        }
                    }
                }
            }
        });



        retrofit = new Retrofit.Builder()
                .baseUrl("https://pokeapi.co/api/v2/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        aptoParaCargar = true;
        offset = 0;


        obtenerInfo(offset);
    }

    private void obtenerInfo(int offset) {

        pokemonService service = retrofit.create(pokemonService.class);

        Call<pokemonRespuesta> pokemonRespuestaCall = service.obtenerListaPokemon(100, offset);


        pokemonRespuestaCall.enqueue(new Callback<pokemonRespuesta>() {
            @Override
            public void onResponse(Call<pokemonRespuesta> call, Response<pokemonRespuesta> response) {
                aptoParaCargar = true;
                if(response.isSuccessful()){
                    pokemonRespuesta pokemonRespuesta = response.body();
                    ArrayList<Pokemon> listapokemon = pokemonRespuesta.getResults();


                    listaPokemonAdapter.adicionarListaPokemon(listapokemon);
                } else {
                    Log.e(TAG, "onResponse:" + response.errorBody());
                }
            }

            @Override
            public void onFailure(Call<pokemonRespuesta> call, Throwable t) {
                aptoParaCargar = true;
                Log.e(TAG, "onFailure: " + t.getMessage());
            }
        });
    }

}