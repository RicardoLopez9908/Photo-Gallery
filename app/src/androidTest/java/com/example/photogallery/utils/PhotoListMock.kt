package com.example.photogallery.utils

import com.example.photogallery.domain.model.PhotoData
import com.example.photogallery.utils.CommonValuesMock.HTTPS_EXAMPLE

object PhotoListMock {
    fun getPhotoList(): List<PhotoData> =
        listOf(
            PhotoData(
                id = 1,
                title = "1",
                url = HTTPS_EXAMPLE,
                thumbnailUrl = HTTPS_EXAMPLE,
                albumId = 1
            ),
            PhotoData(
                id = 2,
                title = "2",
                url = HTTPS_EXAMPLE,
                thumbnailUrl = HTTPS_EXAMPLE,
                albumId = 1
            ),
            PhotoData(
                id = 3,
                title = "3",
                url = HTTPS_EXAMPLE,
                thumbnailUrl = HTTPS_EXAMPLE,
                albumId = 1
            ),
            PhotoData(
                id = 4,
                title = "4",
                url = HTTPS_EXAMPLE,
                thumbnailUrl = HTTPS_EXAMPLE,
                albumId = 1
            ),
            PhotoData(
                id = 5,
                title = "5",
                url = HTTPS_EXAMPLE,
                thumbnailUrl = HTTPS_EXAMPLE,
                albumId = 1
            )
        )
}
