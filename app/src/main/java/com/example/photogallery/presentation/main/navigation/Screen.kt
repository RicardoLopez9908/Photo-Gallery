package com.example.photogallery.presentation.main.navigation

sealed class Screen(val route: String) {
    object Albums : Screen(route = "albums_screen")
}
