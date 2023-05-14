package com.example.deezerapimusicapp.model.albumDetail


data class AlbumDetailViewState(
    val isSuccess: Boolean = false,
    val isLoading: Boolean = true,
    val albumDetailList: Tracks? = null,
    val error: String = ""
)
