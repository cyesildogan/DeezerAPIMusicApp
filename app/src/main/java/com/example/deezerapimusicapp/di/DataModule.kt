package com.example.deezerapimusicapp.di

import com.example.deezerapimusicapp.api.RetrofitAPI
import com.example.deezerapimusicapp.repository.artist.ArtistRepository
import com.example.deezerapimusicapp.repository.artist.ArtistRepositoryImpl
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
    // providesApiRemoteDataSource
    @Provides
    fun providesApiArtistRepository(retrofitAPI: RetrofitAPI) : ArtistRepository{
        return ArtistRepositoryImpl(retrofitAPI)
    }
}