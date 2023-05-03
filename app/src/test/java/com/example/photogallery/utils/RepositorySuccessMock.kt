package com.example.photogallery.utils

import com.example.photogallery.domain.model.AlbumData
import com.example.photogallery.domain.model.PhotoData
import com.example.photogallery.domain.repository.PhotoGalleryRepository
import com.example.photogallery.util.Response

class RepositorySuccessMock : PhotoGalleryRepository {

    var isDarkTheme: Boolean? = null

    override suspend fun getAlbums(): Response<List<AlbumData>> =
        Response.Success(Mocks.getAlbumList())

    override suspend fun getPhotos(albumId: Int): Response<List<PhotoData>> =
        Response.Success(Mocks.getPhotoList())

    override suspend fun putDarkThemeValue(value: Boolean) {
        isDarkTheme = value
    }

    override suspend fun getDarkThemeValue(): Boolean? =
        isDarkTheme
}
