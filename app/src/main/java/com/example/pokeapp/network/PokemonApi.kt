package com.example.pokeapp.network

import com.example.pokeapp.models.PokemonList
import com.example.pokeapp.models.pokemonResponse.Pokemon
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PokemonApi {
    @GET("api/v2/pokemon")
    suspend fun getPokemon(
        @Query("offset")
        offset: Int = 0,
        @Query("limit")
        limit: Int = 100
    ):  PokemonList

    @GET("api/v2/pokemon/{id}")
    suspend fun getPokemonDetail(
        @Path("id")
        pokemonId: Int
    ) : Pokemon
}