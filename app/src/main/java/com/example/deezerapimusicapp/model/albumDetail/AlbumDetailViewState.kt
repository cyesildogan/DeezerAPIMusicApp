package com.example.deezerapimusicapp.model.albumDetail

import com.example.deezerapimusicapp.model.genre.GenreModel

data class AlbumDetailViewState(
    val isSuccess: Boolean = false,
    val isLoading: Boolean = true,
    val albumDetailList: Tracks? = null,
    val albumReleaseDetail: List<DataX>? = null,
    val error: String = ""
)
