package com.example.photogallery.domain.model

data class PhotoData(
    val id: Int,
    val title: String,
    val url: String,
    val thumbnailUrl: String,
    val albumId: Int? = null
)
