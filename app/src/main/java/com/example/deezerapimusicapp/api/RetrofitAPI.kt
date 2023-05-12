package com.example.deezerapimusicapp.api

import com.example.deezerapimusicapp.model.artist.ArtistsModel
import com.example.deezerapimusicapp.model.genre.GenreModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface RetrofitAPI {

    @GET("genre")
   suspend fun getGenreList() : Response<GenreModel>


    @GET("genre/{id}/artists")
    suspend fun getArtistList(@Path("id") categoriesId : String) : Response<ArtistsModel>






}