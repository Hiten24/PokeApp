package com.example.pokeapp.ui.fragments

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.example.pokeapp.databinding.FragmentPokemonDetailBinding
import com.example.pokeapp.models.pokemonResponse.Pokemon
import com.example.pokeapp.ui.viewModel.PokemonDetailViewModel
import com.example.pokeapp.ui.viewModel.PokemonDetailViewModelFactory

class PokemonDetailFragment: Fragment() {

    private lateinit var binding: FragmentPokemonDetailBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPokemonDetailBinding.inflate(inflater, container, false)

        val id = PokemonDetailFragmentArgs.fromBundle(requireArguments()).pokemonId
        Toast.makeText(context, "${id}", Toast.LENGTH_SHORT).show()

        val viewModelFactory = PokemonDetailViewModelFactory(id)
        val viewModel = ViewModelProvider(this, viewModelFactory).get(PokemonDetailViewModel::class.java)

        viewModel.pokemon.observe(viewLifecycleOwner, Observer { pokemon ->
            updateUi(pokemon)
        })

        return binding.root
    }

    private fun updateUi(pokemon: Pokemon) {
        val b = binding

        Glide.with(this)
            .load(pokemon.sprites.other.official_artwork.front_default)
            .into(b.ivPokemon)

        b.tvName.text = pokemon.name
        b.tvHeight.text = pokemon.height.toString()
        b.tvWeight.text = pokemon.weight.toString()
        //hp
        b.rvHp.text = pokemon.stats[0].base_stat.toString()
        //attack
        b.tvAttack.text = pokemon.stats[1].base_stat.toString()
        //defence
        b.tvDefence.text = pokemon.stats[2].base_stat.toString()
        //spAttack
        b.tvSpAttack.text = pokemon.stats[3].base_stat.toString()
        //spDefence
        b.tvSpDefence.text = pokemon.stats[4].base_stat.toString()
        //spSpeed
        b.tvSpeed.text = pokemon.stats[5].base_stat.toString()
        b.tvType.text = pokemon.types[0].type.name
    }
}