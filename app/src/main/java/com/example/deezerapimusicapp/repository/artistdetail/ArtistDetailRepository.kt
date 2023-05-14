package com.example.deezerapimusicapp.repository.artistdetail

import com.example.deezerapimusicapp.model.artistdetail.ArtistDetailModel
import com.example.deezerapimusicapp.util.Resource
import kotlinx.coroutines.flow.Flow

interface ArtistDetailRepository {

    suspend fun getArtistDetail(getId : String) : Flow<Resource<List<ArtistDetailModel>>>
}