package com.example.photogallery.presentation.initial.view

import androidx.annotation.DrawableRes
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.repeatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ExperimentalMotionApi
import androidx.constraintlayout.compose.MotionLayout
import androidx.constraintlayout.compose.MotionScene
import com.example.photogallery.R
import com.example.photogallery.presentation.base.view.JumperButton
import com.example.photogallery.ui.theme.MinimalistFontFamily
import com.example.photogallery.ui.theme.Purple80

private const val ANIMATION_DURATION = 2500

@OptIn(ExperimentalMotionApi::class)
@Composable
fun InitialScreen(
    navigateToNextScreen: () -> Unit
) {
    val animatedAlpha = remember { Animatable(0f) }

    LaunchedEffect(animatedAlpha) {
        animatedAlpha.animateTo(
            1f,
            animationSpec = repeatable(
                iterations = 1,
                animation = tween(
                    durationMillis = ANIMATION_DURATION,
                    easing = LinearEasing
                )
            )
        )
    }

    val context = LocalContext.current
    val motionSceneContent = remember {
        context.resources
            .openRawResource(R.raw.initial_screen_motion_scene)
            .readBytes()
            .decodeToString()
    }
    MotionLayout(
        motionScene = MotionScene(content = motionSceneContent),
        progress = animatedAlpha.value,
        modifier = Modifier.fillMaxSize()
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
                .layoutId("background")
        )
        for (i in 1..9) {
            Image(
                painter = painterResource(id = getDrawablePicture(i)),
                contentDescription = null,
                modifier = Modifier.layoutId("picture_example_$i")
            )
        }
        Text(
            text = stringResource(id = R.string.app_name),
            modifier = Modifier
                .layoutId("title_text")
                .clip(RoundedCornerShape(8.dp))
                .background(Purple80)
                .padding(dimensionResource(id = R.dimen.initial_title_text_padding)),
            style = MaterialTheme.typography.displayMedium.copy(fontFamily = MinimalistFontFamily)
        )
        JumperButton(
            modifier = Modifier
                .padding(bottom = dimensionResource(id = R.dimen.start_button_bottom_padding))
                .layoutId("start_button"),
            text = stringResource(id = R.string.start_button),
            onClick = navigateToNextScreen,
            jump = true
        )
    }
}

@DrawableRes
private fun getDrawablePicture(pictureNumber: Int): Int =
    when (pictureNumber) {
        1 -> R.drawable.picture_example_1
        2 -> R.drawable.picture_example_2
        3 -> R.drawable.picture_example_3
        4 -> R.drawable.picture_example_4
        5 -> R.drawable.picture_example_5
        6 -> R.drawable.picture_example_6
        7 -> R.drawable.picture_example_7
        8 -> R.drawable.picture_example_8
        else -> R.drawable.picture_example_9
    }
