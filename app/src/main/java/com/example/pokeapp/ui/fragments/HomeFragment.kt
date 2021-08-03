package com.example.pokeapp.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.pokeapp.adapters.PokemonListAdapter
import com.example.pokeapp.databinding.FragmentHomeBinding
import com.example.pokeapp.ui.viewModel.ApiStates
import com.example.pokeapp.ui.viewModel.HomeViewModel
import com.example.pokeapp.util.Constant.PAGE_SIZE

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

        setupHomeRecyclerAdapter(pokemonListAdapter)

        viewModel.pokemonList.observe(viewLifecycleOwner, { result ->
            pokemonListAdapter.differ.submitList(result)
        })

        viewModel.apiStatus.observe(viewLifecycleOwner, { states ->
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
        isLoading = false
    }

    private fun showProgressbar() {
        binding.pbHome.visibility = View.VISIBLE
        isLoading = true
    }

    private fun setupHomeRecyclerAdapter(pokemonListAdapter: PokemonListAdapter) {
        binding.rvHome.apply {
            adapter = pokemonListAdapter
            layoutManager = GridLayoutManager(activity, 2)
            addOnScrollListener(this@HomeFragment.scrollListener)
        }
    }

    private var isLoading = false
    private var isLastPage = false
    private var isScrolling = false

    private val scrollListener = object : RecyclerView.OnScrollListener() {
        override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
            isScrolling = true
        }

        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
            val shouldPaginate = shouldPaginate(recyclerView)
            if(shouldPaginate) {
                viewModel.getPokemon()
                isScrolling = false
            }
        }
    }

    private fun shouldPaginate(recyclerView: RecyclerView): Boolean {
        val layoutManager = recyclerView.layoutManager as LinearLayoutManager
        val firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition()
        val visibleLastItem = layoutManager.childCount
        val totalItemCount = layoutManager.itemCount

        val isNotLoadingAndNotLastPage = !isLoading && !isLastPage
        val isAtLastItem = firstVisibleItemPosition + visibleLastItem >= totalItemCount
        val isNotAtBeginning = firstVisibleItemPosition >= 0
        val isTotalMoreThanVisible = totalItemCount >= PAGE_SIZE

        return isNotLoadingAndNotLastPage
                && isAtLastItem
                && isNotAtBeginning
                && isTotalMoreThanVisible
                && isScrolling
    }
}