package com.example.photogallery.domain.source.preferences

interface Preferences {

    suspend fun putDarkThemeValue(value: Boolean)

    suspend fun getDarkThemeValue(): Boolean?
}
