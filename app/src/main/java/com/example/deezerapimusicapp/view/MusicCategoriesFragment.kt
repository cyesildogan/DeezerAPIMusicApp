package com.example.deezerapimusicapp.view

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.deezerapimusicapp.R
import com.example.deezerapimusicapp.databinding.MusicCategoriesFragmentBinding

class MusicCategoriesFragment: Fragment(R.layout.music_categories_fragment) {

    private var fragmentBinding : MusicCategoriesFragmentBinding? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val binding = MusicCategoriesFragmentBinding.bind(view)
        fragmentBinding = binding

        binding.likeButton.setOnClickListener {

            findNavController().navigate(MusicCategoriesFragmentDirections.actionMusicCategoriesFragmentToFavoritesListFragment())

        }
    }
    override fun onDestroyView() {
        fragmentBinding = null
        super.onDestroyView()
    }
}