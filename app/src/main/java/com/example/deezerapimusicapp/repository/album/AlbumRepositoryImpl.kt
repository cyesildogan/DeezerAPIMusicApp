package com.example.deezerapimusicapp.repository.album

import com.example.deezerapimusicapp.api.RetrofitAPI
import com.example.deezerapimusicapp.model.albums.AlbumsModel
import com.example.deezerapimusicapp.model.albums.Data
import com.example.deezerapimusicapp.repository.BaseRepository
import com.example.deezerapimusicapp.util.Resource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class AlbumRepositoryImpl @Inject constructor(
    private val retrofitAPI: RetrofitAPI
) : BaseRepository(), AlbumRepository {
    override suspend fun getAlbums(getArtistId: String): Flow<Resource<AlbumsModel>> {
        return getResult {
            retrofitAPI.getAlbums(getArtistId)
        }
    }
}