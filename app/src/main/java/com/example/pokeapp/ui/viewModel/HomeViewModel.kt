package com.example.pokeapp.ui.viewModel

import android.util.Log
import androidx.lifecycle.*
import com.example.pokeapp.models.PokemonEntry
import com.example.pokeapp.models.PokemonList
import com.example.pokeapp.models.PokemonResult
import com.example.pokeapp.network.PokemonApiService
import kotlinx.coroutines.launch
import java.lang.Exception

enum class ApiStates { LOADING, SUCCESS, ERROR }

class HomeViewModel: ViewModel() {

    private val _apiStatus = MutableLiveData<ApiStates>()

    val apiStatus: LiveData<ApiStates>
        get() = _apiStatus

    /*private val _pokemonList = MutableLiveData<List<PokemonResult>>()

    val pokemonList: LiveData<List<PokemonResult>>
        get() = _pokemonList*/

    private val _pokemonList = MutableLiveData<List<PokemonEntry>>()

    val pokemonList: LiveData<List<PokemonEntry>>
        get() = _pokemonList

    init {
        getPokemon()
    }

    private fun getPokemon() = viewModelScope.launch {
        _apiStatus.value = ApiStates.LOADING
        try {
            val pokemon = PokemonApiService.pokemonApi.getPokemon().results
            val pokemonEntry = pokemon.mapIndexed { index, entry ->
                val id = entry.url.dropLast(1).takeLastWhile { it.isDigit() }
                val url = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/$id.png"
//                val type = PokemonApiService.pokemonApi.getPokemonDetail(entry.name).types[0].type.name
                PokemonEntry(id.toInt(), entry.name, url)
            }
            _pokemonList.postValue(pokemonEntry)
            _apiStatus.value = ApiStates.SUCCESS
        } catch (e: Exception) {
            Log.d("HomeViewModel", e.message.toString())
            _apiStatus.value = ApiStates.ERROR
        }
    }
    /*private fun getPokemon() = viewModelScope.launch {
        _apiStatus.value = ApiStates.LOADING
        try {
            _pokemonList.value = PokemonApiService.pokemonApi.getPokemon().results
            _apiStatus.value = ApiStates.SUCCESS
        } catch (e: Exception) {
            _apiStatus.value = ApiStates.ERROR
        }
    }*/
}