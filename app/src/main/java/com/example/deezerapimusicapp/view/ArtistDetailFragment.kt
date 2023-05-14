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
import com.example.deezerapimusicapp.adapter.ArtistDetailAdapter
import com.example.deezerapimusicapp.databinding.ArtistDetailsFragmentBinding
import com.example.deezerapimusicapp.model.albumDetail.AlbumDetailsModel
import com.example.deezerapimusicapp.model.albums.Data
import com.example.deezerapimusicapp.util.loadUrl
import com.example.deezerapimusicapp.viewmodel.ArtistDetailViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ArtistDetailFragment: Fragment(R.layout.artist_details_fragment) {

    private var getArtistPic : String = ""
    private var getArtistName : String = ""
    private var getArtistId : String = ""
    private val args : ArtistDetailFragmentArgs by navArgs()
    private lateinit var fragmentBinding : ArtistDetailsFragmentBinding
    private val artistDetailViewModel : ArtistDetailViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        args.artistDetailId.let {
            getArtistPic = it

        }
        args.getArtistName.let {
            getArtistName = it
        }
        args.getArtistId.let {
            getArtistId = it.toString()
        }
        updateArtistDetailState(getArtistId)
        fragmentBinding = ArtistDetailsFragmentBinding.inflate(inflater)
        fragmentBinding.artistDetailArtistImageView.loadUrl(getArtistPic)
        fragmentBinding.artistDetailArtistNameTextView.text = getArtistName
        return fragmentBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


    }

    private fun updateArtistDetailState(getArtistId : String){
        viewLifecycleOwner.lifecycleScope.launch {
            artistDetailViewModel.getArtistDetail(getArtistId)
            artistDetailViewModel.albumState.collect{ albumState->
                if (albumState.isLoading){
                    Toast.makeText(requireContext(),"Yükleniyor",Toast.LENGTH_LONG).show()
                }else if(albumState.isSuccess){
                    fragmentBinding.recyclerViewArtistDetail.visibility = View.VISIBLE
                    setRecyclerView(albumState.albumList!!.data)
                }else{
                    fragmentBinding.recyclerViewArtistDetail.visibility=View.VISIBLE
                    Toast.makeText(requireContext(),"No no", Toast.LENGTH_LONG).show()
                }
            }
        }
    }

    fun setRecyclerView(albumList: List<Data>){
        fragmentBinding.recyclerViewArtistDetail.adapter = ArtistDetailAdapter(albumList,
            ArtistDetailAdapter.OnClickListener{
                findNavController().navigate(ArtistDetailFragmentDirections.actionArtistDetailFragmentToAlbumDetailsFragment(it.album.id,it.album.title))
            })

    }

    }

