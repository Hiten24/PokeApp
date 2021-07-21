package com.example.pokeapp.models.pokemonResponse

data class HeldItem(
    val item: Item,
    val version_details: List<VersionDetail>
)