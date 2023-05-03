package com.example.photogallery.data.repository

import com.example.photogallery.data.api.PhotoGalleryApi
import com.example.photogallery.domain.model.AlbumData
import com.example.photogallery.domain.model.PhotoData
import com.example.photogallery.domain.repository.PhotoGalleryRepository
import com.example.photogallery.domain.source.preferences.Preferences
import com.example.photogallery.util.Response
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class PhotoGalleryRepositoryImpl @Inject constructor(
    private val api: PhotoGalleryApi,
    private val preferences: Preferences
) : PhotoGalleryRepository {

    override suspend fun getAlbums(): Response<List<AlbumData>> =
        withContext(Dispatchers.IO) {
            return@withContext try {
                Response.Success(
                    data = api.getAlbums()
                )
            } catch (e: Exception) {
                Response.Error(exception = e)
            }
        }

    override suspend fun getPhotos(albumId: Int): Response<List<PhotoData>> =
        withContext(Dispatchers.IO) {
            return@withContext try {
                Response.Success(
                    data = api.getPhotos(albumId)
                )
            } catch (e: Exception) {
                Response.Error(exception = e)
            }
        }

    override suspend fun putDarkThemeValue(value: Boolean) {
        withContext(Dispatchers.IO) {
            preferences.putDarkThemeValue(value)
        }
    }

    override suspend fun getDarkThemeValue(): Boolean? =
        withContext(Dispatchers.IO) {
            preferences.getDarkThemeValue()
        }
}
