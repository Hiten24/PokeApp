package com.example.pokeapp.network

import com.example.pokeapp.models.PokemonList
import com.example.pokeapp.models.PokemonSpeciesResponse.PokemonSpeciesResponse
import com.example.pokeapp.models.evolutionChain.EvolutionChainResponse
import com.example.pokeapp.models.pokemonResponse.Pokemon
import com.example.pokeapp.models.type.TypeList
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PokemonApi {

    @GET("api/v2/pokemon")
    suspend fun getPokemon(
        @Query("offset")
        offset: Int,
        @Query("limit")
        limit: Int
    ):  PokemonList

    @GET("api/v2/pokemon/{id}")
    suspend fun getPokemonDetail(
        @Path("id")
        pokemonId: Int
    ) : Pokemon

    @GET("api/v2/pokemon/{id}")
    suspend fun getPokeType(
        @Path("id")
        pokemonId: Int
    ): TypeList

    @GET("api/v2/pokemon-species/{specieName}")
    suspend fun getPokemonSpecies(
        @Path("specieName")
        pokemonId: String
    ): PokemonSpeciesResponse

    @GET("api/v2/evolution-chain/{chainId}")
    suspend fun getEvolutionChain(
        @Path("chainId")
        evolutionChainId: String
    ): EvolutionChainResponse

}