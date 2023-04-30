package com.example.photogallery.presentation.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.rememberNavController
import com.example.photogallery.PhotoGalleryApp
import com.example.photogallery.presentation.main.navigation.SetupNavGraph
import com.example.photogallery.ui.theme.PhotoGalleryTheme
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var application: PhotoGalleryApp
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PhotoGalleryTheme(darkTheme = application.colorThemeIsDark.value) {
                SetupNavGraph(
                    navController = rememberNavController(),
                    changeThemeColor = application::changeColorTheme
                )
            }
        }
    }
}
