package com.example.pokeapp.models

import com.example.pokeapp.models.evolutionChain.EvolutionChainResponse
import com.example.pokeapp.models.pokemonResponse.Ability
import com.example.pokeapp.models.pokemonResponse.Stat
import com.example.pokeapp.models.pokemonResponse.Type
import java.io.Serializable

data class PokemonDetail(
    val name: String,
    val id: Int,
    val types: List<Type>,
    val imageUrl: String,
    val stats: List<Stat>,
    val description: String,
    val species: String,
    val height: Int,
    val weight: Int,
    val abilities: List<Ability>,
    val evYield: String,
    val catchRate: Int,
    val baseFriendship: Int,
    val baseExp: Int,
    val growthRate: String,
    val evolutionChain: EvolutionChainResponse
): Serializable