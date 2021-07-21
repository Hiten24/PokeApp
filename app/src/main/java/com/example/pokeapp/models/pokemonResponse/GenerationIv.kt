package com.example.pokeapp.models.pokemonResponse

import com.squareup.moshi.Json

data class GenerationIv(
    @Json(name = "diamond-pearl")
    val diamond_pearl: DiamondPearl,
    @Json(name = "heartgold-soulsilver")
    val heartgold_soulsilver: HeartgoldSoulsilver,
    val platinum: Platinum
)