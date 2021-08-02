package com.example.pokeapp.ui.fragments.viewpager_fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.pokeapp.R
import com.example.pokeapp.adapters.MovesAdapter
import com.example.pokeapp.models.PokemonDetail
import com.example.pokeapp.models.pokemonResponse.Pokemon

class DetailMovesFragment: Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_detail_moves, container, false)

        val pokemon = this.arguments?.getSerializable("pokemon") as PokemonDetail

        val rvMoves: RecyclerView = view.findViewById(R.id.rvDetailMoves)
//        val moveAdapter = MovesAdapter(pokemon.moves)
        /*rvMoves.apply {
            adapter = moveAdapter
            layoutManager = LinearLayoutManager(activity)
        }*/

        return view
    }
}