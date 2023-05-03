package com.example.photogallery.utils

import com.example.photogallery.domain.model.AlbumData
import com.example.photogallery.domain.model.PhotoData

object Mocks {

    const val EXCEPTION_MESSAGE = "GENERIC_ERROR_MOCK"

    fun getAlbumList(): List<AlbumData> =
        listOf(
            AlbumData(
                id = 1,
                title = "1",
                userId = 1,
                thumbnailUrl = "HTTPS_EXAMPLE"
            ),
            AlbumData(
                id = 2,
                title = "2",
                userId = 2,
                thumbnailUrl = "HTTPS_EXAMPLE"
            )
        )

    fun getPhotoList(): List<PhotoData> =
        listOf(
            PhotoData(
                id = 1,
                title = "1",
                url = "HTTPS_EXAMPLE",
                thumbnailUrl = "HTTPS_EXAMPLE",
                albumId = 1
            ),
            PhotoData(
                id = 2,
                title = "2",
                url = "HTTPS_EXAMPLE",
                thumbnailUrl = "HTTPS_EXAMPLE",
                albumId = 1
            )
        )
}
