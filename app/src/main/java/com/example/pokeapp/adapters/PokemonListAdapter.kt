 package com.example.pokeapp.adapters

import android.content.res.ColorStateList
import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.content.res.AppCompatResources
import androidx.navigation.findNavController
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.pokeapp.databinding.ItemPokemonBinding
import com.example.pokeapp.models.PokemonEntry
import com.example.pokeapp.ui.fragments.HomeFragmentDirections
import com.example.pokeapp.util.getPokemonTypeColor
import com.example.pokeapp.util.getTypeTag

 class PokemonListAdapter: RecyclerView.Adapter<PokemonListAdapter.PokemonListViewHolder>() {

    inner class PokemonListViewHolder(val binding: ItemPokemonBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonListViewHolder {
        return PokemonListViewHolder(ItemPokemonBinding.inflate(LayoutInflater.from(parent.context)))
    }

    private val differCallback = object : DiffUtil.ItemCallback<PokemonEntry>() {
        override fun areItemsTheSame(oldItem: PokemonEntry, newItem: PokemonEntry): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: PokemonEntry, newItem: PokemonEntry): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, differCallback)

    override fun onBindViewHolder(holder: PokemonListViewHolder, position: Int) {
        val pokemon = differ.currentList[position]
        val view = holder.binding

        val color = getPokemonTypeColor(pokemon.type)
        view.pokemonCard.setCardBackgroundColor(Color.parseColor(color[1]))
//        view.pokemonCard.alpha = 0.9F

        view.tvPokemonName.text = pokemon.name
        Glide.with(view.root)
            .asBitmap()
            .load(pokemon.imgUrl)
            .into(view.ivPokemon)

        view.pokemonTypeBackground.imageTintList = ColorStateList.valueOf(Color.parseColor(color[0]))
        view.pokemonTypeIcon.setImageDrawable(AppCompatResources.getDrawable(view.root.context, getTypeTag(pokemon.type)))

        view.clItemPokemon.setOnClickListener {
            view.root.findNavController()
                .navigate(HomeFragmentDirections.actionHomeFragmentToPokemonDetailFragment(pokemon.id))
        }
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }
}
