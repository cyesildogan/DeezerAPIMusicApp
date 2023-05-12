package com.example.deezerapimusicapp.model.genre



data class GenreViewState(
    val isSuccess: Boolean = false,
    val isLoading: Boolean = true,
    val genreList: GenreModel? = null,
    val error: String = "",

    )
