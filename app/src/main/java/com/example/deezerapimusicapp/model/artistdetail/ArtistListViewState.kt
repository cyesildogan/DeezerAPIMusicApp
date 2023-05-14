package com.example.deezerapimusicapp.model.artistdetail


data class ArtistListViewState(
    val isSuccess: Boolean = false,
    val isLoading: Boolean = true,
    val artistList: List<ArtistDetailModel>? = null,
    val error: String = "",
)
