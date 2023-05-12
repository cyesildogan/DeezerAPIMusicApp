package com.example.deezerapimusicapp.repository.genre


import com.example.deezerapimusicapp.api.RetrofitAPI
import com.example.deezerapimusicapp.model.genre.GenreModel
import com.example.deezerapimusicapp.repository.BaseRepository
import com.example.deezerapimusicapp.util.Resource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GenreRepositoryImpl @Inject constructor(
    private val retrofitAPI: RetrofitAPI
) : BaseRepository(), GenreRepository {

    override suspend fun getGenre(): Flow<Resource<GenreModel>> {
        return getResult {
            retrofitAPI.getGenreList()
        }


    }


}