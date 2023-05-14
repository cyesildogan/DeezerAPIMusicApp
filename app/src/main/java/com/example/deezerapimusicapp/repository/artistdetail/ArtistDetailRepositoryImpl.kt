package com.example.deezerapimusicapp.repository.artistdetail

import com.example.deezerapimusicapp.api.RetrofitAPI
import com.example.deezerapimusicapp.model.albums.Data
import com.example.deezerapimusicapp.model.artistdetail.ArtistDetailModel
import com.example.deezerapimusicapp.repository.BaseRepository
import com.example.deezerapimusicapp.util.Resource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ArtistDetailRepositoryImpl @Inject constructor(
    private val retrofitAPI: RetrofitAPI
) : BaseRepository(), ArtistDetailRepository{
    override suspend fun getArtistDetail(getId: String): Flow<Resource<List<ArtistDetailModel>>> {
        return getResult {
            retrofitAPI.getArtistDetail(getId)
        }
    }

}