package com.example.pokeapp.ui.fragments.viewpager_fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.pokeapp.databinding.FragmentDetailEvolutionBinding

class DetailEvolutionFragment: Fragment() {

    lateinit var binding: FragmentDetailEvolutionBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDetailEvolutionBinding.inflate(inflater, container, false)

        return binding.root
    }
}