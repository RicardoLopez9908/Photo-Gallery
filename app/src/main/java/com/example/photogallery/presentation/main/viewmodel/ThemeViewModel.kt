package com.example.photogallery.presentation.main.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.photogallery.domain.repository.PhotoGalleryRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ThemeViewModel @Inject constructor(
    private val repository: PhotoGalleryRepository
) : ViewModel() {

    private var _isDarkTheme = MutableStateFlow<Boolean?>(null)
    var isDarkTheme = _isDarkTheme.asStateFlow()

    init {
        getIsDarkThemeValue()
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
