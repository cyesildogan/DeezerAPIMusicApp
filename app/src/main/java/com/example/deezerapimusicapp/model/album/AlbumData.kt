package com.example.deezerapimusicapp.model.album

data class AlbumData(
    val `data`: List<Data>,
    val next: String,
    val total: Int
)