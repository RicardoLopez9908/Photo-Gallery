package com.example.photogallery.domain.repository

import com.example.photogallery.domain.model.AlbumData
import com.example.photogallery.domain.model.PhotoData
import com.example.photogallery.util.Response

interface PhotoGalleryRepository {

    suspend fun getAlbums(): Response<List<AlbumData>>

    suspend fun getPhotos(albumId: Int): Response<List<PhotoData>>

    suspend fun putDarkThemeValue(value: Boolean)

    suspend fun getDarkThemeValue(): Boolean?
}
