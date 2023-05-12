package com.example.deezerapimusicapp.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.deezerapimusicapp.R
import com.example.deezerapimusicapp.adapter.GenreListAdapter
import com.example.deezerapimusicapp.databinding.MusicCategoriesFragmentBinding
import com.example.deezerapimusicapp.model.genre.GenreData
import com.example.deezerapimusicapp.viewmodel.MusicCategoriesViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MusicCategoriesFragment: Fragment(R.layout.music_categories_fragment) {

    private lateinit var fragmentBinding : MusicCategoriesFragmentBinding
    private val musicViewModel : MusicCategoriesViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        updateGenreState()
        fragmentBinding = MusicCategoriesFragmentBinding.inflate(inflater)
        return fragmentBinding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val binding = MusicCategoriesFragmentBinding.bind(view)
        fragmentBinding = binding

        binding.likeButton.setOnClickListener {

            findNavController().navigate(MusicCategoriesFragmentDirections.actionMusicCategoriesFragmentToFavoritesListFragment())
        }
    }

   private fun updateGenreState(){
        viewLifecycleOwner.lifecycleScope.launch {
            musicViewModel.getGenre()
            musicViewModel.genreState.collect{ genreState->
                if (genreState.isLoading){

                }else if(genreState.isSuccess){
                    fragmentBinding.recyclerViewMusicCategories.visibility=View.VISIBLE
                    setRecyclerView(genreState.genreList!!.data)
                }else{
                    fragmentBinding.recyclerViewMusicCategories.visibility=View.VISIBLE
                    Toast.makeText(requireContext(),"No no",Toast.LENGTH_LONG).show()
                }
            }
        }
    }

    fun setRecyclerView(genreList: List<GenreData>){
        fragmentBinding.recyclerViewMusicCategories.adapter = GenreListAdapter(genreList,GenreListAdapter.OnClickListener{
                findNavController().navigate(MusicCategoriesFragmentDirections
                    .actionMusicCategoriesFragmentToArtistListFragment(it.id))
            })


    }
    override fun onDestroyView() {

        super.onDestroyView()
    }
}