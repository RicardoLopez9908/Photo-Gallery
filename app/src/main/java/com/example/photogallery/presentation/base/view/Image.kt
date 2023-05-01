package com.example.photogallery.presentation.base.view

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ShapeDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.photogallery.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomAsyncCardImage(
    onClick: () -> Unit,
    title: String,
    thumbnailUrl: String
) {
    val localDensity = LocalDensity.current
    var cardWidth by remember { mutableStateOf(0.dp) }

    val alphaVisibility = if (cardWidth == 0.dp) 0f else 1f

    var isLoading by remember { mutableStateOf(false) }

    Card(
        modifier = Modifier.alpha(alphaVisibility),
        onClick = onClick,
        elevation = CardDefaults.elevatedCardElevation(
            defaultElevation = dimensionResource(id = R.dimen.custom_async_card_elevation)
        )
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .alpha(alphaVisibility)
                .padding(dimensionResource(id = R.dimen.custom_card_content_padding))
                .onGloballyPositioned { coordinates ->
                    cardWidth = with(localDensity) { coordinates.size.width.toDp() }
                }
        ) {
            Box {
                AsyncImage(
                    model = thumbnailUrl,
                    contentDescription = title,
                    modifier = Modifier
                        .alpha(if (isLoading) 0f else 1f)
                        .fillMaxWidth()
                        .clip(shape = ShapeDefaults.Medium),
                    contentScale = ContentScale.FillWidth,
                    onLoading = { isLoading = true },
                    onSuccess = { isLoading = false }
                )
                if (isLoading) {
                    Box(
                        modifier = Modifier
                            .alpha(alphaVisibility)
                            .size(cardWidth),
                        contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator(
                            color = MaterialTheme.colorScheme.primary
                        )
                    }
                }
            }
            Text(
                modifier = Modifier
                    .alpha(alphaVisibility)
                    .padding(top = dimensionResource(id = R.dimen.custom_card_title_top_padding)),
                text = title,
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.titleMedium
            )
        }
    }
}
