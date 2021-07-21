package com.example.pokeapp.models.pokemonResponse

import com.squareup.moshi.Json

data class Versions(
    @Json(name = "generation-i")
    val generation_i: GenerationI,
    @Json(name = "generation-io")
    val generation_ii: GenerationIi,
    @Json(name = "generation-iii")
    val generation_iii: GenerationIii,
    @Json(name = "generation-iv")
    val generation_iv: GenerationIv,
    @Json(name = "generation-v")
    val generation_v: GenerationV,
    @Json(name = "generation-vi")
    val generation_vi: GenerationVi,
    @Json(name = "generation-vii")
    val generation_vii: GenerationVii,
    @Json(name = "generation-viii")
    val generation_viii: GenerationViii
)