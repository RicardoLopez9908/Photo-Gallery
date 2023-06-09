package com.example.photogallery.presentation.main.navigation

sealed class Screen(val route: String) {

    object Initial : Screen(route = "initial_screen")
    object Albums : Screen(route = "albums_screen")
    object Photos : Screen(route = "photos_screen")
    object PhotoDetail : Screen(route = "photo_detail_screen")
}
