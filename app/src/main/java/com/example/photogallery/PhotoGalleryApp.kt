package com.example.photogallery

import android.app.Application
import androidx.compose.runtime.mutableStateOf
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class PhotoGalleryApp : Application() {
    var colorThemeIsDark = mutableStateOf(false)
        private set

    fun changeColorTheme() {
        colorThemeIsDark.value = !colorThemeIsDark.value
    }
}
