package com.example.deezerapimusicapp.repository.albumdetail


import com.example.deezerapimusicapp.model.albumDetail.AlbumDetailsModel
import com.example.deezerapimusicapp.util.Resource
import kotlinx.coroutines.flow.Flow

interface AlbumDetailRepository {
    suspend fun getArtists(getId: String): Flow<Resource<AlbumDetailsModel>>

}