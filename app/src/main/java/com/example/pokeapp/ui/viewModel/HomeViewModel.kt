package com.example.pokeapp.ui.viewModel

import android.util.Log
import androidx.lifecycle.*
import com.example.pokeapp.models.PokemonEntry
import com.example.pokeapp.models.PokemonList
import com.example.pokeapp.models.PokemonResult
import com.example.pokeapp.models.pokemonResponse.Pokemon
import com.example.pokeapp.network.PokemonApi
import com.example.pokeapp.network.PokemonApiService
import com.example.pokeapp.util.Constant.PAGE_SIZE
import kotlinx.coroutines.launch
import java.lang.Exception

enum class ApiStates { LOADING, SUCCESS, ERROR }

class HomeViewModel: ViewModel() {

    private var curPage = 0

    private val _apiStatus = MutableLiveData<ApiStates>()

    val apiStatus: LiveData<ApiStates>
        get() = _apiStatus

    private val _pokemonList = MutableLiveData<List<PokemonEntry>>(listOf())

    val pokemonList: LiveData<List<PokemonEntry>>
        get() = _pokemonList

    init {
        getPokemon()
    }

    fun getPokemon() = viewModelScope.launch {
        _apiStatus.value = ApiStates.LOADING
        try {
            val pokemon = PokemonApiService.pokemonApi.getPokemon(curPage * PAGE_SIZE, PAGE_SIZE).results
            val pokemonEntries = pokemon.mapIndexed { _, entry ->
                val id = entry.url.dropLast(1).takeLastWhile { it.isDigit() }
//                val url = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/$id.png"
                val type = PokemonApiService.pokemonApi.getPokeType(id.toInt()).types[0].type.name
                PokemonEntry(id.toInt(), entry.name,type = type)
            }
            curPage++
            _pokemonList.value = _pokemonList.value?.plus(pokemonEntries)
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