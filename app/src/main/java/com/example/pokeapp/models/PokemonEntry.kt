package com.example.pokeapp.models

data class PokemonEntry(
    val id: Int,
    val name: String,
    val imgUrl: String = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/$id.png",
    val type: String
)

//"https://pokeres.bastionbot.org/images/pokemon/$id.png"