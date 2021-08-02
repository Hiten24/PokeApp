package com.example.pokeapp.adapters

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.pokeapp.models.PokemonDetail
import com.example.pokeapp.models.pokemonResponse.Pokemon
import com.example.pokeapp.ui.fragments.PokemonDetailFragment
import com.example.pokeapp.ui.fragments.viewpager_fragments.DetailAboutFragment
import com.example.pokeapp.ui.fragments.viewpager_fragments.DetailEvalutionFragment
import com.example.pokeapp.ui.fragments.viewpager_fragments.DetailMovesFragment
import com.example.pokeapp.ui.fragments.viewpager_fragments.DetailStatsFragment

class PokemonDetailViewPagerAdapter(fragmentManager: FragmentManager, lifecycle: Lifecycle, val pokemon: PokemonDetail) :
    FragmentStateAdapter(fragmentManager, lifecycle) {

    override fun getItemCount(): Int = 3

    override fun createFragment(position: Int): Fragment {
        val fragment = when (position){
            0 -> DetailAboutFragment()
//            1 -> DetailAboutFragment()
            1 -> DetailStatsFragment()
            else -> DetailEvalutionFragment()
        }
        val bundle = Bundle()
        bundle.putSerializable("pokemon", pokemon)
        fragment.arguments = bundle
        return fragment
    }
}