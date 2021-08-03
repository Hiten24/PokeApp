package com.example.pokeapp.ui.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pokeapp.models.PokemonDetail
import com.example.pokeapp.network.PokemonApiService
import kotlinx.coroutines.launch
import java.util.*

class PokemonDetailViewModel(val id: Int): ViewModel() {

    private val _pokemon = MutableLiveData<PokemonDetail>()

    val pokemon: LiveData<PokemonDetail>
        get() = _pokemon

    private val _status = MutableLiveData<ApiStates>()

    val status: LiveData<ApiStates>
        get() = _status

    init {
        getPokemonDetail()
    }

    private fun getPokemonDetail() = viewModelScope.launch {

        _status.value = ApiStates.LOADING
        try {
            val pokemon = PokemonApiService.pokemonApi.getPokemonDetail(id)
            val pokemonSpecies = PokemonApiService.pokemonApi.getPokemonSpecies(pokemon.species.name)
            val chainId = pokemonSpecies.evolution_chain.url.dropLast(1).takeLastWhile { it.isDigit() }
            Log.d("PokemonChainId", chainId)
            val evolutionChain = PokemonApiService.pokemonApi.getEvolutionChain(chainId)

            _pokemon.value = PokemonDetail(
                pokemon.name.replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString() },
                pokemon.id,
                pokemon.types,
                pokemon.sprites.other.official_artwork.front_default,
                pokemon.stats,
                pokemonSpecies.flavor_text_entries[6].flavor_text,
                pokemonSpecies.genera[7].genus,
                pokemon.height,
                pokemon.weight,
                pokemon.abilities,
                "",
                pokemonSpecies.capture_rate,
                pokemonSpecies.base_happiness,
                pokemon.base_experience,
                pokemonSpecies.growth_rate.name,
                evolutionChain
            )
            _status.value = ApiStates.SUCCESS
        }catch (e: Exception){
            Log.d("PokemonDetailViewModel", e.message.toString())
            _status.value = ApiStates.ERROR
        }
    }
}