package com.example.pokeapp.models.pokemonResponse

import com.squareup.moshi.Json

data class GenerationVii(
    val icons: Icons,
    @Json(name = "ultra-sun-ultra-moon")
    val ultra_sun_ultra_moon: UltraSunUltraMoon
)