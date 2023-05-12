package com.example.deezerapimusicapp.model.artist


data class ArtistViewState(
    val isSuccess: Boolean = false,
    val isLoading: Boolean = true,
    val artistList: ArtistsModel? = null,
    val error: String = "",

    ) {
}