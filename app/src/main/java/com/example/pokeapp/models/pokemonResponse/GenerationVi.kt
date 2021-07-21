package com.example.pokeapp.models.pokemonResponse

import com.squareup.moshi.Json

data class GenerationVi(
    @Json(name = "omegaruby-alphasapphire")
    val omegaruby_alphasapphire: OmegarubyAlphasapphire,
    @Json(name = "x-y")
    val x_y: XY
)