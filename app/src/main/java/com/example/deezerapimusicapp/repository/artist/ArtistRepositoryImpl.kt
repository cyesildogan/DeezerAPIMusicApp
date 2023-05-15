package com.example.deezerapimusicapp.repository.artist

import com.example.deezerapimusicapp.api.RetrofitAPI
import com.example.deezerapimusicapp.model.artist.ArtistsModel
import com.example.deezerapimusicapp.repository.BaseRepository
import com.example.deezerapimusicapp.util.Resource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ArtistRepositoryImpl @Inject constructor(
    private val retrofitAPI: RetrofitAPI
) : BaseRepository(), ArtistRepository {
    override suspend fun getArtists(getId: String): Flow<Resource<ArtistsModel>> {
        return getResult {
            retrofitAPI.getArtistList(getId)
        }
    }
}