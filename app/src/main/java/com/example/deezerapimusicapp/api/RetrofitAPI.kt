package com.example.deezerapimusicapp.api

import com.example.deezerapimusicapp.model.albumDetail.AlbumDetailsModel
import com.example.deezerapimusicapp.model.albums.AlbumsModel
import com.example.deezerapimusicapp.model.artist.ArtistsModel
import com.example.deezerapimusicapp.model.artistdetail.ArtistDetailModel

import com.example.deezerapimusicapp.model.genre.GenreModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface RetrofitAPI {

    @GET("genre")
   suspend fun getGenreList() : Response<GenreModel>


    @GET("genre/{id}/artists")
    suspend fun getArtistList(@Path("id") categoriesId : String) : Response<ArtistsModel>

    @GET("artist/{artist_id}")
    suspend fun getArtistDetail(@Path("artist_id") artistId : String) : Response<List<ArtistDetailModel>>

    @GET("artist/{artist_id}/top?limit=50")
    suspend fun getAlbums(@Path("artist_id") artistId: String) : Response<AlbumsModel>

    @GET("album/{album_id}")
    suspend fun getAlbumDetail(@Path("album_id") albumId : String) : Response<AlbumDetailsModel>




}