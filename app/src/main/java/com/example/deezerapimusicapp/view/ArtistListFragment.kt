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
import androidx.navigation.fragment.navArgs
import com.example.deezerapimusicapp.R
import com.example.deezerapimusicapp.adapter.ArtistListAdapter
import com.example.deezerapimusicapp.databinding.ArtistsListFragmentBinding
import com.example.deezerapimusicapp.model.artist.ArtistData
import com.example.deezerapimusicapp.viewmodel.ArtistListViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ArtistListFragment : Fragment(R.layout.artists_list_fragment) {

    private var getCategoriesId : String= ""
    private var getCategoriesName : String = ""
    private val args : ArtistListFragmentArgs by navArgs()
    private lateinit var fragmentBinding : ArtistsListFragmentBinding
    private val artistListViewModel : ArtistListViewModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        args.artistsListId.let {
            getCategoriesId = it.toString()
        }
        args.getCategoriesName.let {
            getCategoriesName = it
        }
        updateArtistState(getCategoriesId)

        fragmentBinding = ArtistsListFragmentBinding.inflate(inflater)
        fragmentBinding.categoriesNameTextView.text = getCategoriesName
        return fragmentBinding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        super.onViewCreated(view, savedInstanceState)

        val binding = ArtistsListFragmentBinding.bind(view)
        fragmentBinding = binding
    }
    private fun updateArtistState(getCategoriesId : String){
        viewLifecycleOwner.lifecycleScope.launch {
            artistListViewModel.getArtists(getCategoriesId)
            artistListViewModel.artistState.collect{ artistState->
                if (artistState.isLoading){

                }else if(artistState.isSuccess){
                    fragmentBinding.recyclerViewArtistsList.visibility=View.VISIBLE
                    setRecyclerView(artistState.artistList!!.data)
                }else{
                    fragmentBinding.recyclerViewArtistsList.visibility=View.VISIBLE
                    Toast.makeText(requireContext(),"No no", Toast.LENGTH_LONG).show()
                }
            }
        }
    }
    fun setRecyclerView(artistList: List<ArtistData>){
        fragmentBinding.recyclerViewArtistsList.adapter = ArtistListAdapter(artistList,
           ArtistListAdapter.OnClickListener{
            findNavController().navigate(ArtistListFragmentDirections.actionArtistListFragmentToArtistDetailFragment(
                artistDetailId = it.picture_big,
                getArtistName = it.name,
                getArtistId = it.id))

        })

    }

}