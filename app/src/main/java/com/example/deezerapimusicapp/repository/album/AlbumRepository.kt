package com.example.deezerapimusicapp.repository.album

import com.example.deezerapimusicapp.model.albums.AlbumsModel
import com.example.deezerapimusicapp.model.albums.Data
import com.example.deezerapimusicapp.util.Resource
import kotlinx.coroutines.flow.Flow

interface AlbumRepository {

    suspend fun getAlbums(getArtistId: String): Flow<Resource<AlbumsModel>>

}