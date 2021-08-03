package com.example.pokeapp.ui.fragments

import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.pokeapp.adapters.PokemonDetailViewPagerAdapter
import com.example.pokeapp.databinding.FragmentPokemonDetailBinding
import com.example.pokeapp.models.PokemonDetail
import com.example.pokeapp.ui.viewModel.ApiStates
import com.example.pokeapp.ui.viewModel.PokemonDetailViewModel
import com.example.pokeapp.ui.viewModel.PokemonDetailViewModelFactory
import com.example.pokeapp.util.getPokemonTypeColor
import com.example.pokeapp.util.getTypeTag
import com.google.android.material.tabs.TabLayoutMediator

class PokemonDetailFragment: Fragment() {

    private lateinit var binding: FragmentPokemonDetailBinding
    lateinit var color: IntArray

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPokemonDetailBinding.inflate(inflater, container, false)

        activity?.actionBar?.hide()

        binding.ivBack.setOnClickListener {
            activity?.onBackPressed()
        }

        val id = PokemonDetailFragmentArgs.fromBundle(requireArguments()).pokemonId

        val viewModelFactory = PokemonDetailViewModelFactory(id)
        val viewModel = ViewModelProvider(this, viewModelFactory).get(PokemonDetailViewModel::class.java)
        viewModel.pokemon.observe(viewLifecycleOwner, { pokemon ->
            updateUi(pokemon)
            setupViewPager(pokemon)
        })

        viewModel.status.observe(viewLifecycleOwner, { status ->
            if(status == ApiStates.LOADING){
                showProgressBar()
            }else {
                hideProgressBar()
            }
        })

        return binding.root
    }

    private fun hideProgressBar() {
        binding.pbPokemonDetail.visibility = View.GONE
        binding.pbBackground.visibility = View.GONE
    }

    private fun showProgressBar() {
        binding.pbPokemonDetail.visibility = View.VISIBLE
        binding.pbBackground.visibility = View.VISIBLE
    }

    private fun updateUi(pokemon: PokemonDetail) {
        val b = binding

        val colorHex = getPokemonTypeColor(pokemon.types[0].type.name)
        color = intArrayOf(Color.parseColor(colorHex[0]),Color.parseColor(colorHex[1]))
//        val gd = GradientDrawable(GradientDrawable.Orientation.LEFT_RIGHT, color)

        val window = activity?.window
        window?.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window?.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        window?.statusBarColor = color[0]

        val typeImg = getTypeTag(pokemon.types[0].type.name)

        b.clPokemonDetail.setBackgroundColor(color[0])

        Glide.with(this)
            .load(pokemon.imageUrl)
            .into(b.ivPokemon)

        b.tvPokeName.text = pokemon.name
        val id = "#${pokemon.id}"
        b.tvPokemonId.text = id
        b.tvPokemonType.text = pokemon.types[0].type.name
        b.tvPokemonType.backgroundTintList = ColorStateList.valueOf(color[1])
        b.tvPokemonType.setCompoundDrawablesWithIntrinsicBounds(typeImg,0,0,0)
    }

    private fun setupViewPager(pokemon: PokemonDetail) {

        val adapter = PokemonDetailViewPagerAdapter(requireActivity().supportFragmentManager, lifecycle, pokemon)
        binding.pokemonDetailViewPager.adapter = adapter

        binding.pokemonDetailTabLayout.setSelectedTabIndicatorColor(color[0])

        TabLayoutMediator(binding.pokemonDetailTabLayout, binding.pokemonDetailViewPager) { tab, position ->
            when (position) {
                0 -> tab.text = "About"
//                1 -> tab.text = "Stat"
                1 -> tab.text = "Stats"
                2 -> tab.text = "Evolution"
            }
        }.attach()
    }
}
