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
import com.example.deezerapimusicapp.adapter.AlbumDetailAdapter
import com.example.deezerapimusicapp.databinding.AlbumDetailsFragmentBinding
import com.example.deezerapimusicapp.model.albumDetail.Album
import com.example.deezerapimusicapp.model.albumDetail.AlbumDetailsModel
import com.example.deezerapimusicapp.model.albumDetail.DataX
import com.example.deezerapimusicapp.model.albumDetail.Tracks
import com.example.deezerapimusicapp.model.albums.AlbumsModel
import com.example.deezerapimusicapp.viewmodel.AlbumDetailsViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlin.time.ExperimentalTime

@AndroidEntryPoint
class AlbumDetailsFragment : Fragment(R.layout.album_details_fragment) {

    private var getAlbumId : String = ""
    private var getAlbumName : String = ""
    private val args : AlbumDetailsFragmentArgs by navArgs()
    private lateinit var fragmentBinding : AlbumDetailsFragmentBinding
    private val albumDetailsViewModel : AlbumDetailsViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        args.albumDetailId.let {
            getAlbumId = it.toString()
        }
        args.albumName.let {
            getAlbumName = it
        }
        updateAlbumDetailState(getAlbumId)
        fragmentBinding = AlbumDetailsFragmentBinding.inflate(inflater)
        fragmentBinding.albumDetailsAlbumNameTextView.text = getAlbumName
        return fragmentBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }


    private fun updateAlbumDetailState(getAlbumId : String){
        viewLifecycleOwner.lifecycleScope.launch {
            albumDetailsViewModel.getAlbumDetails(getAlbumId)
            albumDetailsViewModel.albumState.collect{albumDetailViewState->
                if (albumDetailViewState.isLoading){

                }else if (albumDetailViewState.isSuccess){
                    fragmentBinding.recyclerViewAlbumDetails.visibility = View.VISIBLE
                    setRecyclerView(albumDetailViewState.albumDetailList!!.data,albumDetailViewState.albumReleaseDetail!!)
                }else{
                    fragmentBinding.recyclerViewAlbumDetails.visibility = View.GONE
                    Toast.makeText(requireContext(),"No no", Toast.LENGTH_LONG).show()
                }

            }
        }
    }
    @OptIn(ExperimentalTime::class)
    fun setRecyclerView(albumDetail: List<DataX>, duration : List<DataX>){
        fragmentBinding.recyclerViewAlbumDetails.adapter = AlbumDetailAdapter(albumDetail,duration,
            AlbumDetailAdapter.OnClickListener{
                findNavController()
            })
    }
}