package com.example.pokeapp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.pokeapp.databinding.ItemMoveBinding
import com.example.pokeapp.models.pokemonResponse.Move

class MovesAdapter(val moves: List<Move>): RecyclerView.Adapter<MovesAdapter.MoveViewHolder>() {

    class MoveViewHolder(val binding: ItemMoveBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoveViewHolder {
        return MoveViewHolder(ItemMoveBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: MoveViewHolder, position: Int) {
        val move = moves[position]
        holder.binding.tvMoveName.text = move.move.name
    }

    override fun getItemCount(): Int {
        return moves.size
    }
}