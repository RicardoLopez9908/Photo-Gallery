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
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AlbumsViewModel @Inject constructor(
    private val repository: PhotoGalleryRepository
) : BaseViewModel() {

    private var _isDarkTheme = MutableStateFlow<Boolean?>(null)
    var isDarkTheme = _isDarkTheme.asStateFlow()

    init {
        getIsDarkThemeValue()
        getAlbums()
    }

    fun getAlbums() {
        _screenContent.tryEmit(Loading)
        viewModelScope.launch(contextAndErrorHandler) {
            val response = repository.getAlbums()

            if (response is Response.Success) {
                response.data.orEmpty().map { album ->
                    async {
                        val photos = repository.getPhotos(albumId = album.id)
                        if (photos is Response.Success) {
                            album.thumbnailUrl = photos.data?.first()?.thumbnailUrl
                        }
                    }
                }.awaitAll()
            }
            _screenContent.emit(response)
        }
    }

    fun saveIsDarkThemeValue(isDark: Boolean) {
        viewModelScope.launch(Dispatchers.Main) {
            repository.putDarkThemeValue(isDark)
            _isDarkTheme.emit(isDark)
        }
    }

    private fun getIsDarkThemeValue() {
        viewModelScope.launch(Dispatchers.Main) {
            val isDark = repository.getDarkThemeValue()
            _isDarkTheme.emit(isDark)
        }
    }
}
