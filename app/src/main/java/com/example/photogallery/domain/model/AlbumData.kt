package com.example.photogallery.domain.model

data class AlbumData(
    val id: Int,
    val title: String,
    val userId: Int? = null,
    // set this value manually:
    var photoDataList: List<PhotoData>? = null
)
