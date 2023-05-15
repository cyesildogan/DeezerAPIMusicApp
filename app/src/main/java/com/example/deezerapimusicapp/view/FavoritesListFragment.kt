package com.example.deezerapimusicapp.view

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.deezerapimusicapp.R
import com.example.deezerapimusicapp.databinding.FavoritesListFragmentBinding

class FavoritesListFragment : Fragment(R.layout.favorites_list_fragment) {

    private var fragmentBinding: FavoritesListFragmentBinding? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val binding = FavoritesListFragmentBinding.bind(view)
        fragmentBinding = binding

        binding.musicButton.setOnClickListener {
            findNavController().navigate(FavoritesListFragmentDirections.actionFavoritesListFragmentToMusicCategoriesFragment())
        }
    }
    override fun onDestroyView() {
        fragmentBinding = null
        super.onDestroyView()
    }

}