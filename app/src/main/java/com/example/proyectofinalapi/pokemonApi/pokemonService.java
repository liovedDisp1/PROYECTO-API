package com.example.proyectofinalapi.pokemonApi;

import com.example.proyectofinalapi.modelos.pokemonRespuesta;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface pokemonService {

    @GET("pokemon")

    Call<pokemonRespuesta> obtenerListaPokemon(@Query("limit") int limit, @Query("offset") int offset);


}
