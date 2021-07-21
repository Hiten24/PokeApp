package com.example.pokeapp.models.pokemonResponse

import com.squareup.moshi.Json

data class GenerationV(
    @Json(name = "black-white")
    val black_white: BlackWhite
)