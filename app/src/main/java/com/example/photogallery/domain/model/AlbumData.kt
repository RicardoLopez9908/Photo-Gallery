package com.example.photogallery.domain.model

data class AlbumData(
    val id: Int,
    val userId: Int? = null,
    val title: String? = null,
    // set this value manually:
    var photoDataList: List<PhotoData>? = null
)
