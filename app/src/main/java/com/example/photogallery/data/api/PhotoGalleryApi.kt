package com.example.photogallery.data.api

import com.example.photogallery.domain.model.AlbumData
import com.example.photogallery.domain.model.PhotoData
import retrofit2.http.GET
import retrofit2.http.Path

interface PhotoGalleryApi {

    @GET("albums")
    suspend fun getAlbums(): List<AlbumData>

    @GET("albums/{albumId}/photos")
    suspend fun getPhotos(
        @Path("albumId") albumId: Int
    ): List<PhotoData>
}
