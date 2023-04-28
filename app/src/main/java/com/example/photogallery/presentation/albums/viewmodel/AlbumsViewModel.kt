package com.example.photogallery.presentation.albums.viewmodel

import androidx.lifecycle.viewModelScope
import com.example.photogallery.domain.repository.PhotoGalleryRepository
import com.example.photogallery.presentation.base.viewmodel.BaseViewModel
import com.example.photogallery.util.Response
import com.example.photogallery.util.Response.Loading
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class AlbumsViewModel @Inject constructor(
    private val repository: PhotoGalleryRepository
) : BaseViewModel() {

    init { getAlbums() }

    fun getAlbums() {
        screenContent.tryEmit(Loading)
        viewModelScope.launch(Dispatchers.Main + coroutineExceptionHandler) {
            val response = repository.getAlbums()

            if (response is Response.Success && response.data.isNullOrEmpty()) {
                screenContent.emit(Response.Error(Exception("Error example"))) // todo: create custom Exception
            } else {
                screenContent.emit(response)
            }
        }
    }
}
