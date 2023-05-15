package com.example.deezerapimusicapp.viewmodel

import android.app.Application
import com.example.deezerapimusicapp.model.albumDetail.AlbumDetailViewState
import com.example.deezerapimusicapp.model.albums.AlbumsViewState
import com.example.deezerapimusicapp.repository.album.AlbumRepository
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
class ArtistDetailViewModel @Inject constructor(
    private val repository: AlbumRepository,
    private val repositoryRelease: AlbumDetailRepository,
    application: Application
) : BaseViewModel(application) {

    private val _albumState = MutableStateFlow(AlbumsViewState())
    val albumState: StateFlow<AlbumsViewState> = _albumState.asStateFlow()
    suspend fun getArtistDetail(getArtistId: String) {
        repository.getAlbums(getArtistId).collect { result ->
            when (result) {
                is Resource.Success -> {
                    _albumState.value = result.data?.let {
                        AlbumsViewState(
                            isSuccess = true, isLoading = false, albumList = it, error = ""
                        )
                    }!!
                }
                is Resource.Loading -> {
                    _albumState.update {
                        it.copy(
                            isLoading = true
                        )
                    }
                }
                is Resource.Error -> {
                    _albumState.update {
                        it.copy(
                            error = "Error"
                        )
                    }
                }
            }
        }
    }
}