package com.example.photogallery.presentation.base.view

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp

@Composable
fun JumperButton(
    modifier: Modifier = Modifier,
    text: String,
    onClick: () -> Unit,
    jump: Boolean = true
) {
    var travelDistance by remember { mutableStateOf(0f) }
    if (jump) {
        val dy by rememberInfiniteTransition().animateFloat(
            initialValue = 0f,
            targetValue = 1f,
            animationSpec = infiniteRepeatable(
                animation = tween(1000, easing = LinearEasing),
                repeatMode = RepeatMode.Reverse
            )
        )
        travelDistance = with(LocalDensity.current) { 8.dp.toPx() } * dy
    }

    Button(
        onClick = onClick,
        modifier = modifier
            .graphicsLayer {
                translationY = travelDistance
            }
    ) { Text(text) }
}
