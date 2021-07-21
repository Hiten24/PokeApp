package com.example.pokeapp.models.pokemonResponse

import com.squareup.moshi.Json

data class GenerationIii(
    val emerald: Emerald,
    @Json(name = "firered-leafgreen")
    val firered_leafgreen: FireredLeafgreen,
    @Json(name = "ruby-sapphire")
    val ruby_sapphire: RubySapphire
)