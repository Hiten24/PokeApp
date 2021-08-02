package com.example.pokeapp.ui.fragments.viewpager_fragments

import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.pokeapp.databinding.FragmentDetailStatsBinding
import com.example.pokeapp.models.PokemonDetail
import com.example.pokeapp.util.getPokemonTypeColor

class DetailStatsFragment: Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val binding = FragmentDetailStatsBinding.inflate(inflater, container, false)

        val pokemon = this.arguments?.getSerializable("pokemon") as PokemonDetail
        val color = getPokemonTypeColor(pokemon.types[0].type.name)
        updateStatsUi(binding, pokemon, color)

        return binding.root
    }

    private fun updateStatsUi(bind: FragmentDetailStatsBinding, pokemon: PokemonDetail,color: Array<String>) {

        val color1 = ColorStateList.valueOf(Color.parseColor(color[0]))

        val hp = pokemon.stats[0].base_stat
        val attack = pokemon.stats[1].base_stat
        val defence = pokemon.stats[2].base_stat
        val spAttack = pokemon.stats[3].base_stat
        val spDefence = pokemon.stats[4].base_stat
        val speed = pokemon.stats[5].base_stat

        bind.tvStatHp.text = hp.toString()
        bind.tvStatAttack.text = attack.toString()
        bind.tvStatDefence.text = defence.toString()
        bind.tvStatSpAttack.text = spAttack.toString()
        bind.tvStatSpDefence.text = spDefence.toString()
        bind.tvStatSpeed.text = speed.toString()

        bind.titleHp.setTextColor(Color.parseColor(color[0]))
        bind.titleAttack.setTextColor(Color.parseColor(color[0]))
        bind.titleDefence.setTextColor(Color.parseColor(color[0]))
        bind.titleSpAttack.setTextColor(Color.parseColor(color[0]))
        bind.titleSpDefence.setTextColor(Color.parseColor(color[0]))
        bind.titleSpeed.setTextColor(Color.parseColor(color[0]))

        bind.pbStatHp.progressTintList = color1
        bind.pbStatAttack.progressTintList = color1
        bind.pbStatDefence.progressTintList = color1
        bind.pbStatSpAttack.progressTintList = color1
        bind.pbStatSpDefence.progressTintList = color1
        bind.pbStatSpeed.progressTintList = color1

        bind.pbStatHp.progress = hp
        bind.pbStatAttack.progress = attack
        bind.pbStatDefence.progress = defence
        bind.pbStatSpAttack.progress = spAttack
        bind.pbStatSpDefence.progress = spDefence
        bind.pbStatSpeed.progress = speed
    }
}