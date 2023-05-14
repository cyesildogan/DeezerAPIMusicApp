package com.example.deezerapimusicapp.viewmodel

import android.app.Application
import com.example.deezerapimusicapp.model.albumDetail.AlbumDetailViewState
import com.example.deezerapimusicapp.repository.albumdetail.AlbumDetailRepository
import com.example.deezerapimusicapp.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class AlbumDetailsViewModel @Inject constructor(
    private val repository: AlbumDetailRepository,
    application: Application
) : BaseViewModel(application){

    private val _albumDetailState = MutableStateFlow(AlbumDetailViewState())
    val albumState : StateFlow<AlbumDetailViewState> = _albumDetailState.asStateFlow()




    suspend fun getAlbumDetails(getAlbumId: String){
        repository.getArtists(getAlbumId).collect{result->
            when(result){
                is Resource.Success -> {
                    _albumDetailState.value = result.data?.let {
                        AlbumDetailViewState(isSuccess = true,
                            isLoading = false,
                            albumDetailList = it.tracks,
                            albumReleaseDetail = it.tracks.data,
                            error = "")
                    }!!
                }is Resource.Loading -> {
                _albumDetailState.update {
                    it.copy(
                        isLoading = true
                    )
                }
            }

                is Resource.Error -> {
                    _albumDetailState.update {
                        it.copy(
                            error = "Error"
                        )
                    }
                }
            }

        }
    }

}