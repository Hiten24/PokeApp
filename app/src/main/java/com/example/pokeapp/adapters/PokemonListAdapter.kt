 package com.example.pokeapp.adapters

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.pokeapp.databinding.ItemPokemonBinding
import com.example.pokeapp.models.PokemonEntry
import com.example.pokeapp.ui.fragments.HomeFragmentDirections
import com.example.pokeapp.util.getPokemonTypeColor
import java.util.*

class PokemonListAdapter(): RecyclerView.Adapter<PokemonListAdapter.PokemonListViewHolder>() {

    inner class PokemonListViewHolder(val binding: ItemPokemonBinding): RecyclerView.ViewHolder(binding.root) {
        /*fun bind(pokemon: PokemonEntry) {
            binding.tvPokemonName.text = pokemon.name
//            binding.tvType.text = pokemon.type
//            val cardBgColor = binding.root.resources.getIdentifier(pokemon.type, "color", binding.root.context.packageName)
//            binding.pokemonCard.setCardBackgroundColor(cardBgColor)
            binding.pokemonCard.setCardBackgroundColor(binding.root.resources.getColor(R.color.grass))
            Glide.with(binding.root)
                .asBitmap()
                .load(pokemon.imgUrl)
                *//*.listener(object: RequestListener<Bitmap>{
                    override fun onLoadFailed(
                        e: GlideException?,
                        model: Any?,
                        target: Target<Bitmap>?,
                        isFirstResource: Boolean
                    ): Boolean {
                        return false
                    }

                    override fun onResourceReady(
                        resource: Bitmap?,
                        model: Any?,
                        target: Target<Bitmap>?,
                        dataSource: DataSource?,
                        isFirstResource: Boolean
                    ): Boolean {
                        Palette.from(resource!!).generate { palette ->
                            palette?.dominantSwatch?.rgb.let { color ->
                                binding.pokemonCard.setCardBackgroundColor(color!!)
                            }
                        }
                        return false
                    }
                })*//*
                .into(binding.ivPokemon)

//            val typeIconName = pokemon.type.replaceFirstChar {
//                if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString()
//            }

//            val typeUrl = "https://raw.githubusercontent.com/Hiten24/PokemonResources/main/icon/png/drawable-mdpi/Types-$typeIconName.png?token=ALQ4L3QHILSXB3JAR7ENKYDBAESCK"
//            Glide.with(binding.root)
//                .load(typeUrl)
//                .into(binding.ivPokeTypeIcone)
        }*/
    }

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

        /*view.tvPokemonName.text = pokemon.name

        Glide.with(view.root)
            .load(pokemon.imgUrl)
            .into(view.ivPokemonImg)*/

        view.pokemonCard.setCardBackgroundColor(Color.parseColor(getPokemonTypeColor(pokemon.type)[0]))
        view.pokemonCard.alpha = 0.9F

        val typeIconUrl = "https://raw.githubusercontent.com/Hiten24/PokemonResources/main/icon/png/drawable-mdpi/Types-${
            pokemon.type.replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.ROOT) else it.toString() }
        }.png"

        Glide.with(view.root)
            .load(typeIconUrl)
            .into(view.ivPokeTypeIcone)

        view.tvPokemonName.text = pokemon.name
        Glide.with(view.root)
            .asBitmap()
            .load(pokemon.imgUrl)
            .into(view.ivPokemon)

        view.clItemPokemon.setOnClickListener {
            view.root.findNavController()
                .navigate(HomeFragmentDirections.actionHomeFragmentToPokemonDetailFragment(pokemon.id))
        }
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }
}