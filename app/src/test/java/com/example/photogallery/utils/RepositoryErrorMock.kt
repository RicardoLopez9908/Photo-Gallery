package com.example.photogallery.utils

import com.example.photogallery.domain.model.AlbumData
import com.example.photogallery.domain.model.PhotoData
import com.example.photogallery.domain.repository.PhotoGalleryRepository
import com.example.photogallery.util.Response
import com.example.photogallery.utils.Mocks.EXCEPTION_MESSAGE

class RepositoryErrorMock : PhotoGalleryRepository {

    override suspend fun getAlbums(): Response<List<AlbumData>> =
        Response.Error(Exception(EXCEPTION_MESSAGE))

    override suspend fun getPhotos(albumId: Int): Response<List<PhotoData>> =
        Response.Error(Exception(EXCEPTION_MESSAGE))

    override suspend fun putDarkThemeValue(value: Boolean) {
        // empty Test
    }

    override suspend fun getDarkThemeValue(): Boolean? = null
}
