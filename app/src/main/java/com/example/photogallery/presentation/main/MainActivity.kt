package com.example.photogallery.presentation.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.collectAsState
import androidx.navigation.compose.rememberNavController
import com.example.photogallery.presentation.albums.viewmodel.AlbumsViewModel
import com.example.photogallery.presentation.main.navigation.SetupNavGraph
import com.example.photogallery.ui.theme.PhotoGalleryTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val albumsViewModel: AlbumsViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val darkMode = albumsViewModel.isDarkTheme.collectAsState()
            PhotoGalleryTheme(
                darkTheme = darkMode.value ?: isSystemInDarkTheme()
            ) {
                SetupNavGraph(
                    navController = rememberNavController(),
                    albumsViewModel = albumsViewModel
                )
            }
        }
    }
}
