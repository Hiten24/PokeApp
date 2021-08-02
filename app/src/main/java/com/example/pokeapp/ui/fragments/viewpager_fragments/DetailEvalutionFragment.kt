package com.example.pokeapp.ui.fragments.viewpager_fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.pokeapp.R
import com.example.pokeapp.databinding.FragmentDetailEvolutionBinding
import com.example.pokeapp.models.Evolution
import com.example.pokeapp.models.PokemonDetail

class DetailEvalutionFragment: Fragment() {

    lateinit var binding: FragmentDetailEvolutionBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDetailEvolutionBinding.inflate(inflater, container, false)

        val pokemon = this.arguments?.getSerializable("pokemon") as PokemonDetail

        /*val evolution = getEvolutionChain(pokemon)
        updateUi(evolution)*/

        return binding.root
    }

    /*private fun getEvolutionChain(pokemon: PokemonDetail): ArrayList<Evolution>{
        var evolutionData = pokemon.evolutionChain.chain
        val evolutionChain: ArrayList<Evolution> = ArrayList()

        while (evolutionData != null) {
            val evoDetails = evolutionData.evolution_details[0]
            evolutionChain.add(Evolution(evolutionData.species.name, evoDetails.min_level))
            evolutionData = evolutionData.evolves_to[0]
        }
        return evolutionChain
    }

    private fun updateUi(evolutions: ArrayList<Evolution>) {
        binding.tvEvolveFrom.text = evolutions[0].speciesName
        binding.tvToEvolve.text = evolutions[1].speciesName
        val evoLevel = "Level ${evolutions[0].minLevel}"
        binding.tvEvolutionLevel.text = evoLevel
    }*/
}