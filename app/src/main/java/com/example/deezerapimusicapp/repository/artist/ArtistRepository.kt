package com.example.deezerapimusicapp.repository.artist

import com.example.deezerapimusicapp.model.artist.ArtistsModel
import com.example.deezerapimusicapp.util.Resource
import kotlinx.coroutines.flow.Flow

interface ArtistRepository {

    suspend fun getArtists(getId : String) : Flow<Resource<ArtistsModel>>

}