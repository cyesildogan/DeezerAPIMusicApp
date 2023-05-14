package com.example.deezerapimusicapp.model.albums

import com.example.deezerapimusicapp.model.albumDetail.AlbumDetailsModel
import com.example.deezerapimusicapp.model.artistdetail.ArtistDetailModel


data class AlbumsViewState(
    val isSuccess: Boolean = false,
    val isLoading: Boolean = true,
    val albumList: AlbumsModel?=null,
    val albumRelease: AlbumDetailsModel? =null,
    val error: String = ""
) {
}