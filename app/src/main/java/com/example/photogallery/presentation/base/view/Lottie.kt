package com.example.photogallery.presentation.base.view

import androidx.annotation.RawRes
import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition

@Composable
fun BasicLottie(
    modifier: Modifier = Modifier,
    @RawRes resource: Int,
    iterations: Int = LottieConstants.IterateForever
) {
    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(resource))

    val animationState = animateLottieCompositionAsState(
        composition,
        iterations = iterations
    )
    Box(modifier = modifier) {
        LottieAnimation(
            composition = composition,
            progress = animationState.progress
        )
    }
}
