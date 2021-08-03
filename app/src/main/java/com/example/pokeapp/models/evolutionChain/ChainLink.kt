package com.example.pokeapp.models.evolutionChain

import com.example.pokeapp.models.pokemonResponse.Species

data class ChainLink(
    val evolution_details: List<EvolutionDetail>,
    val evolves_to: List<ChainLink>,
    val species: Species
)
