package com.example.pokeapp.ui.fragments.viewpager_fragments

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.pokeapp.R
import com.example.pokeapp.databinding.FragmentDetailAboutBinding
import com.example.pokeapp.models.PokemonDetail
import com.example.pokeapp.models.pokemonResponse.Pokemon
import com.example.pokeapp.util.*
import org.w3c.dom.Text

class DetailAboutFragment: Fragment() {

    lateinit var binding: FragmentDetailAboutBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDetailAboutBinding.inflate(inflater, container, false)

        val pokemon = this.arguments?.getSerializable("pokemon") as PokemonDetail
        updateUi(pokemon)
        return binding.root
    }

    private fun updateUi(pokemon: PokemonDetail) {

        val color = getPokemonTypeColor(pokemon.types[0].type.name)

        binding.titlePokedexData.setTextColor(Color.parseColor(color[0]))
        binding.titleTraining.setTextColor(Color.parseColor(color[0]))

        val height = convertHeightDecimeterToMeter(pokemon.height).toString() + "m."
        val weight = convertWeightHectogramsToKilograms(pokemon.weight).toString() +
                "kg. (" + String.format("%.1f" ,convertWeightHectogramsTOLbs(pokemon.weight)) + ("lbs)")

        binding.tvDetailAbout.text = pokemon.description

        binding.tvDetailSpecies.text = pokemon.species
        binding.tvDetailHeight.text = height
        binding.tvDetailWeight.text = weight
        binding.tvDetailAbilities.text = formatAbilities(pokemon.abilities)

        binding.tvDetailCatchRate.text = pokemon.catchRate.toString()
        binding.tvDetailBaseFriendship.text = pokemon.baseFriendship.toString()
        binding.tvDetailBaseExp.text = pokemon.baseExp.toString()
        binding.tvDetailGrowthRate.text = pokemon.growthRate

    }
}