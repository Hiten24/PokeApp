package com.example.pokeapp.ui.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pokeapp.models.pokemonResponse.Pokemon
import com.example.pokeapp.network.PokemonApiService
import kotlinx.coroutines.launch

class PokemonDetailViewModel(val id: Int): ViewModel() {

    private val _pokemon = MutableLiveData<Pokemon>()

    val pokemon: LiveData<Pokemon>
        get() = _pokemon

    init {
        getPokemonDetail()
    }

    private fun getPokemonDetail() = viewModelScope.launch {
        _pokemon.value = PokemonApiService.pokemonApi.getPokemonDetail(id)
    }
}