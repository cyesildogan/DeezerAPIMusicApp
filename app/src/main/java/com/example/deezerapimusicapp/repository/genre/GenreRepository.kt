package com.example.deezerapimusicapp.repository.genre


import com.example.deezerapimusicapp.model.genre.GenreModel
import com.example.deezerapimusicapp.util.Resource
import kotlinx.coroutines.flow.Flow


interface GenreRepository {

    suspend fun getGenre() : Flow<Resource<GenreModel>>



}