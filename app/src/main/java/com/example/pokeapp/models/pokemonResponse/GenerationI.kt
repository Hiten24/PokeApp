package com.example.pokeapp.models.pokemonResponse

import com.squareup.moshi.Json

data class GenerationI(
    @Json(name = "red-blue")
    val red_blue: RedBlue,
    val yellow: Yellow
)