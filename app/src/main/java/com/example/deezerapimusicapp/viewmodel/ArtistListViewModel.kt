package com.example.deezerapimusicapp.viewmodel

import android.app.Application
import com.example.deezerapimusicapp.model.artist.ArtistViewState
import com.example.deezerapimusicapp.repository.artist.ArtistRepository
import com.example.deezerapimusicapp.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class ArtistListViewModel @Inject constructor(
    private val repository: ArtistRepository,
    application: Application,

    ) : BaseViewModel(application) {

    private val _artistState = MutableStateFlow(ArtistViewState())
    val artistState: StateFlow<ArtistViewState> = _artistState.asStateFlow()
    suspend fun getArtists(getCategoriesId: String) {
        repository.getArtists(getCategoriesId).collect { result ->
            when (result) {
                is Resource.Success -> {
                    _artistState.value = result.data?.let {
                        ArtistViewState(isSuccess = true, isLoading = false, artistList = it, "")
                    }!!
                }
                is Resource.Loading -> {
                    _artistState.update {
                        it.copy(
                            isLoading = true
                        )
                    }
                }
                is Resource.Error -> {
                    _artistState.update {
                        it.copy(
                            error = "Error"
                        )
                    }
                }
            }
        }
    }
}

