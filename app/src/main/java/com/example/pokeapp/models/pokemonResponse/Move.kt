package com.example.pokeapp.models.pokemonResponse

data class Move(
    val move: MoveX,
    val version_group_details: List<VersionGroupDetail>
)