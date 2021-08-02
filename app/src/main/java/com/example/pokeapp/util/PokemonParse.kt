package com.example.pokeapp.util

import android.graphics.drawable.Drawable
import com.example.pokeapp.R
import com.example.pokeapp.models.pokemonResponse.Ability

fun convertHeightDecimeterToMeter(height: Int): Float = height.toFloat()/10

fun convertWeightHectogramsToKilograms(weight: Int): Float = weight.toFloat()/10

fun convertWeightHectogramsTOLbs(weight: Int): Float = (weight.toFloat()/4.536).toFloat()

fun formatAbilities(abilities: List<Ability>): String {
    var ability: String = ""
    for(i in abilities) {
        ability = ability + "${abilities.indexOf(i)+1}. " + i.ability.name + "\n"
    }
    return ability
}

fun getTypeTag(type: String): Int {
    return when(type) {
        "bug" -> R.drawable.ic_bug
        "dark" -> R.drawable.ic_dark
        "dragon" -> R.drawable.ic_dragon
        "electric" -> R.drawable.ic_electric
        "fairy" -> R.drawable.ic_fairy
        "fight" -> R.drawable.ic_fight
        "fire" -> R.drawable.ic_fire
        "flaying" -> R.drawable.ic_flying
        "ghost" -> R.drawable.ic_ghost
        "grass" -> R.drawable.ic_grass
        "ground" -> R.drawable.ic_ground
        "ice" -> R.drawable.ic_ice
        "normal" -> R.drawable.ic_normal
        "poison" -> R.drawable.ic_poison
        "psychic" -> R.drawable.ic_psychic
        "rock" -> R.drawable.ic_rock
        "steel" -> R.drawable.ic_steel
        "water" -> R.drawable.ic_water
        else -> R.drawable.arrow
    }
}

fun getPokemonTypeColor(type: String): Array<String> {
    return  when(type){
        "bug" -> arrayOf(TypeColor.bug, TypeSecondColor.bug)
        "dark" -> arrayOf(TypeColor.dark, TypeSecondColor.dark)
        "dragon" -> arrayOf(TypeColor.dragon, TypeSecondColor.dragon)
        "electric" -> arrayOf(TypeColor.electric, TypeSecondColor.electric)
        "fairy" -> arrayOf(TypeColor.fairy, TypeSecondColor.fairy)
        "fight" -> arrayOf(TypeColor.fight, TypeSecondColor.fight)
        "fire" -> arrayOf(TypeColor.fire, TypeSecondColor.fire)
        "flaying" -> arrayOf(TypeColor.flaying, TypeSecondColor.flaying)
        "ghost" -> arrayOf(TypeColor.ghost, TypeSecondColor.ghost)
        "grass" -> arrayOf(TypeColor.grass, TypeSecondColor.grass)
        "ground" -> arrayOf(TypeColor.ground, TypeSecondColor.ground)
        "ice" -> arrayOf(TypeColor.ice, TypeSecondColor.ice)
        "normal" -> arrayOf(TypeColor.normal, TypeSecondColor.normal)
        "poison" -> arrayOf(TypeColor.poison, TypeSecondColor.poison)
        "psychic" -> arrayOf(TypeColor.psychic, TypeSecondColor.psychic)
        "rock" -> arrayOf(TypeColor.rock, TypeSecondColor.rock)
        "steel" -> arrayOf(TypeColor.steel, TypeSecondColor.steel)
        "water" -> arrayOf(TypeColor.water, TypeSecondColor.water)
        else -> arrayOf("#FFFFFF","#000000")
    }
}