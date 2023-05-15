package com.example.deezerapimusicapp.di

import com.example.deezerapimusicapp.api.RetrofitAPI
import com.example.deezerapimusicapp.repository.album.AlbumRepository
import com.example.deezerapimusicapp.repository.album.AlbumRepositoryImpl
import com.example.deezerapimusicapp.repository.albumdetail.AlbumDetailRepository
import com.example.deezerapimusicapp.repository.albumdetail.AlbumDetailRepositoryImpl
import com.example.deezerapimusicapp.repository.artist.ArtistRepository
import com.example.deezerapimusicapp.repository.artist.ArtistRepositoryImpl
import com.example.deezerapimusicapp.repository.artistdetail.ArtistDetailRepository
import com.example.deezerapimusicapp.repository.artistdetail.ArtistDetailRepositoryImpl
import com.example.deezerapimusicapp.repository.genre.GenreRepository
import com.example.deezerapimusicapp.repository.genre.GenreRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object DataModule {
    @Provides
    fun providesApiGenreRepository(retrofitAPI: RetrofitAPI): GenreRepository {
        return GenreRepositoryImpl(retrofitAPI)
    }
    @Provides
    fun providesApiArtistRepository(retrofitAPI: RetrofitAPI) : ArtistRepository{
        return ArtistRepositoryImpl(retrofitAPI)
    }
    @Provides
    fun providesApiArtistDetailRepository(retrofitAPI: RetrofitAPI) : ArtistDetailRepository{
        return ArtistDetailRepositoryImpl(retrofitAPI)
    }
    @Provides
    fun providesApiAlbumRepository(retrofitAPI: RetrofitAPI) : AlbumRepository{
        return AlbumRepositoryImpl(retrofitAPI)
    }
    @Provides
    fun providesApiAlbumDetailRepository(retrofitAPI: RetrofitAPI) : AlbumDetailRepository{
        return AlbumDetailRepositoryImpl(retrofitAPI)
    }
}