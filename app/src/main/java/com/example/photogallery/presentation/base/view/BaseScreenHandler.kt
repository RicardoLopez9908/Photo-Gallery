package com.example.photogallery.presentation.base.view

import androidx.compose.runtime.Composable
import com.example.photogallery.util.Response

@Composable
fun BaseScreenHandler(
    response: Response<Any?>,
    onClickRetry: () -> Unit,
    successContent: @Composable () -> Unit
) {
    when (response) {
        is Response.Success -> successContent()
        is Response.Loading -> LoadingScreen()
        is Response.Error -> ErrorScreen(
            e = response.exception,
            retryOnClick = onClickRetry
        )
    }
}
