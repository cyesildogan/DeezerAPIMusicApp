package com.example.deezerapimusicapp.repository.albumdetail

import com.example.deezerapimusicapp.api.RetrofitAPI
import com.example.deezerapimusicapp.model.albumDetail.AlbumDetailsModel
import com.example.deezerapimusicapp.repository.BaseRepository
import com.example.deezerapimusicapp.util.Resource
import kotlinx.coroutines.flow.Flow

class AlbumDetailRepositoryImpl(
    private val retrofitAPI: RetrofitAPI
): BaseRepository(),AlbumDetailRepository {
    override suspend fun getArtists(getId: String): Flow<Resource<AlbumDetailsModel>> {
        return getResult {
            retrofitAPI.getAlbumDetail(getId)
        }
    }
}