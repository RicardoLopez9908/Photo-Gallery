package com.example.photogallery.utils

import com.example.photogallery.domain.model.AlbumData
import com.example.photogallery.utils.CommonValuesMock.HTTPS_EXAMPLE

object AlbumListMock {

    fun getAlbumList(): List<AlbumData> =
        listOf(
            AlbumData(
                id = 1,
                title = "1",
                userId = 1,
                thumbnailUrl = HTTPS_EXAMPLE
            ),
            AlbumData(
                id = 2,
                title = "2",
                userId = 2,
                thumbnailUrl = HTTPS_EXAMPLE
            ),
            AlbumData(
                id = 3,
                title = "3",
                userId = 3,
                thumbnailUrl = HTTPS_EXAMPLE
            ),
            AlbumData(
                id = 4,
                title = "4",
                userId = 4,
                thumbnailUrl = HTTPS_EXAMPLE
            ),
            AlbumData(
                id = 5,
                title = "5",
                userId = 5,
                thumbnailUrl = HTTPS_EXAMPLE
            )
        )
}
