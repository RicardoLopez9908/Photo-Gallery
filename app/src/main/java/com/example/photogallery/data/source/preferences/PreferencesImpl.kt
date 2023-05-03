package com.example.photogallery.data.source.preferences

import android.content.Context
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import com.example.photogallery.domain.source.preferences.Preferences
import kotlinx.coroutines.flow.first
import java.lang.Exception
import javax.inject.Inject

private const val PHOTO_GALLERY_PREFERENCES = "photo_gallery_MM"
private const val DARK_THEME_KEY = "dark_theme_key"

private val Context.dataStore by preferencesDataStore(name = PHOTO_GALLERY_PREFERENCES)

class PreferencesImpl @Inject constructor(
    private val context: Context
) : Preferences {

    override suspend fun putDarkThemeValue(value: Boolean) {
        val preferenceKey = booleanPreferencesKey(DARK_THEME_KEY)
        context.dataStore.edit { preferences ->
            preferences[preferenceKey] = value
        }
    }

    override suspend fun getDarkThemeValue(): Boolean? {
        return try {
            val preferencesKey = booleanPreferencesKey(DARK_THEME_KEY)
            val preferences = context.dataStore.data.first()
            preferences[preferencesKey]
        } catch (e: Exception) {
            null
        }
    }
}
