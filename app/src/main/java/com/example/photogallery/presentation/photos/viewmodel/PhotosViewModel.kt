package com.example.photogallery.presentation.photos.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.example.photogallery.domain.repository.PhotoGalleryRepository
import com.example.photogallery.presentation.base.viewmodel.BaseViewModel
import com.example.photogallery.presentation.main.navigation.NavigationConstants.ALBUM_ID
import com.example.photogallery.util.Response
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PhotosViewModel @Inject constructor(
    private val repository: PhotoGalleryRepository,
    savedStateHandle: SavedStateHandle
) : BaseViewModel() {

    private val albumId: Int? = savedStateHandle[ALBUM_ID]

    init {
        getPhotos()
    }

    fun getPhotos() {
        _screenContent.tryEmit(Response.Loading)
        viewModelScope.launch(contextAndErrorHandler) {
            albumId?.let {
                val response = repository.getPhotos(albumId)

                _screenContent.emit(response)
            } ?: run {
                _screenContent.emit(Response.Error(exception = Exception("No recibimos un albumId"))) // TODO: create custom exception
            }
        }
    }
}
