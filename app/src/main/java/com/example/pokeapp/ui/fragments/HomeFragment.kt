package com.example.pokeapp.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.pokeapp.adapters.PokemonListAdapter
import com.example.pokeapp.databinding.FragmentHomeBinding
import com.example.pokeapp.ui.viewModel.ApiStates
import com.example.pokeapp.ui.viewModel.HomeViewModel

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private val viewModel by lazy {
        ViewModelProvider(this).get(HomeViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        val pokemonListAdapter = PokemonListAdapter()

        binding.rvHome.apply {
            adapter = pokemonListAdapter
            layoutManager = GridLayoutManager(activity,2)
        }

        viewModel.pokemonList.observe(viewLifecycleOwner, Observer { result ->
            pokemonListAdapter.differ.submitList(result)
        })

        viewModel.apiStatus.observe(viewLifecycleOwner, Observer { states ->
            when (states!!) {
                ApiStates.LOADING -> showProgressbar()
                ApiStates.SUCCESS -> hideProgressbar()
                ApiStates.ERROR -> {
                    hideProgressbar()
                    Toast.makeText(context, "No Internet Connection!", Toast.LENGTH_SHORT).show()
                }
            }
        })

        return binding.root
    }

    private fun hideProgressbar() {
        binding.pbHome.visibility = View.GONE
    }

    private fun showProgressbar() {
        binding.pbHome.visibility = View.VISIBLE
    }
}