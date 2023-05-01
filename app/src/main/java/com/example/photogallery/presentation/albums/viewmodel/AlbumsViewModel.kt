package com.example.photogallery.presentation.albums.viewmodel

import androidx.lifecycle.viewModelScope
import com.example.photogallery.domain.repository.PhotoGalleryRepository
import com.example.photogallery.presentation.base.viewmodel.BaseViewModel
import com.example.photogallery.util.Response
import com.example.photogallery.util.Response.Loading
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AlbumsViewModel @Inject constructor(
    private val repository: PhotoGalleryRepository
) : BaseViewModel() {

    init {
        getAlbums()
    }

    fun getAlbums() {
        screenContent.tryEmit(Loading)
        viewModelScope.launch(Dispatchers.Main + coroutineExceptionHandler) {
            val response = repository.getAlbums()

            if (response is Response.Success) {
                response.data.orEmpty().map { album ->
                    async {
                        val photos = repository.getPhotos(albumId = album.id)
                        if (photos is Response.Success && !photos.data.isNullOrEmpty()) {
                            album.photoDataList = photos.data
                        }
                    }
                }.awaitAll()
            }

            screenContent.emit(response)
        }
    }
}
